import java.awt.event.KeyEvent;

public class GameControls {

	private static int[][] playerKeys = new int[][] { 
		new int[] { KeyEvent.VK_W, KeyEvent.VK_S}, new int[] {KeyEvent.VK_UP, KeyEvent.VK_DOWN} 
	};
	private static int pauseKey = KeyEvent.VK_SHIFT;
	private static int exitKey = KeyEvent.VK_ESCAPE;
	
	public static boolean pauseKeyPressed(KeyEvent event) {
		if (pauseKey == event.getKeyCode()) { return true; }
		return false;
	}
	
	public static boolean exitKeyPressed(KeyEvent event) {
		if (exitKey == event.getKeyCode()) { return true; }
		return false;
	}
	
	public static boolean downKeyPressed(KeyEvent event) {
		for (int[] player : playerKeys) {
			if (player[1] == event.getKeyCode()) { return true; }
		}
		return false;
	}
	
	public static boolean downKeyPressed(KeyEvent event, int index) {
		if (playerKeys[index][1] == event.getKeyCode()) { return true; }
		return false;
	}
	
	public static boolean upKeyPressed(KeyEvent event) {
		for (int[] player : playerKeys) {
			if (player[0] == event.getKeyCode()) { return true; }
		}
		return false;
	}
	
	public static boolean upKeyPressed(KeyEvent event, int index) {
		if (playerKeys[index][0] == event.getKeyCode()) { return true; }
		return false;
	}

	public static boolean downKeyReleased(KeyEvent event) {
		for (int[] player : playerKeys) {
			if (player[1] == event.getKeyCode()) { return true; }
		}
		return false;
	}
	
	public static boolean downKeyReleased(KeyEvent event, int index) {
		if (playerKeys[index][1] == event.getKeyCode()) { return true; }
		return false;
	}
	
	public static boolean upKeyReleased(KeyEvent event) {
		for (int[] player : playerKeys) {
			if (player[0] == event.getKeyCode()) { return true; }
		}
		return false;
	}
	
	public static boolean upKeyReleased(KeyEvent event, int index) {
		if (playerKeys[index][0] == event.getKeyCode()) { return true; }
		return false;
	}
}
