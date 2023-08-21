package gameComponents;

import java.awt.Graphics;

import presentation.GamePanelManager;

public class Level {
	
	public Brick[] bricks;
	
	public Level(char level) {	
		
		// different methods to load bricks for different levels
		switch(level) {
		case '1':
			loadBricks_level1();
			break;
		case '2':
			loadBricks_level2();
			break;
		case '3':
			loadBricks_level3();
			break;
		case '4':
			loadBricks_level4();
			break;
		case '5':
			loadBricks_level5();
			break;
		}
		
	}
	
	//big rectangle
	private void loadBricks_level5() {
		/* for test
		int row = 1;
		int column = 13;
		bricks = new Brick[row*column];
		int a = 0;
		for(int i = 0; i < row; i++) {
			for(int j = 0; j < column; j++) {
				bricks[a] = new Brick(1 + j * Brick.BRICK_WEIGHT, GamePanelManager.INFO_HEIGHT + GamePanelManager.UNIT_SIZE * 3 + (1 + i * Brick.BRICK_HEIGHT));
				a++;
			}
		}
		*/
		
		
		int row = 8;
		int column = 15;
		bricks = new Brick[row*column];
		int a = 0;
		for(int i = 0; i < row; i++) {
			for(int j = 0; j < column; j++) {
				bricks[a] = new Brick(1 + j * Brick.BRICK_WEIGHT, GamePanelManager.INFO_HEIGHT + GamePanelManager.UNIT_SIZE * 3 +(1 + i * Brick.BRICK_HEIGHT));				
				a++;
			}
		}
		
		
	}
	
	//diamond
	private void loadBricks_level4() {
		/* for test
		int row = 1;
		int column = 14;
		bricks = new Brick[row*column];
		int a = 0;
		for(int i = 0; i < row; i++) {
			for(int j = 0; j < column; j++) {
				bricks[a] = new Brick(1 + j * Brick.BRICK_WEIGHT, GamePanelManager.INFO_HEIGHT + GamePanelManager.UNIT_SIZE * 3 + (1 + i * Brick.BRICK_HEIGHT));
				a++;
			}
		}
		*/
		
		
		int row = 8;
		int row2 = 7;
		bricks = new Brick[(1+(2*row - 1))*row/2 + (1+(2*row2 - 1))*row2/2];
		int a = 0;
		
		for(int i = 0; i < row; i++) {
			for(int j = 0; j < (2 * i) + 1; j++) {
				bricks[a] = new Brick((1 + (GamePanelManager.SCREEN_WIDTH / 2) - 2*GamePanelManager.UNIT_SIZE) - i*Brick.BRICK_WEIGHT + j*Brick.BRICK_WEIGHT, GamePanelManager.INFO_HEIGHT + GamePanelManager.UNIT_SIZE * 3 + i * Brick.BRICK_HEIGHT);
				a++;
			}
		}
		for(int i = row2; i > 0; i--) {
			int i0 = row2-i;
			for(int j = 0; j < (2 * i0) + 1; j++) {
				bricks[a] = new Brick((1 + (GamePanelManager.SCREEN_WIDTH / 2) - 2*GamePanelManager.UNIT_SIZE) - i0*Brick.BRICK_WEIGHT + j*Brick.BRICK_WEIGHT, GamePanelManager.INFO_HEIGHT + GamePanelManager.UNIT_SIZE * 3 + (row + row2 - 1)*Brick.BRICK_HEIGHT - i0 * Brick.BRICK_HEIGHT);
				a++;				
			}
		}
		
		
	}
	
