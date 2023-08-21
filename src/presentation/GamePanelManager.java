package presentation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import gameComponents.*;
import scoreManagement.*;

public class GamePanelManager extends JPanel {
	public static final int SCREEN_WIDTH = 1500;  //whole window area
	public static final int SCREEN_HEIGHT= 825;   //whole window area
	public static final int INFO_HEIGHT= 100;     //info area, from the top of the window
	public static final int UNIT_SIZE = 25;
	static final int GAME_UNIT = (SCREEN_WIDTH * SCREEN_HEIGHT) / UNIT_SIZE;
	static final int DELAY = 15;
	private String info;
	public static int ballNumber;
	public static int score;
	public static int brickNumber;
	static char drawWhat;  // 'M' -> menu, 'G' -> game, 'R' -> rank, 'H' -> help
	private ScoreManager scoreManager;
	public Level level;
	public Paddle paddle;
	public Ball ball;
	public static ArrayList<Ball> ballList = new ArrayList<>();
	public static int countFallBalls = 0;
	public Laser laser;
	private char levelTo = '1';
	public static boolean isGameInBuff = false;  //To keep only one buff is existing at same time
	public boolean startCountTime = false;
	public long timeCounter = 0;
	public boolean isLevelChanged = false;
	boolean isStart = false;
	boolean isPlayFocused = false;
	boolean isHighScoresFocused = false;
	boolean isHelpFocused = false;
	boolean isExitFocused = false;
	boolean isBackFocused = false;
	String salute30LivesCode;
	boolean isSalute = false;
	
	
	
	
	
	public GamePanelManager() {
		this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
		this.setBackground(Color.black);
		this.setFocusable(true);
		this.setLayout(null);
		this.addMouseMotionListener(new PaddleListener(this));
		this.addKeyListener(new GameListener(this));
		this.addMouseListener(new GuidingListener(this));
		this.addMouseMotionListener(new GuidingListener(this));
		this.addMouseListener(new LaserListener(this));
		init();
	}
	
	private void init() {
		level = new Level(levelTo);
		ballNumber = 3;
		score = 0;
		brickNumber = level.bricks.length;
		paddle = new Paddle((SCREEN_WIDTH - Paddle.length) / 2, SCREEN_HEIGHT - UNIT_SIZE);
		ball = new Ball(paddle.x + (Paddle.length / 2) - (Ball.diameter / 2), paddle.y - UNIT_SIZE, this);
		ballList.add(ball);
		drawWhat = 'M';  // initial status is 'M', which means menu
		scoreManager = new ScoreManager(false);  // false means don't reset scores
		salute30LivesCode = "";
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		chooseScreen(g);
	}
	
	private void chooseScreen(Graphics g) {
		switch(drawWhat) {
		case 'M':
			drawMenu(g);
			break;
		case 'G':
			drawGame(g);
			break;
		case 'R':
			drawRank(g);
			break;
		case 'H':
			drawHelp(g);
			break;
		}
	}
	
	/**
	 * decide the color of the buttons in Menu
	 * */
	private void decideColor(Graphics g, boolean isFocused) {
		if(!isFocused) {
			g.setColor(Color.green);
		}else {
			g.setColor(Color.red);
		}
	}
	
	private void drawMenu(Graphics g) {
		// first thing is cancel the focuses on the buttons, which are not on this page
		isBackFocused = false;
		
		// draw background
		g.setColor(Color.black);
		g.fillRect(0, 0, GamePanelManager.SCREEN_WIDTH, GamePanelManager.SCREEN_HEIGHT + GamePanelManager.INFO_HEIGHT + GamePanelManager.UNIT_SIZE);
		
		// draw title
		g.setColor(Color.green);
		g.setFont(new Font("Ink Free", Font.BOLD, 100));
		g.drawString("Welcome to Break Out !!!", 165, 150);
		
		// draw buttons with proper color
		g.setFont(new Font("Arial", Font.BOLD, 50));
		decideColor(g, isPlayFocused);
		g.drawString("PLAY", GamePanelManager.SCREEN_WIDTH / 2 - 100,  450);
		decideColor(g, isHighScoresFocused);		
		g.drawString("HIGH SCORES", GamePanelManager.SCREEN_WIDTH / 2 - 200,  550);
		decideColor(g, isHelpFocused);		
		g.drawString("HELP", GamePanelManager.SCREEN_WIDTH / 2 - 100, 650);
		decideColor(g, isExitFocused);		
		g.drawString("EXIT", GamePanelManager.SCREEN_WIDTH / 2 - 90, 750);
		
//		drawGrid(g); // 最后删除
	}
	
