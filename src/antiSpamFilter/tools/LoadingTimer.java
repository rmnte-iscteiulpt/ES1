/**
 * 
 */
package antiSpamFilter.tools;

/**
 * @author skner
 *
 */
public class LoadingTimer {

	private long startTime;
	
	/**
	 * 
	 */
	public LoadingTimer() {
		startTime = System.nanoTime();
	}

	public long getElapsedTime()	{
		long elapsedTime = System.nanoTime() - startTime;
		return elapsedTime/1000000;
	}
	
	public void reset()	{
		startTime = System.nanoTime();
	}
}