	// uneven big rectangle
	private void loadBricks_level3() {
		/* for test
		int row = 1;
		int column = 15;
		bricks = new Brick[row*column];
		int a = 0;
		for(int i = 0; i < row; i++) {
			for(int j = 0; j < column; j++) {
				bricks[a] = new Brick(1 + j * Brick.BRICK_WEIGHT, GamePanel.INFO_HEIGHT + GamePanel.UNIT_SIZE * 3 + i * Brick.BRICK_HEIGHT);
				a++;
			}
		}
		*/
		
		
		int row = 4;
		int column = 8;
		int column2 = 7;
		bricks = new Brick[row*column + row*column2];
		int a = 0;
		
		for(int i = 0; i < row; i++) {
			for(int j = 0; j < column; j++) {
				bricks[a] = new Brick((1 + j * (Brick.BRICK_WEIGHT * 2)), GamePanelManager.INFO_HEIGHT + GamePanelManager.UNIT_SIZE * 3 + i * (Brick.BRICK_HEIGHT * 2));
				a++;
			}
		}
		
		for(int i = 0; i < row; i++) {
			for(int j = 0; j < column2; j++) {
				bricks[a] = new Brick((1 + Brick.BRICK_WEIGHT + j * (Brick.BRICK_WEIGHT * 2)), GamePanelManager.INFO_HEIGHT + GamePanelManager.UNIT_SIZE * 4 + i * (Brick.BRICK_HEIGHT * 2));
				a++;
			}
		}
		
	}
	
	// 2077
	private void loadBricks_level1() {
		/* for test
		int row = 2;
		int column = 13;
		bricks = new Brick[row*column];
		int a = 0;
		for(int i = 0; i < row; i++) {
			for(int j = 0; j < column; j++) {
				bricks[a] = new Brick(1 + j * Brick.BRICK_WEIGHT, GamePanelManager.INFO_HEIGHT + GamePanelManager.UNIT_SIZE * 3 + (1 + i * Brick.BRICK_HEIGHT));
				a++;
			}
		}
		*/
		
		int row;
		int column;
		int a = 0;
		bricks = new Brick[111];
		
		row = 3;
		column = 15;
		
		for(int i = 0; i < row; i++) {
			for(int j = 0; j < column; j++) {
				if(j != 3 && j != 7 && j != 11) {
					bricks[a] = new Brick(1 + j * Brick.BRICK_WEIGHT, GamePanelManager.INFO_HEIGHT + GamePanelManager.UNIT_SIZE * 3 +(1 + i * Brick.BRICK_HEIGHT));				
					a++;					
				}
			}
		}
		
		for(int i = 0; i < row; i++) {
			for(int j = 0; j < column; j++) {
				if(j != 0 && j != 1 && j != 3 && j != 5 && j != 7 && j != 8 && j != 9 && j != 11 && j != 12 && j != 13) {
					bricks[a] = new Brick(1 + j * Brick.BRICK_WEIGHT, GamePanelManager.INFO_HEIGHT + GamePanelManager.UNIT_SIZE * 6 +(1 + i * Brick.BRICK_HEIGHT));				
					a++;					
				}
			}
		}
		
		for(int i = 0; i < row; i++) {
			for(int j = 0; j < column; j++) {
				if(j != 3 && j != 5 && j != 7 && j != 8 && j != 9 && j != 11 && j != 12 && j != 13) {
					bricks[a] = new Brick(1 + j * Brick.BRICK_WEIGHT, GamePanelManager.INFO_HEIGHT + GamePanelManager.UNIT_SIZE * 9 +(1 + i * Brick.BRICK_HEIGHT));				
					a++;					
				}
			}
		}
		
		for(int i = 0; i < row; i++) {
			for(int j = 0; j < column; j++) {
				if(j != 1 && j != 2 && j != 3 && j != 5 && j != 7 && j != 8 && j != 9 && j != 11 && j != 12 && j != 13) {
					bricks[a] = new Brick(1 + j * Brick.BRICK_WEIGHT, GamePanelManager.INFO_HEIGHT + GamePanelManager.UNIT_SIZE * 12 +(1 + i * Brick.BRICK_HEIGHT));				
					a++;					
				}
			}
		}
		
		for(int i = 0; i < row; i++) {
			for(int j = 0; j < column; j++) {
				if(j != 3 && j != 7 && j != 8 && j != 9 && j != 11 && j != 12 && j != 13) {
					bricks[a] = new Brick(1 + j * Brick.BRICK_WEIGHT, GamePanelManager.INFO_HEIGHT + GamePanelManager.UNIT_SIZE * 15 +(1 + i * Brick.BRICK_HEIGHT));				
					a++;					
				}
			}
		}
		
		
	}
	
