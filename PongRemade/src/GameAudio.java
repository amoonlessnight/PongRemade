import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class GameAudio {

	private static final String SOUND_FOLDER = "src/resources/sound/";
	private static final String WAV = ".wav";
	
	public static synchronized void play(final String filename) {
		new Thread(new Runnable() {
			public void run() {
				try {
					Clip clip = AudioSystem.getClip();
					AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(filename));
					clip.open(inputStream);
					clip.start();
				} catch (Exception e) {
					System.out.println("Play sound error: " + e.getMessage() + " for " + filename);
				}
			}
		}).start();
	}
	
	public static void playLocalWav(String filename) {
		if (!filename.endsWith(WAV)) { filename = filename.concat(WAV); }
		play(SOUND_FOLDER + filename);
	}
	
	public static void playBounceSE() {
		playLocalWav("mixkit-game-ball-tap-2073");
	}
	
	public static void playPaddleSE() {
		playLocalWav("mixkit-small-hit-in-a-game-2072");
	}
	
	public static void playGoalSE() {
		playLocalWav("mixkit-arcade-mechanical-bling-210");
	}
	
	public static void playGameOverSE() {
		playLocalWav("mixkit-unlock-new-item-game-notification-254");
	}
}
