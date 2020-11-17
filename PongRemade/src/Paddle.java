public class Paddle extends Sprite {
	private static final int DISTANCE_FROM_EDGE = 25;
	private static final String PADDLE_FILENAME = "paddle.png";

	public Paddle(Player player, int panelWidth, int panelHeight) {
		loadImage(PADDLE_FILENAME);
		int initX = player == Player.One ? DISTANCE_FROM_EDGE : panelWidth - DISTANCE_FROM_EDGE - getWidth();
		setSpeed(4);
		setStartPos(initX, panelHeight / 2 - getHeight() / 2);
		resetStartPos();
	}

	public void move(int panelWidth, int panelHeight) {
		setXPos(getXPos() + getXVelocity(), panelWidth);
		setYPos(getYPos() + getYVelocity(), panelHeight);
	}
	
}