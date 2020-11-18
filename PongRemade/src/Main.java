import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

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
		BackgroundPanel background = new BackgroundPanel(img, BackgroundPanel.SCALED, 0.50f, 0.5f);
        
        setContentPane(background); 
	}
	
	public static void main(String[] args) {
		new Main();
	}

}
