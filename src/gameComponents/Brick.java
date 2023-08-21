package gameComponents;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import presentation.GamePanelManager;

public class Brick extends Abstract2DDrawing {
	
	static final int BRICK_WEIGHT = GamePanelManager.UNIT_SIZE * 4;
	static final int BRICK_HEIGHT = GamePanelManager.UNIT_SIZE;
	boolean isBroken = false;
	public Bonus bonus;
	
	public Brick(double x, double y) {
		super(x, y);
		bonus = new Bonus(x + GamePanelManager.UNIT_SIZE + (GamePanelManager.UNIT_SIZE / 2), y);
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
//		g.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
		g.setColor(Color.CYAN);
		g.fill3DRect((int)x, (int)y, BRICK_WEIGHT, BRICK_HEIGHT, true);
	}
	
	public void brickBroken() {
		System.out.println("Brick broken!");	
		
		isBroken = true;
		GamePanelManager.brickNumber--;
		GamePanelManager.score += 10;
		
		// decide should the speed be increased
		if(GamePanelManager.score % 50 == 0 && GamePanelManager.score != 0) {
			for(Ball ball : GamePanelManager.ballList) {
				ball.setSpeedChanged(false);
			}
		}
		
		// decide whether or not drop the bonus
		int i = (new Random()).nextInt(2); 
//		int i = 0; // for test
		if(i == 0 && !GamePanelManager.isGameInBuff) {
			bonus.ifDrawBonus = true;
		}
		
		
	}


}
