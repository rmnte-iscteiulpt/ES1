package antiSpamFilter.tools;

/**
 * A simple tool, serving of a loading timer, for debugging purposes
 * @author skner
 *
 */
public class LoadingTimer {

	private long startTime;
	
	/**
	 * Constructor
	 */
	public LoadingTimer() {
		startTime = System.nanoTime();
	}

	/**
	 * Returns the elapsed time since the timer was created or resetted
	 * @return The elapsed time
	 */
	public long getElapsedTime()	{
		long elapsedTime = System.nanoTime() - startTime;
		return elapsedTime/1000000;
	}
	
	/**
	 * Resets the timer
	 */
	public void reset()	{
		startTime = System.nanoTime();
	}
}
