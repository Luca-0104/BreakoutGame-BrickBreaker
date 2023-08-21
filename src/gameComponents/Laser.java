package gameComponents;

import java.awt.Color;
import java.awt.Graphics;

import presentation.GamePanelManager;

public class Laser extends Abstract2DDrawing {
	GamePanelManager gp;
	public static boolean isLaserUnlocked = false;
	public static boolean isFireLaser = false;

	public Laser(int x, int y, GamePanelManager gp) {
		super(x, y);
		this.gp = gp;
	}

	/**
	 * when draw the laser, if there is a brick above, the brick can be broken
	 */
	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.green);
		
		Brick aimedBrick;
		double maxY;   // the y of the aimedBrick
		
		gp.paddle.centralX = gp.paddle.x + (Paddle.length / 2); // refresh the centralX of paddle
		int col = (int)(gp.paddle.centralX / Brick.BRICK_WEIGHT); // how many columns before the aimed brick, even the column is empty, it should also be counted
		double aimedX = col * Brick.BRICK_WEIGHT + 1; // the x of the aimed brick
		
		// store the bricks in the aimed column
		int count = 0;
		for(int i = 0; i < gp.level.bricks.length; i++) {
			if(!gp.level.bricks[i].isBroken && gp.level.bricks[i].x == aimedX) { //****!!!!问题！！！
				count++;
			}
		}
		System.out.println("this column left: " + count + "brick(s)");
		Brick[] colBricks = new Brick[count];  
		
		if(count != 0) { // when aimed column is not empty
			
			
			// pick out all the bricks in the aimed column
			int index = 0;
			for(int i = 0; i < gp.level.bricks.length; i++) {
				if(gp.level.bricks[i].x == aimedX && !gp.level.bricks[i].isBroken) {
					colBricks[index] = gp.level.bricks[i];
					index++;
				}
			}	
			
			// to find the brick at the bottom of this column
			aimedBrick = colBricks[0];
			maxY = colBricks[0].y;  
			for(int i = 1; i < colBricks.length; i++) {
				if(colBricks[i] != null) {
					if(colBricks[i].y >= maxY) {
						aimedBrick = colBricks[i];
						maxY = colBricks[i].y;
					}
				}
			}
			
			// draw the laser and break the brick
			gp.paddle.centralX = gp.paddle.x + (Paddle.length / 2);
			g.fillRect((int)(gp.paddle.centralX - 1), (int)(maxY + Brick.BRICK_HEIGHT), 25, (int)(gp.paddle.y - maxY));
			System.out.println("laser beam is drawn1(brick broken)");
			if(aimedBrick != null) {
				aimedBrick.isBroken = true;	
				GamePanelManager.brickNumber--;
				GamePanelManager.score += 10;
			}			
			
		}else {  // when aimed column is empty
			
			// draw the laser only, no brick is broken
			gp.paddle.centralX = gp.paddle.x + (Paddle.length / 2);
			g.fillRect((int)(gp.paddle.centralX - 1), GamePanelManager.INFO_HEIGHT + GamePanelManager.UNIT_SIZE, 25, GamePanelManager.SCREEN_HEIGHT - GamePanelManager.INFO_HEIGHT - 2*GamePanelManager.UNIT_SIZE);			
			System.out.println("laser beam is drawn2(no brick broken)");
			
		}

	}
	
	
	

}
