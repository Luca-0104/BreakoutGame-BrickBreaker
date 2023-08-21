package scoreManagement;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import presentation.GamePanelManager;

public class ScoreManager {

		static Player[] players = new Player[10];
		
		/**
		 * @param reset Whether or not to clean the ScoreBoard into default informations
		 * */
		public ScoreManager(boolean reset) {
			if(reset) {
				for(int i = 0; i < players.length; i++) {
					players[i] = new Player("PLAYER", 0);
				}
				
				// refresh the Scores file
				writeInScores();
				
			}else {
				// refresh the array(player) according to the file
				readIntoArray();
				
				// decide whether or not should the file be refreshed
				if(!players[0].getName().equals("PLAYER")) {
					
					// Initialize the array 
					for(int i = 0; i < players.length; i++) {
						players[i] = new Player("PLAYER", 0);
					}
					
				}else {
					// Initialize the array 
					for(int i = 0; i < players.length; i++) {
						players[i] = new Player("PLAYER", 0);
					}
					
					// refresh the Scores file
					writeInScores();
				}
			}
			
		}
		
		
		/**
		 * write the scores from array (players) into the file to refresh the file
		 * 
		 * just for the method insertPlayer and the constructor to use
		 */
		private void writeInScores() {
			
			try(DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("Scores.txt")))){
				
				for(int i = 0; i < players.length; i++) {
					dos.writeUTF(players[i].getName());
					dos.writeInt(players[i].getScore());				
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		/**
		 * read the scores from the file into the array to refresh the array (players)
		 * 
		 * just for methods insertPlayer and showScores to use
		 */
		private void readIntoArray() {
			
			try {
				
				DataInputStream dis = new DataInputStream(new BufferedInputStream(new FileInputStream("Scores.txt")));
				
				for(int i = 0; i < players.length; i++) {
					players[i] = new Player(dis.readUTF(), dis.readInt());
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		public void insertPlayer(Player p) {	
			// refresh the array 
			readIntoArray();
			
			// insert the player into proper position in an descending order
			for(int i = 0; i < players.length; i++) {
				
				if(p.getScore() >= players[i].getScore()) {
					
					int j;
					for(j = players.length - 1; j > i; j--) {
						players[j] = players[j - 1];
					}
					players[j] = p;
					break;
					
				}
			}
			
			// refresh the Scores file
			writeInScores();
		}
		
		public void showScores(Graphics g) {
			// refresh the array
			readIntoArray();
			
			// draw informations out
			for(int i = 0; i < players.length; i++) {
				g.setColor(Color.white);
				g.setFont(new Font("Arial", Font.PLAIN, 30));
				if(i == 9) {
					g.drawString("NO." + (i + 1) + "   " + players[i].getName(), 8*GamePanelManager.UNIT_SIZE, 70*(i+1) + 80);
					g.drawString("-------------------------------------------------------------", 19*GamePanelManager.UNIT_SIZE, 70*(i+1) + 80);
					g.drawString("" + players[i].getScore(), 47*GamePanelManager.UNIT_SIZE, 70*(i+1) + 80);
				}else {
					g.drawString("NO." + (i + 1) + "   " + players[i].getName(), 8*GamePanelManager.UNIT_SIZE, 70*(i+1) + 80);
					g.drawString("-------------------------------------------------------------", 19*GamePanelManager.UNIT_SIZE, 70*(i+1) + 80);
					g.drawString("" + players[i].getScore(), 47*GamePanelManager.UNIT_SIZE, 70*(i+1) + 80);
				}
			}
			
		}
		
		
		
}
