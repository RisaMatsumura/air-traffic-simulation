package model;

/**
 * TODO need to handle negative number
 * Time object stores a time in Day:Hour:Minute:Second format.
 * It can be used to indicate duration of time or an time of a day.
 * The class also provides basic calculations and comparisons with another Time object.
 * @author risam
 *
 */
public class Time {
	
	private int day = 0;
	private int hour = 0;
	private int minute = 0;
	private int second = 0;
	private int timeInSeconds = 0;
	
	// Time with 0 is provided as a constant for a quick reference.
	public static Time TIME_ZERO = new Time(0);
	
	/**
	 * Create a Time object with specified day, hour, minute and second.
	 * @param day
	 * @param hour
	 * @param minute
	 * @param second
	 */
	public Time(int day, int hour, int minute, int second) {
		setTime(day, hour, minute, second);
	}
	
	/**
	 * Create a Time object with specified hour, minute and second.
	 * @param hour
	 * @param minute
	 * @param second
	 */
	public Time(int hour, int minute, int second) {
		setTime(0, hour, minute, second);
	}

	/**
	 * Create a Time object with specified seconds.
	 * @param timeInSeconds
	 */
	public Time(int timeInSeconds) {
		setTime(timeInSeconds);
	}

	/**
	 * Set time with specified seconds.
	 * @param seconds
	 */
	public void setTime(int seconds) {
		setTimeInSeconds(seconds);
		int[] hms = calculateHMS(seconds);
		setDay(hms[0]);
		setHour(hms[1]);
		setMinute(hms[2]);
		setSecond(hms[3]);
	}
	
	/**
	 * Set time with specified day, hour, minute and second.
	 * @param day
	 * @param hour
	 * @param minute
	 * @param second
	 */
	public void setTime(int day, int hour, int minute, int second) {
		this.day = day;
		this.hour = hour;
		this.minute = minute;
		this.second = second;
		timeInSeconds = calculateTimeInSeconds(day, hour, minute, second);
	}
	
	/**
	 * Calculate time in seconds from given day, hour, minute and second.
	 * @param day
	 * @param hour
	 * @param minute
	 * @param second
	 * @return
	 */
	public int calculateTimeInSeconds(int day, int hour, int minute, int second) {
		return day * 24 * 60 * 60 + hour * 60 * 60 + minute * 60 + second;
	}
	
	/**
	 * Calculate time in Hour: Minute: Second form from given seconds.
	 * @param timeInSeconds
	 * @return
	 */
	public int[] calculateHMS(int timeInSeconds) {
		int[] hms = {0, 0, 0, 0};
		hms[0] = timeInSeconds / 3600 /24;
		hms[1] = timeInSeconds / 3600;
		hms[2] = (timeInSeconds % 3600) / 60;
		hms[3] = (timeInSeconds % 3600) % 60;
		return hms;
	}
	
	/**
	 * Set day of a Time object.
	 * @param day
	 */
	public void setDay(int day) {
		this.day = day;
	}
	
	/**
	 * Get day of a Time object.
	 * @return
	 */
	public int getDay() {
		return day;
	}
	
	/**
	 * Set hour of a Time object.
	 * @param hour
	 */
	public void setHour(int hour) {
		this.hour = hour;
	}
	
	/**
	 * Get hour of a Time object.
	 * @return
	 */
	public int getHour() {
		return hour;
	}

	/**
	 * Set minute of a Time object.
	 * @param minute
	 */
	public void setMinute(int minute) {
		this.minute = minute;
	}

	/**
	 * Get minute of a Time object.
	 * @return
	 */
	public int getMinute() {
		return minute;
	}

	/**
	 * Set second of a Time object.
	 * @param second
	 */
	public void setSecond(int second) {
		this.second = second;
	}

	/**
	 * Get second of a Time object.
	 * @return
	 */
	public int getSecond() {
		return second;
	}

	/**
	 * Set timeInSeconds of a Time object.
	 * @param timeInSeconds
	 */
	public void setTimeInSeconds(int timeInSeconds) {
		this.timeInSeconds = timeInSeconds;
		
	}

	/**
	 * Get timeInSeconds of a Time object.
	 * @return
	 */
	public int getTimeInSeconds() {
		return timeInSeconds;
	}

	/**
	 * Add time to a Time object.
	 * @param time
	 * @return
	 */
	public Time add(Time time) {
		// TODO what if negative number
		int newTimeInSeconds = timeInSeconds + time.getTimeInSeconds();
		return new Time(newTimeInSeconds);
	}

	/**
	 * Subtract time from a Time object.
	 * @param time
	 * @return
	 */
	public Time subtract(Time time) {
		
		// TODO what if negative number
		int newTimeInSeconds = getTimeInSeconds() - time.getTimeInSeconds();
		return new Time(newTimeInSeconds);
	}
	
	/**
	 * Devide a Time object by devider.
	 * @param devider
	 * @return
	 */
	public Time devide(int devider) {
		int timeInSeconds = this.getTimeInSeconds() / devider;
		Time time = new Time(timeInSeconds);
		return time;
	}
	
	/**
	 * Convert a Time object into a String form.
	 */
	public String toString() {
		String timeString = null;
		if (this != null)
			timeString = formatNumber(hour) + ":" + formatNumber(minute) + ":" + formatNumber(second);
		return timeString;
	}

	/**
	 * Format a variables to have 2 digits.
	 * @param time
	 * @return
	 */
	private String formatNumber(int time) {
		String formattedNumber = "";
		if (time < 10) {
			formattedNumber += "0";
		}
		formattedNumber += time;
		return formattedNumber;
	}
	
	/**
	 * Evaluate whether a time is the same as the one in another object.
	 * 
	 * @param time
	 * @return
	 */
	public boolean equalTo(Time time) {
		boolean isEqual = false;
		if (getHour() == time.getHour() 
				&& getMinute() == time.getMinute() 
				&& getSecond() == time.getSecond()
				&& getTimeInSeconds() == time.getTimeInSeconds()) {
			isEqual = true;
		}
		return isEqual;
	}
	
	/**
	 * Compare a time value with another.
	 * Return 1 the first value is larger than the second.
	 * Return -1 the first value is smaller than the second.
	 * Return 0 the  first and the second values are the same.
	 * 
	 * @param time
	 * @return
	 */
	public int compareTo(Time time) {
		if (timeInSeconds > time.getTimeInSeconds()){
			return 1;
		} else if (timeInSeconds < time.getTimeInSeconds()) {
			return -1;
		} else {
			return 0;
		}	
	}

}
