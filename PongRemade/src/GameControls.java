import java.awt.event.KeyEvent;

public class GameControls {

	private int[][] playerKeys = new int[][] { 
		new int[] { KeyEvent.VK_W, KeyEvent.VK_S}, new int[] {KeyEvent.VK_UP, KeyEvent.VK_DOWN} 
	};
	private int pauseKey = KeyEvent.VK_SHIFT;
	private int exitKey = KeyEvent.VK_ESCAPE;
	
	public boolean downKeyPressed(KeyEvent event) {
		for (int[] player : playerKeys) {
			if (player[0] == event.getKeyCode()) { return true; }
		}
		return false;
	}
	
	public boolean downKeyPressed(KeyEvent event, int index) {
		if (playerKeys[index][0] == event.getKeyCode()) { return true; }
		return false;
	}
	
	public boolean upKeyPressed(KeyEvent event) {
		for (int[] player : playerKeys) {
			if (player[1] == event.getKeyCode()) { return true; }
		}
		return false;
	}
	
	public boolean upKeyPressed(KeyEvent event, int index) {
		if (playerKeys[index][1] == event.getKeyCode()) { return true; }
		return false;
	}

	public boolean downKeyReleased(KeyEvent event) {
		for (int[] player : playerKeys) {
			if (player[0] == event.getKeyCode()) { return true; }
		}
		return false;
	}
	
	public boolean downKeyReleased(KeyEvent event, int index) {
		if (playerKeys[index][0] == event.getKeyCode()) { return true; }
		return false;
	}
	
	public boolean upKeyReleased(KeyEvent event) {
		for (int[] player : playerKeys) {
			if (player[1] == event.getKeyCode()) { return true; }
		}
		return false;
	}
	
	public boolean upKeyReleased(KeyEvent event, int index) {
		if (playerKeys[index][1] == event.getKeyCode()) { return true; }
		return false;
	}
}
