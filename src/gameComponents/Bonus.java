package gameComponents;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Arrays;
import java.util.Random;

import presentation.GamePanelManager;

public class Bonus extends Abstract2DDrawing {
	
	static int diameter = GamePanelManager.UNIT_SIZE;
	public static int bonusSpeed = 5;
	
	// 'M' -> multiBall, 'W' -> widePaddle, 'S' -> stickyPaddle, 'L' -> laser
	private char[] effects = {'W', 'S', 'L', 'M'};
	private char effect;
	private Color color;  // purple -> 'M', green -> 'W', yellow -> 'S', white -> 'L'
	public boolean ifDrawBonus = false;
	public static boolean isWLImplemented = false; // only the time limited effects should be signed as isImplemented
								     // namely, only 'W' and 'L'
	
	public Bonus(double x, double y) {
		super(x, y);
		generateRandomEffect();
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(this.color);
		g.fillOval((int)x, (int)y, Bonus.diameter, Bonus.diameter);

	}
	
	//参数最后可以简化！！！
	public void hitPaddle(Paddle paddle, GamePanelManager gp) {
		// first, decide if the bonus hit the bottom
		hitBottom();
		
		// then, if not hit the bottom, decide if it hit the paddle
		if(ifDrawBonus) {
			
			if(x >= paddle.x - (Bonus.diameter / 2) && x <= paddle.x + Paddle.length - 1 - (Bonus.diameter / 2)) {
				if(y + Bonus.diameter - 1 >= paddle.y) {
					ifDrawBonus = false;
					implementEffect(paddle, gp);
					if(effect == 'W' || effect == 'L') {
						isWLImplemented = true;  						
					}
					GamePanelManager.isGameInBuff = true;
					System.out.println("Get Bonus!");
				}
			}
			
		}
		
	}
	
	private void hitBottom() {
		if(y + Bonus.diameter - 1 >= GamePanelManager.SCREEN_HEIGHT) {
			ifDrawBonus = false;
		}
	}
	
	private void generateRandomEffect() {
		int i = (new Random()).nextInt(3);
		effect = effects[i];
		if(effect == 'M') {
			color = new Color(123, 150, 250);
			
		}else if(effect == 'W'){
			color = Color.green;
			
		}else if(effect == 'S') {
			color = Color.yellow;
			
		}else if(effect == 'L') {
			color = Color.white;
			
		}
	}
	
	
	//-----------------implement effects------------------
	
