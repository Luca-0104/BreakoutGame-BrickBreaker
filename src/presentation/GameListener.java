package presentation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.Timer;

import gameComponents.Ball;
import gameComponents.Bonus;

public class GameListener implements ActionListener, KeyListener {
	
	private GamePanelManager gp;
	int countKey;
	public static ArrayList<Integer> tempIndexes = new ArrayList<>(); //to record the index of the ball that should be remove from the ballList
	Timer timer = new Timer(GamePanelManager.DELAY, this);
	
	public GameListener(GamePanelManager gp) {
		this.gp = gp;
		countKey = 0;
		timer.start();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(GamePanelManager.drawWhat == 'G' && gp.isStart) {
			// bonus drops
			gp.moveBonus(Bonus.bonusSpeed); // 5 means speed
			
			// bonus hits the paddle
			for(int i = 0; i < gp.level.bricks.length; i++) {
				gp.level.bricks[i].bonus.hitPaddle(gp.paddle, gp);
			}
			
			// check are there any bonuses run out of the time so that should be canceled 
			for(int i = 0; i < gp.level.bricks.length; i++) {
				if(GamePanelManager.isGameInBuff && Bonus.isWLImplemented) {
					gp.startCountTime = true;
				}
			}
			
			//10 seconds after getting the buff, the buff will be cancel
			if(gp.startCountTime) {
				gp.timeCounter += GamePanelManager.DELAY;
				if(gp.timeCounter >= 10 * 1000) {     
					Bonus.cancelW(gp.paddle);
					Bonus.cancelL();
					GamePanelManager.isGameInBuff = false;
					gp.startCountTime = false;
					gp.timeCounter = 0;
				}
			}
				
			
			// move the ball and check the statue
			if(GamePanelManager.ballNumber > 0) {
				
				// operate on each ball
				for(Ball ball : GamePanelManager.ballList) {
					if(ball.isBallLaunched) {
						// ball starts moving
						ball.move(ball.getSpeedX(), ball.getSpeedY());
						
						// ball speed up as getting scores
						ball.speedUp();
						
						// rebound (four boundaries)
						ball.hitWall(gp);
						
						// rebound (paddle)
						ball.hitPaddle(gp.paddle.x, gp.paddle.y, gp);
						
						// rebound (bricks)
						ball.hitBrick(gp.level.bricks);						
					}
				}
				
				// to delete the balls, which fall in to the screen bottom
				for(int i : tempIndexes) {
					if(GamePanelManager.ballList.size() >= i + 1) {
						GamePanelManager.ballList.remove(i);
					}
				}
				
				tempIndexes = new ArrayList<>();
				
				// change level
				gp.level.nextLevel(gp);
				
			}else if(GamePanelManager.ballNumber <= 0){
				// run out of balls --> game over
				gp.gameOver();
			}
		
		}
		gp.repaint();
			
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(GamePanelManager.drawWhat == 'G' && gp.isStart) {
			
			if(e.getKeyCode() == KeyEvent.VK_SPACE) {
				
				// initialize the speed of each ball and launch the ball
				for(Ball ball : GamePanelManager.ballList) {
					if(!ball.isBallLaunched) {
						
						if(gp.paddle.isPaddleSticky) {
							// every time before launched, change the ball to another random speed direction
							ball.initSpeed(gp);
							gp.paddle.isPaddleSticky = false;
							GamePanelManager.isGameInBuff = false;
						}else {
							ball.initSpeed(gp);
						}
						
						ball.isBallLaunched = true;
						
					}
				}
							
				
			}
			
		}
		
		// ***********************************A Salute to Contra*********************************** 
		if(GamePanelManager.drawWhat == 'M') {
			if(e.getKeyCode() == KeyEvent.VK_UP) {
				gp.salute30LivesCode += "U";
				countKey++;
			}
			if(e.getKeyCode() == KeyEvent.VK_DOWN) {
				gp.salute30LivesCode += "D";
				countKey++;
			}
			if(e.getKeyCode() == KeyEvent.VK_LEFT) {
				gp.salute30LivesCode += "L";
				countKey++;
				
			}
			if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
				gp.salute30LivesCode += "R";
				countKey++;
				
			}
			if(e.getKeyCode() == KeyEvent.VK_B) {
				gp.salute30LivesCode += "B";
				countKey++;
				
			}
			if(e.getKeyCode() == KeyEvent.VK_A) {
				gp.salute30LivesCode += "A";
				countKey++;
				if(gp.salute30LivesCode.equals("UUDDLRLRBABA")) {
					System.out.println("******************** 30 lives ********************");
					countKey = 0;
					GamePanelManager.ballNumber = 30;
					gp.isSalute = true;					
				}
				if(countKey > 12) {
					gp.salute30LivesCode = "";
					countKey = 0;
				}
			}
			if(e.getKeyCode() != KeyEvent.VK_UP && e.getKeyCode() != KeyEvent.VK_DOWN && e.getKeyCode() != KeyEvent.VK_LEFT && e.getKeyCode() != KeyEvent.VK_RIGHT && e.getKeyCode() != KeyEvent.VK_B && e.getKeyCode() != KeyEvent.VK_A) {
				gp.salute30LivesCode = "";
			}
		}
		// **************************************************************************************** 
		
		
		
		gp.repaint();
	}
	
	@Override
	public void keyTyped(KeyEvent e) {}
	@Override
	public void keyReleased(KeyEvent e) {}

}
