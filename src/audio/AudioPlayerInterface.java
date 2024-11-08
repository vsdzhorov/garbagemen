package audio;

public interface AudioPlayerInterface {

	/**
	 * Starts playing the background music if it's not started already.
	 */
	void playBackgroundMusic();

	/**
	 * Stops the background music if it is currently playing.
	 */
	void stopBackgroundMusic();

	/**
	 * Checks if the background music is playing.
	 *
	 * @return true if background music is playing, false if not
	 */
	boolean isPlayingBackgroundMusic();

	/**
	 * Plays the crash sound effect.
	 */
	void playCrashSound();

	/**
	 * Plays the laser sound effect.
	 */
	void playLaserSound();

	/**
	 * Plays the explosion for debrisses sound effect.
	 */
	void playDebrisExplosionSound();

	/**
	 * Plays the explosion for ship sound effect.
	 */
	void playShipExplosionSound();

	void playDebrisCrashSound();

}
