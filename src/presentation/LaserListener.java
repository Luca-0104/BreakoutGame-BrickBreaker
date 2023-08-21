package presentation;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import gameComponents.Laser;

public class LaserListener implements MouseListener {
	
	GamePanelManager gp;
	
	public LaserListener(GamePanelManager gp) {
		this.gp = gp;
	}
	
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(Laser.isLaserUnlocked) {
			Laser.isFireLaser = true;
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

}
