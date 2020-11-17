import java.awt.Color;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Sprite {

	private int width, height;
	private int xPos, yPos;
	private int startXPos, startYPos;
	private int xVelocity, yVelocity;
	private Color colour;
	private int speed = 1;
	private Image image;
	
	public int getWidth() { return width; }
	public int getHeight() { return height; }
	public int getXPos() { return xPos; }
	public int getYPos() { return yPos; }
	public int getXVelocity() { return xVelocity; }
	public int getYVelocity() { return yVelocity; }
	public Color getColour() { return colour; }
	public Rectangle getRect() { return new Rectangle(xPos, yPos, width, height); }
	public int getSpeed() { return speed; };
	public Image getImage() { return image; };
		
	public void loadImage(String filename) {
		ImageIcon imgicon = new ImageIcon("src/resources/" + filename);
		image = imgicon.getImage();
		
		setWidth(image.getWidth(null));
		setHeight(image.getHeight(null));
	}
	
	public void setStartPos(int newXPos, int newYPos) {
		startXPos = newXPos;
		startYPos = newYPos;
	}
	
	public void resetStartPos() {
		setXPos(startXPos);
		setYPos(startYPos);
	}
	
	public void setColour(Color newColour) {
		colour = newColour;
	}
	
	public void setXVelocity(int xVel) {
		xVelocity = (xVel);
	}
	
	public void setYVelocity(int yVel) {
		yVelocity = (yVel);
	}
	
	public int getTrueXVelocity() { 
		return getXVelocity() + (getXVelocity() > 0 ? getSpeed() : -getSpeed()); 
	}
	
	public int getTrueYVelocity() { 
		return getYVelocity() + (getYVelocity() > 0 ? getSpeed() : -getSpeed()); 
	}
	
	public void reverseXVelocity() {
		xVelocity = -xVelocity;
	}
	
	public void reverseYVelocity() {
		yVelocity = -yVelocity;
	}
	
	public void setWidth(int newWidth) {
		width = newWidth;
	}
	
	public void setHeight(int newHeight) {
		height = newHeight;
	}
	
	public void setXPos(int newXPos) {
		xPos = newXPos;
	}
	
	public void setYPos(int newYPos) {
		yPos = newYPos;
	}
	
	public void setXPos(int newXPos, int panelWidth) {
		xPos = newXPos;
		if (xPos < 0) {
			xPos = 0;
		} else if (xPos + getWidth() > panelWidth) {
			xPos = panelWidth - getWidth();
		}
	}
	
	public void setYPos(int newYPos, int panelHeight) {
		yPos = newYPos;
		if (yPos < 0) {
			yPos = 0;
		} else if (yPos + getHeight() > panelHeight) {
			yPos = panelHeight - getHeight();
		}
	}
	
	public void move() {
		setXPos(getXPos() + getTrueXVelocity());
		setYPos(getYPos() + getTrueYVelocity());
	}
	
	// Check if this sprite's Rectangle intersects with another sprite's Rectangle
	public boolean intersects(Sprite sprite) {
		return getRect().intersects(sprite.getRect());
	}
	
}