	private void implementEffect(Paddle paddle, GamePanelManager gp) {
		
		switch(effect) {
//		case 'M':
//			multiBall(gp);
//			break;
		case 'W':
			widePaddle(paddle);
			break;
		case 'S':
			stickyPaddle(gp);
			break;
		case 'L':
			laser(gp);
			break;
		}

		/* for test
		multiBall(gp);
		widePaddle(paddle);
		stickyPaddle(gp);
		laser(gp);
		*/		
	}
	
	
	// multiBall
	private void separateSpeed(double x, double y, double[] speeds) {
		double a;
		double b1, b2;
		double x1, y1;
		double x2, y2;
		double velocity;
		a = Math.atan(y / x);
		b1 = a + (Math.PI / 6);
		b2 = a - (Math.PI / 6);
		velocity = Math.abs(x * Math.cos(a)) + Math.abs(y * Math.sin(a));
		
		// if b is still in range of 0 ~ pi/2
		if(a > (Math.PI / 6) && a < (Math.PI / 6) * 2) {
			//keep the new speed x y has the same sign with the original one
			if(x > 0) {
				x1 = Math.abs(Math.abs(Math.cos(b1)) * velocity);
				x2 = Math.abs(Math.abs(Math.cos(b2)) * velocity);
			}else {
				x1 = -Math.abs(Math.abs(Math.cos(b1)) * velocity);
				x2 = -Math.abs(Math.abs(Math.cos(b2)) * velocity);				
			}
			speeds[0] = x1;
			speeds[2] = x2;
			
			if(y > 0) {
				y1 = Math.abs(Math.abs(Math.sin(b1)) * velocity);
				y2 = Math.abs(Math.abs(Math.sin(b2)) * velocity);							
			}else {
				y1 = -Math.abs(Math.abs(Math.sin(b1)) * velocity);
				y2 = -Math.abs(Math.abs(Math.sin(b2)) * velocity);											
			}
			speeds[1] = y1;
			speeds[3] = y2;
			
		// if b is larger than pi/2	
		}else if(a >= Math.PI/3) {
			if(x > 0) {
				x1 = -Math.abs(Math.abs(Math.cos(b1)) * velocity);
				x2 = Math.abs(Math.abs(Math.cos(b2)) * velocity);
			}else {
				x1 = Math.abs(Math.abs(Math.cos(b1)) * velocity);
				x2 = -Math.abs(Math.abs(Math.cos(b2)) * velocity);
			}
			speeds[0] = x1;
			speeds[2] = x2;
			
			if(y > 0) {
				y1 = Math.abs(Math.abs(Math.sin(b1)) * velocity);
				y2 = Math.abs(Math.abs(Math.sin(b2)) * velocity);
			}else {
				y1 = -Math.abs(Math.abs(Math.sin(b1)) * velocity);
				y2 = -Math.abs(Math.abs(Math.sin(b2)) * velocity);
			}
			speeds[1] = y1;
			speeds[3] = y2;
			
		// if b is less than 0	
		}else if(a <= Math.PI/6) {
			if(x > 0) {
				x1 = Math.abs(Math.abs(Math.cos(b1)) * velocity);
				x2 = Math.abs(Math.abs(Math.cos(b2)) * velocity);
			}else {
				x1 = -Math.abs(Math.abs(Math.cos(b1)) * velocity);
				x2 = -Math.abs(Math.abs(Math.cos(b2)) * velocity);
			}
			speeds[0] = x1;
			speeds[2] = x2;
			
			if(y > 0) {
				y1 = Math.abs(Math.abs(Math.sin(b1)) * velocity);
				y2 = -Math.abs(Math.abs(Math.sin(b2)) * velocity);
			}else {
				y1 = -Math.abs(Math.abs(Math.sin(b1)) * velocity);
				y2 = Math.abs(Math.abs(Math.sin(b2)) * velocity);
			}
			speeds[1] = y1;
			speeds[3] = y2;
		}
	}
	private void multiBall(GamePanelManager gp) {
		GamePanelManager.isGameInBuff = true;
		Ball[] tempBalls = new Ball[2];
		
		for(Ball ball : GamePanelManager.ballList) {
			double[] speeds = new double[4];
			separateSpeed(ball.getSpeedX(), ball.getSpeedY(), speeds);
			
			Ball ball1 = new Ball(ball.x, ball.y, gp, speeds[0], speeds[1]);
			ball1.isBallLaunched = true;
			tempBalls[0] = ball1;
			Ball ball2 = new Ball(ball.x, ball.y, gp, speeds[2], speeds[3]);
			ball2.isBallLaunched = true;
			tempBalls[1] = ball2;
			
			
			System.out.println(Arrays.toString(speeds));
			GamePanelManager.ballNumber += 2;
		}
		
		for(Ball ball : tempBalls) {
			GamePanelManager.ballList.add(ball);
		}
		
	}
	
	//widePaddle
	private void widePaddle(Paddle paddle) {
		if(!paddle.isDoubled) {
			Paddle.length *= 2;
			paddle.isDoubled = true;
			isWLImplemented = true;
			System.out.println("Peddle length doubled!");
		}
	}
	 //stickyPaddle
	private void stickyPaddle(GamePanelManager gp) {
		gp.paddle.isPaddleSticky = true;
		System.out.println("Ball is sticked!");
	}
	
	//laser
	private void laser(GamePanelManager gp) {
		Laser.isLaserUnlocked = true;
		isWLImplemented = true;
	}
	
	//-----------------cancel effects------------------
	
	//cancel WidePaddle
	public static void cancelW(Paddle paddle){	
		Paddle.length = 6*GamePanelManager.UNIT_SIZE;
		paddle.isDoubled = false;
		isWLImplemented = false;
		GamePanelManager.isGameInBuff = false;
		System.out.println("Peddle length renormal!");
	}
	
	//cancel Laser
	public static void cancelL() {
		Laser.isLaserUnlocked = false;
		Laser.isFireLaser = false;
		isWLImplemented = false;
		GamePanelManager.isGameInBuff = false;
		System.out.println("Laser is locked");
	}

	
	
}