	private void drawGame(Graphics g) {
		// first thing is cancel the focuses on the buttons, which are not on this page
		isPlayFocused = false;
		isHighScoresFocused = false;
		isHelpFocused = false;
		isExitFocused = false;
		isBackFocused = false;
		
		
		//add a refreshed information bar 
		makeInfoBar(g);
				
		// bar between info and game screen
		g.setColor(Color.gray);
		g.fillRect(0, INFO_HEIGHT, SCREEN_WIDTH, UNIT_SIZE);
		
		// draw the background grids
		drawGrid(g);
		
		// draw bricks for the proper level
		levelChange();
		level.draw(g);
		
		// draw the ball
		for(Ball ball : ballList) {
			ball.draw(g);
		}
		
		// draw the paddle
		paddle.draw(g);		
		
		// draw bonus ball
		for(int i = 0; i < level.bricks.length; i++) {
			if(level.bricks[i].bonus.ifDrawBonus) {
				level.bricks[i].bonus.draw(g);
			}
		}
		
		// draw laser
		if(Laser.isLaserUnlocked && Laser.isFireLaser) {
			laser = new Laser(0, 0, this);
			laser.draw(g);
			Laser.isFireLaser = false;
		}
		
	}
	
	private void drawRank(Graphics g) {
		// first thing is cancel the focuses on the buttons, which are not on this page
		isPlayFocused = false;
		isHighScoresFocused = false;
		isHelpFocused = false;
		isExitFocused = false;
		
		// draw the screen background
		g.setColor(Color.black);
		g.fillRect(0, 0, GamePanelManager.SCREEN_WIDTH, GamePanelManager.SCREEN_HEIGHT + GamePanelManager.INFO_HEIGHT + GamePanelManager.UNIT_SIZE);
		
		// draw the title
		g.setColor(Color.green);
		g.setFont(new Font("Ink Free", Font.BOLD, 80));
		g.drawString("TOP 10 Board", 500, 90);
		
		// read scores from the file and show it
		scoreManager.showScores(g);
		
		// draw Back button
		decideColor(g, isBackFocused);
		g.setFont(new Font("Arial", Font.PLAIN, 40));
		g.drawString("Back", 50, 775);
	
//		drawGrid(g);
		
	}
	