	//BDIC
	private void loadBricks_level2() {
		/* for test
		int row = 2;
		int column = 14;
		bricks = new Brick[row*column];
		int a = 0;
		for(int i = 0; i < row; i++) {
			for(int j = 0; j < column; j++) {
				bricks[a] = new Brick(1 + j * Brick.BRICK_WEIGHT, GamePanelManager.INFO_HEIGHT + GamePanelManager.UNIT_SIZE * 3 + (1 + i * Brick.BRICK_HEIGHT));
				a++;
			}
		}
		*/
		
		
		int row;
		int column;
		int a = 0;
		bricks = new Brick[122];
		
		row = 3;
		column = 15;
		
		for(int i = 0; i < row; i++) {
			for(int j = 0; j < column; j++) {
				if(j != 3 && j != 7 && j != 11 && (i != 0 || j != 2) && (i != 0 || j != 6) && (i != 0 || j != 12)) {
					bricks[a] = new Brick(1 + j * Brick.BRICK_WEIGHT, GamePanelManager.INFO_HEIGHT + GamePanelManager.UNIT_SIZE * 3 +(1 + i * Brick.BRICK_HEIGHT));				
					a++;					
				}
			}
		}
		
		for(int i = 0; i < row; i++) {
			for(int j = 0; j < column; j++) {
				if(j != 1 && j != 3 && j != 5 && j != 7 && j != 8 && j != 10 && j != 11 && j != 13 && j != 14) {
					bricks[a] = new Brick(1 + j * Brick.BRICK_WEIGHT, GamePanelManager.INFO_HEIGHT + GamePanelManager.UNIT_SIZE * 6 +(1 + i * Brick.BRICK_HEIGHT));				
					a++;					
				}
			}
		}
		
		for(int i = 0; i < row; i++) {
			for(int j = 0; j < column; j++) {
				if(j != 3 && j != 5 && j != 7 && j != 8 && j != 10 && j != 11 && j != 13 && j != 14 && (i != 1 || j != 2)) {
					bricks[a] = new Brick(1 + j * Brick.BRICK_WEIGHT, GamePanelManager.INFO_HEIGHT + GamePanelManager.UNIT_SIZE * 9 +(1 + i * Brick.BRICK_HEIGHT));				
					a++;					
				}
			}
		}
		
		for(int i = 0; i < row; i++) {
			for(int j = 0; j < column; j++) {
				if(j != 1 && j != 3 && j != 5 && j != 7 && j != 8 && j != 10 && j != 11 && j != 13 && j != 14) {
					bricks[a] = new Brick(1 + j * Brick.BRICK_WEIGHT, GamePanelManager.INFO_HEIGHT + GamePanelManager.UNIT_SIZE * 12 +(1 + i * Brick.BRICK_HEIGHT));				
					a++;					
				}
			}
		}
		
		for(int i = 0; i < row; i++) {
			for(int j = 0; j < column; j++) {
				if(j != 3 && j != 7 && j != 11 && (i != 2 || j != 2) && (i != 2 || j != 6) && (i != 2 || j != 12)) {
					bricks[a] = new Brick(1 + j * Brick.BRICK_WEIGHT, GamePanelManager.INFO_HEIGHT + GamePanelManager.UNIT_SIZE * 15 +(1 + i * Brick.BRICK_HEIGHT));				
					a++;					
				}
			}
		}
		
	}
	
	public void nextLevel(GamePanelManager gp) {
		if(GamePanelManager.brickNumber == 0 && !gp.isLevelChanged) {
			
			if(gp.getLevelTo() == '1') {
				gp.setLevelTo('2');
				gp.isLevelChanged = true;
				
			}else if(gp.getLevelTo() == '2') {
				gp.setLevelTo('3');
				gp.isLevelChanged = true;
				
			}else if(gp.getLevelTo() == '3') {
				gp.setLevelTo('4');
				gp.isLevelChanged = true;
				
			}else if(gp.getLevelTo() == '4') {
				gp.setLevelTo('5');
				gp.isLevelChanged = true;
				
			}else {
				gp.gameWin();
				
			}
			
			for(Ball ball : GamePanelManager.ballList) {
				ball.isBallLaunched = false;
			}
			
			
		}
	}
	
	public void draw(Graphics g) {
		for(int i = 0; i < bricks.length; i++) {
			if(!bricks[i].isBroken) {
				bricks[i].draw(g);
			}
		}
	}
	
	

}
