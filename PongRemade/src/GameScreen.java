import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GameScreen extends JPanel implements ActionListener, KeyListener {

	private final static Color BACKGROUND_COLOUR = Color.black;
	private final static int TIMER_DELAY = 5;
	private final static String BALL_IMAGE_FILENAME = "ballsprite2.png";
	
	GameControls gameControls = new GameControls();
	GameState gameState = GameState.Initialising;
	Ball ball;
	Paddle[] paddles = new Paddle[2];
	
	int[] playerScores = new int[] {0,0};
	
	public GameScreen() {
		setBackground(BACKGROUND_COLOUR);
		Timer timer = new Timer(TIMER_DELAY, this);
		timer.start();
		addKeyListener(this);
		setFocusable(true);
	}
	
	private void paintDottedLine(Graphics graphics) {
		Graphics2D graphics2d = (Graphics2D)graphics.create();
		Stroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[] {9}, 0);
		graphics2d.setStroke(dashed);
		graphics2d.setPaint(Color.GRAY);
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
	}
	
	public void paintObjects(Graphics graphics) {
		paintSprite(graphics, ball);
		for (Paddle paddle : paddles) { paintSprite(graphics, paddle); }
		//paintScores(graphics);
	}
	
	public void paintSprite(Graphics graphics, Sprite sprite) {
		Graphics2D g2d = (Graphics2D)graphics;
		g2d.drawImage(sprite.getImage(), sprite.getXPos(), sprite.getYPos(), this);
	}
	
	@Override
	public void keyTyped(KeyEvent event) {
		// TODO Auto-generated method stub
		
	}

	public void checkPlayerKeyPress(KeyEvent event, Player player) {
		int index = player == Player.One ? 0 : 1;
		if (gameControls.downKeyPressed(event, index)) {
			paddles[index].setYVelocity(paddles[index].getSpeed());
		} else if (gameControls.upKeyPressed(event, index)) {
			paddles[index].setYVelocity(-paddles[index].getSpeed());
		} 
	}
	
	public void checkPlayerKeyReleased(KeyEvent event, Player player) {
		int index = player == Player.One ? 0 : 1;
		if (gameControls.downKeyReleased(event, index) || gameControls.upKeyReleased(event, index)) {
			paddles[index].setYVelocity(0);
		}
	}
	
	@Override
	public void keyPressed(KeyEvent event) {
		for (Player player : Player.values()) {
			checkPlayerKeyPress(event, player);
		}
	}

	@Override
	public void keyReleased(KeyEvent event) {
		for (Player player : Player.values()) {
			checkPlayerKeyReleased(event, player);
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
	
	public void checkWallBounce() {
		if (ball.atHorzEdge(getWidth())) {
			addScore(ball.getXPos() == 0 ? Player.Two : Player.One);
			ball.reverseXVelocity();
			ball.resetStartPos();
			ball.resetSpeed();
		}
		if (ball.atVertEdge(getHeight())) {
			ball.reverseYVelocity();
			GameAudio.playBounceSE();
		}
	}
	
	public void addScore(Player player) {
		
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
