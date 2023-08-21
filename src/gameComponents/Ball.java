package gameComponents;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;


import presentation.GameListener;
import presentation.GamePanelManager;

public class Ball extends Abstract2DDrawing {
	
	public static int diameter = GamePanelManager.UNIT_SIZE;
	private boolean alive;
	private double centralX;
	private double centralY;
	private double speedX;
	private double speedY;
	private double InitialSpeedX = 0;
	private double InitialSpeedY = 0;
	public static double speedAdjust;
	private boolean speedChanged;
	public boolean isBallLaunched = false;
	private boolean hasInitialSpeed;
	
	public Ball(double x, double y, GamePanelManager gp) {
		super(x, y);
		alive = true;
		speedChanged = false;
		initSpeed(gp);
		centralX = x + (diameter / 2);
		centralY = y + (diameter / 2);
		
		hasInitialSpeed = false;
	}
	
	public Ball(double x, double y, GamePanelManager gp, double InitialSpeedX, double InitialSpeedY) {
		super(x, y);
		alive = true;
		speedChanged = false;
		this.InitialSpeedX = InitialSpeedX;
		this.InitialSpeedY = InitialSpeedY;		
		initSpeed(gp);
		centralX = x + (diameter / 2);
		centralY = y + (diameter / 2);
		
		hasInitialSpeed = true;
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.red);
		if(alive) {
			g.fillOval((int)x, (int)y, diameter, diameter);
		}
	}
	
	private void refreshCentralPoint() {
		centralX = x + (diameter / 2);
		centralY = y + (diameter / 2);
	}
	
	public void initSpeed(GamePanelManager gp) {
		
		//if the ball has the initialSpeed, we will just multiply it by the speedAdjust
		//if the ball does not have the initialSpeed, we will give it the random initialSpeed first, then multiply it
		if(hasInitialSpeed) {
			speedAdjust = 3.0;                      // if change this, it should also be changed in GamePanel.levelChange() method
			speedX = InitialSpeedX * speedAdjust;
			speedY = InitialSpeedY * speedAdjust;
		}else {
			RandomSpeed rs = new RandomSpeed();
			InitialSpeedX = rs.getUnitizedRandomX();
			InitialSpeedY = rs.getUnitizedRandomY();
			speedAdjust = 3.0;                      // if change this, it should also be changed in GamePanel.levelChange() method
			speedX = InitialSpeedX * speedAdjust;
			speedY = InitialSpeedY * speedAdjust;
			
			System.out.println("speedX: " + speedX);
			System.out.println("speedY: " + speedY);
			System.out.println("----------------------------------------");	
		}
		
		
	}
	
	/**
	 * Every time get 50(value) scores, speedAdjust will increase by 0.1, then set the new speed
	 * 
	 * If Change the 'value', It should be changed both in this method AND brickBroken method!! Do not forget this!!!
	 * 
	 * 'value' means the speed of the increasing of speed
	 * */
	public void speedUp() {
		if(GamePanelManager.score % 50 == 0 && GamePanelManager.score != 0 && !speedChanged) {
			speedChanged = true;
			speedAdjust += 0.3;
			speedX = InitialSpeedX * speedAdjust;
			speedY = InitialSpeedY * speedAdjust;
			
			System.out.println("Speed up!");
			System.out.println("speedX: " + speedX);
			System.out.println("speedY: " + speedY);
			System.out.println("----------------------------------------");
			
		}
	}
	
	public void hitPaddle(double paddleX, double paddleY, GamePanelManager gp) {
		if(gp.paddle.isPaddleSticky) {
			if(x >= paddleX - (Ball.diameter / 2) && x <= paddleX + Paddle.length - (Ball.diameter / 2)) {
				if(y + Ball.diameter - 1 >= paddleY) {
					isBallLaunched = false;
					System.out.println("Stick with paddle!");
				}
			}
			
		}else {      // if paddle is not sticky
			
			
			if(centralX >= paddleX && centralX < paddleX + (Paddle.length / 3)) {                             //left part of the paddle
				if(y + Ball.diameter >= paddleY) {
					if(y + Ball.diameter > paddleY) {
						y = paddleY - Ball.diameter - 5;
					}
					
					if(speedX <= 0) {
						double velocity;
						double a;
						double r;
						Random random = new Random();
						
						a = Math.atan(Math.abs(speedY) / Math.abs(speedX));
						r = (random.nextInt(21) + 10) * Math.PI / 180;
						velocity = Math.sin(a) * Math.abs(speedY) + Math.cos(a) * Math.abs(speedX);
						if(a - r <= Math.PI / 6) {
							r = 0;
						}
						speedY = -Math.abs(velocity * Math.sin(a - r));
						speedX = -Math.abs(velocity * Math.cos(a - r));
						
					}else {
						double velocity;
						double a;
						double b;
						double r;
						Random random = new Random();
						
						a = Math.atan(Math.abs(speedY) / Math.abs(speedX));
						r = (random.nextInt(21) + 10) * Math.PI / 180;
						velocity = Math.sin(a) * Math.abs(speedY) + Math.cos(a) * Math.abs(speedX);
						if(a + r >= Math.PI / 2) {
							b = a + (Math.PI / 2 - a) / 2;
							speedY = -Math.abs(velocity * Math.sin(b));
							speedX = Math.abs(velocity * Math.cos(b));							
						}else {
							speedY = -Math.abs(velocity * Math.sin(a + r));
							speedX = Math.abs(velocity * Math.cos(a + r));							
						}
						
					}
					
					
					System.out.println("Collide with paddle in left part!");
				}
			}else if(centralX >= paddleX + 2*GamePanelManager.UNIT_SIZE && centralX < paddleX + 2 * (Paddle.length / 3)) {   //middle part of the paddle
				if(y + Ball.diameter >= paddleY) {
					if(y + Ball.diameter > paddleY) {
						y = paddleY - Ball.diameter - 5;
					}
					speedY *= -1; 
					System.out.println("Collide with paddle in middle part!");
				}
			}else if(centralX >= paddleX + 4*GamePanelManager.UNIT_SIZE && centralX < paddleX + 3 * (Paddle.length / 3)) {   //right part of the paddle
				if(y + Ball.diameter >= paddleY) {
					if(y + Ball.diameter > paddleY) {
						y = paddleY - Ball.diameter - 5;
					}
					
					if(speedX >= 0) {
						double velocity;
						double a;
						double r;
						Random random = new Random();
						
						a = Math.atan(Math.abs(speedY) / Math.abs(speedX));
						r = (random.nextInt(21) + 10) * Math.PI / 180;
						velocity = Math.sin(a) * Math.abs(speedY) + Math.cos(a) * Math.abs(speedX);
						if(a - r <= Math.PI / 6) {
							r = 0;
						}
						speedY = -Math.abs(velocity * Math.sin(a - r));
						speedX = Math.abs(velocity * Math.cos(a - r));
						
					}else {
						double velocity;
						double a;
						double b;
						double r;
						Random random = new Random();
						
						a = Math.atan(Math.abs(speedY) / Math.abs(speedX));
						r = (random.nextInt(21) + 10) * Math.PI / 180;
						velocity = Math.sin(a) * Math.abs(speedY) + Math.cos(a) * Math.abs(speedX);
						if(a + r >= Math.PI / 2) {
							b = a + (((Math.PI / 2) - a) / 2);
							speedY = -Math.abs(velocity * Math.sin(b));
							speedX = -Math.abs(velocity * Math.cos(b));
						}else {
							speedY = -Math.abs(velocity * Math.sin(a + r));
							speedX = -Math.abs(velocity * Math.cos(a + r));							
						}
						
					}
					
					
					System.out.println("Collide with paddle in right part!");
				}
			}
			
			
		}
		
	}
	
	public void hitWall(GamePanelManager gp) {
		//left
		if(x <= 0) {
			if(x < 0) {
				x = 1;
			}
			speedX *= -1;
			System.out.println("Collide with left border!");
		}
		//right
		if(x + Ball.diameter >= GamePanelManager.SCREEN_WIDTH) {
			if(x + Ball.diameter > GamePanelManager.SCREEN_WIDTH) {
				x = GamePanelManager.SCREEN_WIDTH - Ball.diameter - 1;
			}
			speedX *= -1;
			System.out.println("Collide with right border!");
		}
		//top
		if(y <= GamePanelManager.INFO_HEIGHT + GamePanelManager.UNIT_SIZE) {
			if(y < GamePanelManager.INFO_HEIGHT + GamePanelManager.UNIT_SIZE) {
				y = GamePanelManager.INFO_HEIGHT + GamePanelManager.UNIT_SIZE + 1;
			}
			speedY *= -1;
			System.out.println("Collide with top border!");
		}
		//bottom
		if(y + Ball.diameter >= GamePanelManager.SCREEN_HEIGHT) {
			
			GamePanelManager.ballNumber--;
			isBallLaunched = false;
			
			if(GamePanelManager.ballList.size() == 1) {

				move(gp.paddle.x + (Paddle.length / 2) - centralX, gp.paddle.y - y - Ball.diameter - 1);
				
			}else if(GamePanelManager.ballList.size() > 1){
				
				for(int i = 0; i < GamePanelManager.ballList.size(); i++) {
					
					if(this == GamePanelManager.ballList.get(i)) {
						GameListener.tempIndexes.add(i);
					}
					
				}
			}
			
			
		}
	}
	
	public void hitBrick(Brick[] bricks) {
		for(int i = 0; i < bricks.length; i++) {
			if(!bricks[i].isBroken) {
				
				// That is important to refresh the central point every time!!!!! 
				// Otherwise, it is only refreshed one time when the Ball object was constructed!!!!!
				refreshCentralPoint();
				
				if(centralX >= bricks[i].x && centralX <= bricks[i].x + Brick.BRICK_WEIGHT - 1) {        // hit the top and bottom border of the brick
					if(speedY < 0) { // going upward
						if(y <= bricks[i].y + Brick.BRICK_HEIGHT - 1 && y >= bricks[i].y) {                                         // the top point
							bricks[i].brickBroken();
							speedY *= -1;
						}						
					}else {          // going downward
						if(y + Ball.diameter - 1 >= bricks[i].y && y + Ball.diameter - 1 <= bricks[i].y + Brick.BRICK_HEIGHT - 1) { // the bottom point
							bricks[i].brickBroken();
							speedY *= -1;
						}
					}
				}else if(centralY >= bricks[i].y && centralY <= bricks[i].y + Brick.BRICK_HEIGHT - 1) {  // hit the left and right border of the brick
					if(speedX > 0) { // towards right
						if(x + Ball.diameter - 1 >= bricks[i].x && x + Ball.diameter - 1 <= bricks[i].x + Brick.BRICK_WEIGHT - 1) { // the right point
							bricks[i].brickBroken();
							speedX *= -1;
						}
					}else {          // towards left
						if(x <= bricks[i].x + Brick.BRICK_WEIGHT - 1 && x >= bricks[i].x) {                                         // the left point
							bricks[i].brickBroken();
							speedX *= -1;
						}
					}
				}
				
			}
		}
		
		
	}

	// getter and setter -------------------------------------------------------------------------------------------------------
	
	public boolean isAlive() {
		return alive;
	}
	
	public void setAlive(boolean alive) {
		this.alive = alive;
	}
	
	public double getCentralX() {
		return centralX;
	}
	
	public void setCentralX(double centralX) {
		this.centralX = centralX;
	}
	
	public double getCentralY() {
		return centralY;
	}
	
	public void setCentralY(double centralY) {
		this.centralY = centralY;
	}
	
	public double getSpeedX() {
		return speedX;
	}
	
	public void setSpeedX(double speedX) {
		this.speedX = speedX;
	}
	
	public double getSpeedY() {
		return speedY;
	}
	
	public void setSpeedY(double speedY) {
		this.speedY = speedY;
	}
	
	public double getInitialSpeedX() {
		return InitialSpeedX;
	}
	
	public void setInitialSpeedX(double initialSpeedX) {
		InitialSpeedX = initialSpeedX;
	}
	
	public double getInitialSpeedY() {
		return InitialSpeedY;
	}
	
	public void setInitialSpeedY(double initialSpeedY) {
		InitialSpeedY = initialSpeedY;
	}
	
	public boolean isSpeedChanged() {
		return speedChanged;
	}
	
	public void setSpeedChanged(boolean speedChanged) {
		this.speedChanged = speedChanged;
	}
	
	public boolean isHasInitialSpeed() {
		return hasInitialSpeed;
	}
	
	public void setHasInitialSpeed(boolean hasInitialSpeed) {
		this.hasInitialSpeed = hasInitialSpeed;
	}
	


}