	private void drawHelp(Graphics g) {
		// first thing is cancel the focuses on the buttons, which are not on this page
		isPlayFocused = false;
		isHighScoresFocused = false;
		isHelpFocused = false;
		isExitFocused = false;
		
		// draw the screen background
		g.setColor(Color.black);
		g.fillRect(0, 0, GamePanelManager.SCREEN_WIDTH, GamePanelManager.SCREEN_HEIGHT + GamePanelManager.INFO_HEIGHT + GamePanelManager.UNIT_SIZE);
		
		// draw text
		g.setColor(Color.green);
		g.setFont(new Font("Ink Free", Font.BOLD, 40));
		g.drawString("About the Game", 100, 60);
		
		g.setColor(Color.white);
		g.setFont(new Font("consolas", Font.PLAIN, 30));
		g.drawString("1. There are 5 levels in total.", 150, 110);
		g.drawString("2. You should control the paddle to bounce the ball to break the bricks.", 150, 160);
		g.drawString("3. There are 3 balls available at the begining of each level.", 150, 210);
		g.drawString("4. If a ball drops into the screen bottom you will lose a ball.", 150, 260);		
		g.drawString("5. When all the bricks are broken you can go to the next level.", 150, 310);
		
		g.setColor(Color.green);
		g.setFont(new Font("Ink Free", Font.BOLD, 40));
		g.drawString("How to Control", 100, 370);
		
		g.setColor(Color.white);
		g.setFont(new Font("consolas", Font.PLAIN, 30));
		g.drawString("1. You can move the paddle with your MOUSE", 150, 420);
		g.drawString("2. You can launch the ball with [SPACE] key.", 150, 470);
		g.drawString("2. You can fire Laser by LEFT CLICKING on the screen.", 150, 520);
		
		g.setColor(Color.green);
		g.setFont(new Font("Ink Free", Font.BOLD, 40));
		g.drawString("About the Bonus", 100, 580);
	
		g.setColor(new Color(123, 150, 250));
		g.fillOval(250, 610, GamePanelManager.UNIT_SIZE, GamePanelManager.UNIT_SIZE);
		g.setColor(Color.green);
		g.fillOval(250, 660, GamePanelManager.UNIT_SIZE, GamePanelManager.UNIT_SIZE);
		g.setColor(Color.yellow);
		g.fillOval(250, 710, GamePanelManager.UNIT_SIZE, GamePanelManager.UNIT_SIZE);
		g.setColor(Color.white);
		g.fillOval(250, 760, GamePanelManager.UNIT_SIZE, GamePanelManager.UNIT_SIZE);
		
		g.setFont(new Font("consolas", Font.PLAIN, 30));
		g.drawString("-------------------------", 350, 630);
		g.drawString("-------------------------", 350, 680);
		g.drawString("-------------------------", 350, 730);
		g.drawString("-------------------------", 350, 780);
		
		g.drawString("Multi-ball", 850, 630);
		g.drawString("Wide-Paddle (10 second)", 850, 680);
		g.drawString("Sticky-Paddle", 850, 730);
		g.drawString("Laser (10 second)", 850, 780);
		
		// draw Back button
		decideColor(g, isBackFocused);
		g.setFont(new Font("Arial", Font.PLAIN, 40));
		g.drawString("Back", 50, 775);
		
	}
	
	/**
	 * for helping to count the coordinates
	 * */
	private void drawGrid(Graphics g) {
		g.setColor(Color.darkGray);
		for(int i = 0; i < SCREEN_WIDTH / UNIT_SIZE; i++) {
			g.drawLine(i*UNIT_SIZE, INFO_HEIGHT + UNIT_SIZE, i*UNIT_SIZE, SCREEN_HEIGHT);
			g.drawLine(0, INFO_HEIGHT + UNIT_SIZE + i*UNIT_SIZE, SCREEN_WIDTH, INFO_HEIGHT + UNIT_SIZE + i*UNIT_SIZE);
		}
	}
	
	private void makeInfoBar(Graphics g) {
		info ="   Level: " + levelTo + "                      Balls: " + ballNumber + "                      Score: " + score + "                      Bricks: " + brickNumber;
		
		// background
		g.setColor(Color.black);
		g.fillRect(0, 0, SCREEN_WIDTH, INFO_HEIGHT);
		
		// information
		g.setColor(Color.green);
		g.setFont(new Font("Arial", Font.BOLD, 40)); 
		g.drawString(info, 0, 65);
		
		// green border
		g.setColor(Color.green);
		g.drawRect(0, 0, SCREEN_WIDTH - 1, INFO_HEIGHT - 1);
		g.drawRect(1, 1, SCREEN_WIDTH - 3, INFO_HEIGHT - 3);
		g.drawRect(2, 2, SCREEN_WIDTH - 5, INFO_HEIGHT - 5);
		g.drawRect(3, 3, SCREEN_WIDTH - 7, INFO_HEIGHT - 7);
	}
	
