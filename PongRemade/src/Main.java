import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

//wait for a key press before starting the game
//ability to restart the game once it has been won
//function to pause the game
//replacing the blocky objects with images
//using a font that better matches the style of the game
//adding a title screen with the option to start game or quit the application
//differing levels of difficulty
//powerups that increases the paddle size or speeds up the ball.

public class Main extends JFrame {

	private static final String BACKGROUND_FILE = "./src/resources/background3.png";
	private static final String GAME_TITLE = "Pong - Remade";
	private static final int[] SCREEN_SIZE = new int[] { 640, 460 };
	
	public Main() {
		setTitle(GAME_TITLE);
		createBackground();		
		setSize(SCREEN_SIZE[0], SCREEN_SIZE[1]);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		add(new GameScreen());
	}
	
	public void createBackground() {
		BufferedImage img = null;
        try {
             File f = new File(BACKGROUND_FILE);
             img = ImageIO.read(f);
             System.out.println("File " + f.toString());
        } catch (Exception e) {
            System.out.println("Cannot read file: " + e);
        }
		BackgroundPanel background = new BackgroundPanel(img);
        
        setContentPane(background); 
	}
	
	public static void main(String[] args) {
		new Main();
	}

}
