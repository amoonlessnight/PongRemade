import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class GameScreen extends JPanel implements ActionListener, KeyListener {

	private final static int TIMER_DELAY = 5;
	private final static String BALL_IMAGE_FILENAME = "ballsprite2.png";
	private final static int SCORE_X_BUFFER = 100;
	private final static int SCORE_Y_BUFFER = 80;
	private final static int SCORE_FONT_SIZE = 50;
	private final static int SCORE_MAX = 5;
	private final static String PAUSED_TEXT = "PAUSED";
	private final static String WIN_TEXT = "WIN!";
	private final static int WIN_X_BUFFER = 20;
	
	GameState gameState = GameState.Initialising;
	
	Ball ball;
	Paddle[] paddles = new Paddle[2];
	
	int[] playerScores = new int[] {0,0};
	Font scoreFont = new Font("Credit Valley", Font.PLAIN, SCORE_FONT_SIZE);
	Player gameWinner;
	
	public GameScreen() {
		setOpaque(false);
		Timer timer = new Timer(TIMER_DELAY, this);
		timer.start();
		addKeyListener(this);
		setFocusable(true);
	}
	
	private void paintDottedLine(Graphics graphics) {
		Graphics2D graphics2d = (Graphics2D)graphics.create();
		Stroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[] {9}, 0);
		graphics2d.setStroke(dashed);
		graphics2d.setPaint(Color.LIGHT_GRAY);
		graphics2d.drawLine(getWidth() / 2,  0,  getWidth() / 2,  getHeight());
		graphics2d.dispose();
	}
	
	@Override
	public void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		paintDottedLine(graphics);
		if (gameState != GameState.Initialising) {
			paintObjects(graphics);
		}
		if (gameState == GameState.Paused) {
			paintPausedScreen(graphics);
		} else if (gameState == GameState.GameOver) {
			paintEndScreen(graphics);
		}
	}
	
	public void paintObjects(Graphics graphics) {
		paintSprite(graphics, ball);
		for (Paddle paddle : paddles) { paintSprite(graphics, paddle); }
		paintScores(graphics);
	}
	
	public void paintSprite(Graphics graphics, Sprite sprite) {
		Graphics2D g2d = (Graphics2D)graphics;
		g2d.drawImage(sprite.getImage(), sprite.getXPos(), sprite.getYPos(), this);
	}
	
	public void paintScores(Graphics graphics) {
		for (int i = 0; i < playerScores.length; i++) {
			graphics.setFont(scoreFont);
			graphics.setColor(Color.WHITE);
			int scoreX = i == 0 ? SCORE_X_BUFFER : getWidth() - SCORE_X_BUFFER;
			graphics.drawString(Integer.toString(playerScores[i]), scoreX, SCORE_Y_BUFFER);
		}
	}
	
	public void paintPausedScreen(Graphics graphics) {
		graphics.setFont(scoreFont);
		graphics.setColor(Color.WHITE);
		int pauseX = getWidth() / 2 - graphics.getFontMetrics().stringWidth(PAUSED_TEXT) / 2;
		graphics.drawString(PAUSED_TEXT, pauseX, getHeight() - SCORE_Y_BUFFER);
	}
	
	public void paintEndScreen(Graphics graphics) {
		graphics.setFont(scoreFont);
		graphics.setColor(Color.WHITE);
		int winX = getWidth() / 2;
		if (gameWinner == Player.One) {
			winX -= WIN_X_BUFFER + graphics.getFontMetrics().stringWidth(WIN_TEXT);
		} else {
			winX += WIN_X_BUFFER;
		}
		graphics.drawString(WIN_TEXT, winX, SCORE_Y_BUFFER);
	}
	
	@Override
	public void keyTyped(KeyEvent event) {
		// TODO Auto-generated method stub
		
	}

	public void checkPlayerKeyPress(KeyEvent event, Player player) {
		int index = player == Player.One ? 0 : 1;
		if (GameControls.downKeyPressed(event, index)) {
			paddles[index].setYVelocity(paddles[index].getSpeed());
		} else if (GameControls.upKeyPressed(event, index)) {
			paddles[index].setYVelocity(-paddles[index].getSpeed());
		} 
	}
	
	public void checkPlayerKeyReleased(KeyEvent event, Player player) {
		int index = player == Player.One ? 0 : 1;
		if (GameControls.downKeyReleased(event, index) || GameControls.upKeyReleased(event, index)) {
			paddles[index].setYVelocity(0);
		}
	}
	
	public void resetGame() {
		for (int i = 0; i < playerScores.length; i++) { playerScores[i] = 0; }
		gameState = GameState.Playing;
	}
	
	@Override
	public void keyPressed(KeyEvent event) {
		if (gameState == GameState.Playing) {
			for (Player player : Player.values()) {
				checkPlayerKeyPress(event, player);
			}
			if (GameControls.pauseKeyPressed(event)) { 
				gameState = GameState.Paused; 
			}
		} else if (gameState == GameState.Paused) {
			gameState = GameState.Playing;
		} else if (gameState == GameState.GameOver) {
			resetGame();
		}
	}

	@Override
	public void keyReleased(KeyEvent event) {
		if (gameState == GameState.Playing) {
			for (Player player : Player.values()) {
				checkPlayerKeyReleased(event, player);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		update();
		repaint();
	}

	public void update() {
		if (gameState == GameState.Initialising) {
			createObjects();
			gameState = GameState.Playing;
		} else if (gameState == GameState.Playing) {
			for (Paddle paddle : paddles) { moveObject(paddle); }
			moveObject(ball);
			checkWallBounce();
			checkPaddleBounce();
		} else if (gameState == GameState.GameOver) {
			
		} else if (gameState == GameState.Paused) {
			
		}
	}
	
	public void createObjects() {
		ball = new Ball(BALL_IMAGE_FILENAME);
		ball.setStartPos(getWidth() / 2 - ball.getWidth() / 2, getHeight() / 2 - ball.getHeight() / 2);
		ball.resetStartPos();
		paddles[0] = new Paddle(Player.One, getWidth(), getHeight());
		paddles[1] = new Paddle(Player.Two, getWidth(), getHeight());
	}
	
	public void moveObject(Sprite sprite) {
		sprite.move(getWidth(), getHeight());
	}
	
	public void atHorzEdgeProcessing() {
		addScore(ball.getXPos() == 0 ? Player.Two : Player.One);
		ball.reverseXVelocity();
		ball.resetStartPos();
		ball.resetSpeed();
		GameAudio.playGoalSE();
		checkGameOver();
	}
	
	public void checkGameOver() {
		for (int i = 0; i < playerScores.length; i++) {
			if (playerScores[i] == SCORE_MAX) {
				gameWinner = i == 0 ? Player.One : Player.Two;
				gameState = GameState.GameOver;
			}
		}
	}
	
	public void checkWallBounce() {
		if (ball.atHorzEdge(getWidth())) {
			atHorzEdgeProcessing();
		}
		if (ball.atVertEdge(getHeight())) {
			ball.reverseYVelocity();
			GameAudio.playBounceSE();
		}
	}
	
	public void addScore(Player player) {
		int index = player == Player.One ? 0 : 1;
		playerScores[index]++;
	}
	
	public void checkPaddleBounce() {
		if (ball.getXVelocity() < 0 && ball.intersects(paddles[0])) {
			ball.reverseXVelocity();
			ball.increaseSpeed();
			GameAudio.playPaddleSE();
		}
		if (ball.getXVelocity() > 0 && ball.intersects(paddles[1])) {
			ball.reverseXVelocity();
			ball.increaseSpeed();
			GameAudio.playPaddleSE();
		}
	}
}
