package audio;

import java.net.URL;

import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class AudioPlayer implements AudioPlayerInterface {

	private static final String BACKGROUND_MUSIC_FILE = "audio/Background.wav"; // game started
	private static final String CRASH_SOUND_FILE = "audio/debrisCrash.wav";
	private static final String FIRE_LASER_SOUND_FILE = "audio/laser.wav"; // laser fired
	private static final String DEBRIS_EXPLOSION_SOUND_FILE = "audio/smallExplosion.wav"; // laser hits debris, ship
																							// hits
	// debris, debris hits planet
	private static final String SPACESHIP_EXPLOSION_SOUND_FILE = "audio/bigExplosion.wav"; // spaceship hits planet
	private static final double CRASH_SOUND_VOLUME = 0.5;

	private static final String DEBRIS_CRASH_SOUND_FILE = "audio/squeak.wav";

	private final MediaPlayer musicPlayer;
	private final AudioClip crashPlayer;
	private final AudioClip laserPlayer;
	private final AudioClip debrisExplosionPlayer;
	private final AudioClip spaceShipExplosionPlayer;
	private final AudioClip debrisCrashPlayer;

	/**
	 * Constructs a new AudioPlayer by directly loading the background music and
	 * crash sound files into a new MediaPlayer / AudioClip.
	 */
	public AudioPlayer() {
		this.musicPlayer = new MediaPlayer(loadAudioFile(BACKGROUND_MUSIC_FILE)); // background Music
		this.crashPlayer = new AudioClip(convertNameToUrl(CRASH_SOUND_FILE));
		this.laserPlayer = new AudioClip(convertNameToUrl(FIRE_LASER_SOUND_FILE)); // Laser Sound
		this.debrisExplosionPlayer = new AudioClip(convertNameToUrl(DEBRIS_EXPLOSION_SOUND_FILE)); // Laser Hits the
		this.debrisCrashPlayer = new AudioClip(convertNameToUrl(DEBRIS_CRASH_SOUND_FILE)); // Debris
		this.spaceShipExplosionPlayer = new AudioClip(convertNameToUrl(SPACESHIP_EXPLOSION_SOUND_FILE)); // Spaceship
																											// Hits the
																											// planet
	}

	public AudioClip getLaserPlayer() {
		return laserPlayer;
	}

	public AudioClip getDebrisExplosionPlayer() {
		return debrisExplosionPlayer;
	}

	public AudioClip getSpaceShipExplosionPlayer() {
		return spaceShipExplosionPlayer;
	}

	@Override
	public void playBackgroundMusic() {
		if (isPlayingBackgroundMusic()) {
			return;
		}
		// Loop for the main music sound:
		this.musicPlayer.setCycleCount(MediaPlayer.INDEFINITE);
		this.musicPlayer.play();
	}

	@Override
	public void stopBackgroundMusic() {
		if (isPlayingBackgroundMusic()) {
			this.musicPlayer.stop();
		}
	}

	@Override
	public boolean isPlayingBackgroundMusic() {
		return MediaPlayer.Status.PLAYING.equals(this.musicPlayer.getStatus());
	}

	/**
	 * Plays the laser sound effect.
	 */
	@Override
	public void playCrashSound() {
		crashPlayer.play(CRASH_SOUND_VOLUME);
	}

	@Override
	public void playDebrisCrashSound() {
		debrisCrashPlayer.play();
	}

	/**
	 * Plays the laser sound effect.
	 */
	@Override
	public void playLaserSound() {
		laserPlayer.play();
	}

	/**
	 * Plays the explosion for debrises sound effect.
	 */
	@Override
	public void playDebrisExplosionSound() {
		debrisExplosionPlayer.play();
	}

	/**
	 * Plays the explosion for ship sound effect.
	 */
	@Override
	public void playShipExplosionSound() {
		spaceShipExplosionPlayer.play();
	}

	private Media loadAudioFile(String fileName) {
		return new Media(convertNameToUrl(fileName));
	}

	private String convertNameToUrl(String fileName) {
		URL musicSourceUrl = getClass().getClassLoader().getResource(fileName);
		if (musicSourceUrl == null) {
			throw new IllegalArgumentException(
					"Please ensure that your resources folder contains the appropriate files for this exercise.");
		}
		return musicSourceUrl.toExternalForm();
	}

	public static String getBackgroundMusicFile() {
		return BACKGROUND_MUSIC_FILE;
	}

	public static String getCrashSoundFile() {
		return CRASH_SOUND_FILE;
	}

}
