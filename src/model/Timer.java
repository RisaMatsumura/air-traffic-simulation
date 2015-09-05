package model;

/**
 * Timer object increment a Time with a given timeIncrement each time.
 * @author risam
 *
 */
public class Timer {

	private Time currentTime;
	private Time timeIncrement = new Time(0, 0, 30);
	
	/**
	 * Create a Timer object with a starting time
	 */
	public Timer(Time currentTime) {
		setCurrentTime(currentTime);
	}
	
	/**
	 * Increment currentTime with timeIncrement
	 * @return
	 */
	public Time incrementTime() {
		Time incrementedTime = currentTime.add(timeIncrement);
		setCurrentTime(incrementedTime);
		return incrementedTime;
	}
	
	
//	public void startTimer() {
//		start = true;
//		while(start) {
//			try {
//				Thread.sleep(1000);
//				currentTime++;
//			}  catch (InterruptedException e) { 
//				e.printStackTrace(); 
//			}
//		}
//	}

	/**
	 * Set currentTime of a Timer object.
	 * @param time
	 */
	public void setCurrentTime(Time time) {
		this.currentTime = time;
	}
	
	/** Get currentTime of a Time object.
	 * @return
	 */
	public Time getCurrentTime() {
		return currentTime;
	}
	
	/**
	 * Set timeIncrement of a Time object.
	 * @param timeIncrement
	 */
	public void setTimeIncrement(Time timeIncrement) {
		this.timeIncrement = timeIncrement;
	}

	/**
	 * get timeIncrement of a Time object.
	 * @return
	 */
	public Time getTimeIncrement() {
		return timeIncrement;
	}
	
	/**
	 * Reset timer with time 0.
	 */
	public void reset() {
		setCurrentTime(Time.TIME_ZERO);
	}
}
