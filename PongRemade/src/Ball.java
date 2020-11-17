import java.awt.Color;

public class Ball extends Sprite {
	
	private final int BALL_SPEED = 1;
	private int totalSpeed = BALL_SPEED;
	private final int SPEED_MOD = 2;

	public void increaseSpeed() {
		totalSpeed++;
		if (totalSpeed % SPEED_MOD == 0) {
			setSpeed(getSpeed() + 1);
		}
	}
	
	public void resetSpeed() {
		totalSpeed = BALL_SPEED;
		setSpeed(BALL_SPEED);
	}
	
	public void initVelocity() {
		setXVelocity(BALL_SPEED);
		setYVelocity(BALL_SPEED);
	}
	
	public Ball(int width, int height) {
		setWidth(width);
		setHeight(height);
		initVelocity();
	}
	
	public Ball(int width, int height, Color colour) {
		setWidth(width);
		setHeight(height);
		setColour(colour);
		initVelocity();
	}
	
	public Ball(String filename) {
		loadImage(filename);
		initVelocity();
	}
	
}
