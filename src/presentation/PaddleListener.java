package presentation;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import gameComponents.Ball;
import gameComponents.Paddle;

public class PaddleListener implements MouseMotionListener {
	
	private GamePanelManager gp;
	
	public PaddleListener(GamePanelManager gp) {
		this.gp = gp;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub	
		
		if(GamePanelManager.drawWhat == 'G' && gp.isStart) {
			if(e.getX() > (Paddle.length / 2) && e.getX() <  GamePanelManager.SCREEN_WIDTH - (Paddle.length / 2)) {
				
				
				//move the paddle
				gp.movePaddle(e.getX() - gp.paddle.x - (Paddle.length / 2));
						
				
				//move the ball
				if(!GamePanelManager.ballList.get(0).isBallLaunched) {  
					GamePanelManager.ballList.get(0).move(e.getX() - gp.ball.x - (Ball.diameter / 2), (gp.paddle.y - GamePanelManager.UNIT_SIZE + 1) - gp.ball.y);
				}
				
				
			}
			
		}
		gp.repaint();
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
