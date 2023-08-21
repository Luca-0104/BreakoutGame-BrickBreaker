package presentation;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JLabel;


public class GuidingListener implements MouseListener, MouseMotionListener {
	
	GamePanelManager gp;
	
	boolean xInPlay;
	boolean yInPlay;
	
	boolean xInHScores;
	boolean yInHScores; 
	
	boolean xInHelp;
	boolean yInHelp; 
	
	boolean xInExit;
	boolean yInExit;
	
	boolean xInBack;
	boolean yInBack;	
	
	public GuidingListener(GamePanelManager gp) {
		this.gp = gp;
	}
	
	private void decideMousePosition(MouseEvent e) {
		xInPlay =  e.getX() >= 25*GamePanelManager.UNIT_SIZE && e.getX() <= 32*GamePanelManager.UNIT_SIZE;
		yInPlay =  e.getY() >= GamePanelManager.INFO_HEIGHT + 12*GamePanelManager.UNIT_SIZE && e.getY() <= GamePanelManager.INFO_HEIGHT + 15*GamePanelManager.UNIT_SIZE;
	
		xInHScores = e.getX() >= 22*GamePanelManager.UNIT_SIZE && e.getX() <= 36*GamePanelManager.UNIT_SIZE;
	    yInHScores = e.getY() >= GamePanelManager.INFO_HEIGHT + 16*GamePanelManager.UNIT_SIZE && e.getY() <= GamePanelManager.INFO_HEIGHT + 19*GamePanelManager.UNIT_SIZE;
	
	    xInHelp = e.getX() >= 25*GamePanelManager.UNIT_SIZE && e.getX() <= 32*GamePanelManager.UNIT_SIZE;
	    yInHelp = e.getY() >= GamePanelManager.INFO_HEIGHT + 20*GamePanelManager.UNIT_SIZE && e.getY() <= GamePanelManager.INFO_HEIGHT + 23*GamePanelManager.UNIT_SIZE;
	
	    xInExit = e.getX() >= 26*GamePanelManager.UNIT_SIZE && e.getX() <= 31*GamePanelManager.UNIT_SIZE;
	    yInExit = e.getY() >= GamePanelManager.INFO_HEIGHT + 24*GamePanelManager.UNIT_SIZE && e.getY() <= GamePanelManager.INFO_HEIGHT + 27*GamePanelManager.UNIT_SIZE;
	    
	    xInBack = e.getX() >= GamePanelManager.UNIT_SIZE && e.getX() <= 6*GamePanelManager.UNIT_SIZE;
	    yInBack = e.getY() >= GamePanelManager.INFO_HEIGHT + 25*GamePanelManager.UNIT_SIZE && e.getY() <= GamePanelManager.INFO_HEIGHT + 28*GamePanelManager.UNIT_SIZE;
	}
	

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
		// initialize the booleans first
		decideMousePosition(e);
		
		// in MENU
		if(GamePanelManager.drawWhat == 'M') {
			// click on PLAY button
			if(xInPlay && yInPlay) {
					gp.isStart = true;
					GamePanelManager.drawWhat = 'G';
					System.out.println("Clicked on PLAY button");
			}
			
			// click on HIGH SCORES button
			if(xInHScores && yInHScores) {
					GamePanelManager.drawWhat = 'R';
					System.out.println("Clicked on HIGH SCORES button");
			}
			
			// click on the HELP
			if(xInHelp && yInHelp) {
					GamePanelManager.drawWhat = 'H';
					System.out.println("Clicked on HELP button");
			}
			
			// click on EXIT button
			if(xInExit && yInExit) {
					System.exit(0);
					System.out.println("Clicked on Exit button");
			}
			
			
		}
		
		// in HIGH SCORE
		if(GamePanelManager.drawWhat == 'R') {
			
			// click on Back button
			if(xInBack && yInBack) {
					GamePanelManager.drawWhat = 'M';
					System.out.println("Clicked on Back butotn");
			}
			
		}
		
		// in HELP
		if(GamePanelManager.drawWhat == 'H') {
			
			// click on Back button
			if(xInBack && yInBack) {
					GamePanelManager.drawWhat = 'M';
					System.out.println("Clicked on Back button");
			}
			
		}
		

		gp.repaint();
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}


	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
		// initialize the booleans first
		decideMousePosition(e);
		
		// in MENU
		if(GamePanelManager.drawWhat == 'M') {
			// in area of PLAY button
			if(xInPlay && yInPlay) {
					gp.isPlayFocused = true;
					System.out.println("in area of PLAY button");
			}else {
				gp.isPlayFocused = false;
			}
			
			// in area of HIGH SCORES button
			if(xInHScores && yInHScores) {
					gp.isHighScoresFocused = true;
					System.out.println("in area of HIGH SCORES button");
			}else {
				gp.isHighScoresFocused = false;
			}
			
			// in area of the HELP
			if(xInHelp && yInHelp) {
					gp.isHelpFocused = true;
					System.out.println("in area of the HELP button");
			}else {
				gp.isHelpFocused = false;				
			}
			
			// in area of EXIT button
			if(xInExit && yInExit) {
					gp.isExitFocused = true;
					System.out.println("in area of EXIT button");
			}else {
				gp.isExitFocused = false;				
			}
		}
		
		// in HIGH SCORES
		if(GamePanelManager.drawWhat == 'R') {
			// in area of Back
			if(xInBack && yInBack) {
				gp.isBackFocused = true;
				System.out.println("in area of Back button");
			}else {
				gp.isBackFocused = false;
			}
		}
		
		// in HELP
		if(GamePanelManager.drawWhat == 'H') {
			// in area of Back
			if(xInBack && yInBack) {
				gp.isBackFocused = true;
				System.out.println("in area of Back button");
			}else {
				gp.isBackFocused = false;
			}
		}
		
		gp.repaint();
		
	}
	
	
	

}
