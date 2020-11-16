import java.awt.Color;

public class Ball extends Sprite {
	
	public Ball(int width, int height) {
		setWidth(width);
		setHeight(height);
	}
	
	public Ball(int width, int height, Color colour) {
		setWidth(width);
		setHeight(height);
		setColour(colour);
	}
	
	public Ball(String filename) {
		loadImage(filename);
	}
}