	private void levelChange() {
		if(isLevelChanged) {
			// change level
			level = new Level(levelTo);
			System.out.println("Level UP!");
			
			// initialize the related variables 
			isLevelChanged = false;
			brickNumber = level.bricks.length;

			if(isSalute) {
				ballNumber = 30;
			}else {
				ballNumber = 3;				
			}
			
			// reset the buffs
			GamePanelManager.isGameInBuff = false;
			Paddle.length = 6*GamePanelManager.UNIT_SIZE; 
			paddle.isDoubled = false;
			paddle.isPaddleSticky = false;
			Laser.isLaserUnlocked = false;
			Laser.isFireLaser = false;
			Bonus.isWLImplemented = false;
			paddle.isPaddleSticky = false;
			GamePanelManager.countFallBalls = 0;
			
			// rest the ball
			Ball.speedAdjust = 3.0;
			
			// reset the place of paddle and ball to the initial place
			movePaddle(((SCREEN_WIDTH - Paddle.length) / 2) - paddle.x);
			moveBall((paddle.x + (Paddle.length / 2) - (Ball.diameter / 2)) - ball.x, (paddle.y - UNIT_SIZE) - ball.y);
		}
	}
	
	/**
	 * just for methods gameOver() and gameWin() to use
	 */
	private void gameEnd(String name, int score) {  
		
		// add this player into top10 (Whether or not this player is a top10 has been decided in insertPlayer method.)
		scoreManager.insertPlayer(new Player(name, score));
		
		/*initialize the related data when the game is ended*/
		isStart = false;

		//reset the balls
		Ball.speedAdjust = 3.0;
		for(Ball ball : ballList) {
			ball.isBallLaunched = false;
			ball.setSpeedChanged(false);
		}
		
		
		drawWhat = 'M';
		if(isSalute) {
			ballNumber = 30;			
		}else {
			ballNumber = 3;						
		}
		
		//reset the score
		GamePanelManager.score = 0;
		
		//reset the level
		levelTo = '1';
		isLevelChanged = true;
		
		//reset the buffs
		GamePanelManager.isGameInBuff = false;
		Paddle.length = 6*GamePanelManager.UNIT_SIZE; 
		paddle.isDoubled = false;
		paddle.isPaddleSticky = false;
		Laser.isLaserUnlocked = false;
		Laser.isFireLaser = false;
		Bonus.isWLImplemented = false;
		paddle.isPaddleSticky = false;
		GamePanelManager.countFallBalls = 0;
	}
	
	public void gameOver() {
		System.out.println("GAME OVER");
		String name =  JOptionPane.showInputDialog (null ,"Game Over!" + "\n" + "You have got " + GamePanelManager.score + " scores in total" + "\n" + "Leave your name here ~") ;
		gameEnd(name, GamePanelManager.score);
	}
	
	public void gameWin() {
		System.out.println("WIN");
		String name =  JOptionPane.showInputDialog (null ,"Congratulate! You Win!" + "\n" + "You have got " + GamePanelManager.score + " scores in total" + "\n" + "Leave your name here ~") ;
		gameEnd(name, GamePanelManager.score);
	}
	
	public void movePaddle(double x) {
		paddle.move(x, 0);
	}
	
	public void moveBall(double x, double y) {  
		ball.move(x, y);			
	}
	
	public void moveBonus(int y) {
		for(int i = 0; i < level.bricks.length; i++) {
			if(level.bricks[i].bonus.ifDrawBonus) {
				level.bricks[i].bonus.move(0, y);
			}
		}
	}

	
	// getters / setters ----------------------------------------------------------------------------------------------------------

	public ScoreManager getScoreManager() {
		return scoreManager;
	}
	
	public void setScoreManager(ScoreManager scoreManager) {
		this.scoreManager = scoreManager;
	}
	
	public char getLevelTo() {
		return levelTo;
	}
	
	public void setLevelTo(char levelTo) {
		this.levelTo = levelTo;
	}
	

}
