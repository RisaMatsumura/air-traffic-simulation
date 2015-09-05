package model;

public class Stats {
	
	private double totalPlanes;
	private double totalArrived;
	private double totalDeparted;
	private double totalCrashed;
	private Time totalWaiting;
	
	public Stats() {
		setTotalPlanes(0);
		setTotalArrived(0);
		setTotalDeparted(0);
		setTotalCrashed(0);
		setTotalWaiting(Time.TIME_ZERO);
	}
	
	public double getTotalPlanes() {
		return totalPlanes;
	}
	public void setTotalPlanes(double totalPlane) {
		this.totalPlanes = totalPlane;
	}
	/**
	 * @return the totalArrived
	 */
	public double getTotalArrived() {
		return totalArrived;
	}
	/**
	 * @param totalArrived the totalArrived to set
	 */
	public void setTotalArrived(double totalArrived) {
		this.totalArrived = totalArrived;
	}
	/**
	 * @return the totalDeparted
	 */
	public double getTotalDeparted() {
		return totalDeparted;
	}
	/**
	 * @param totalDeparted the totalDeparted to set
	 */
	public void setTotalDeparted(double totalDeparted) {
		this.totalDeparted = totalDeparted;
	}
	/**
	 * @return the totalCrashed
	 */
	public double getTotalCrashed() {
		return totalCrashed;
	}
	/**
	 * @param totalCrashed the totalCrashed to set
	 */
	public void setTotalCrashed(double totalCrashed) {
		this.totalCrashed = totalCrashed;
	}
	/**
	 * @return the totalWaitingSum
	 */
	public Time getTotalWaiting() {
		return totalWaiting;
	}
	/**
	 * @param totalWaitingSum the totalWaitingSum to set
	 */
	public void setTotalWaiting(Time totalWaiting) {
		this.totalWaiting = totalWaiting;
	}
	

}
