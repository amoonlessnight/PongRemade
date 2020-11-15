import javax.swing.JFrame;

public class Main extends JFrame {

	private static final String GAME_TITLE = "Pong - Remade";
	private static final int[] SCREEN_SIZE = new int[] { 640, 460 };
	
	public Main() {
		setTitle(GAME_TITLE);
		setSize(SCREEN_SIZE[0], SCREEN_SIZE[1]);
		setResizable(false);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new Main();
	}

}
