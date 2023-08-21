package gameComponents;

import java.awt.Graphics;

public abstract class Abstract2DDrawing {
	
	public double x;
	public double y;
	
	public Abstract2DDrawing(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public void move(double x, double y) {
		this.x += x;
		this.y += y;
	}
	
	public abstract void draw(Graphics g);

}