package gameComponents;

import java.awt.Color;
import java.awt.Graphics;

import presentation.GamePanelManager;

public class Paddle extends Abstract2DDrawing {
	
	public static int length = GamePanelManager.UNIT_SIZE * 6;
	double centralX;
	public boolean isDoubled = false;
	public boolean isPaddleSticky = false;
	
	public Paddle(int x, int y) {
		super(x, y);
		centralX = x + (Paddle.length / 2);
	}
	
	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.green);
		g.fillRect((int)x, (int)y, length, GamePanelManager.UNIT_SIZE);
	}


}
