package presentation;

import javax.swing.JFrame;

public class GameFrame extends JFrame {
	
	public GameFrame() {
		this.setTitle("Breakout");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		
		GamePanelManager gp = new GamePanelManager();
		this.add(gp);
		
		
		this.pack();
		this.setVisible(true);
		this.setLocationRelativeTo(null);
	}
}
