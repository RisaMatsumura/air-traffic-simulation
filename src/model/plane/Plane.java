package model.plane;

import java.util.Random;

import model.Time;
import priority.PriorityLevel2;

public abstract class Plane extends PriorityLevel2 {

	protected PlaneStatus status;	
	protected int id;
	
	protected Time initialisedTime;
	protected Time waitingTime;

	protected Time initialFuelTime = null;
	protected Time currentFuelTime = null;
	protected Time landingStartTime = null;
	protected Time arrivalTime = null;

	protected Time takeOffStartTime = null;
	protected Time departureTime = null;
	
	protected Time crashedTime = null;

	// Time required for landing and take off
	protected static Time LANDING_DURATION;
	protected static Time TAKEOFF_DURATION;

	// Minimum and maximum values for the remaining fuel time
	protected Time MIN_FUEL_TIME;
	protected Time MAX_FUEL_TIME;

	/**
	 * Create a Plane object with given id, status and initialised time
	 * @param id
	 * @param status
	 * @param time
	 */
	public Plane(int id, PlaneStatus status, Time time) {
		setId(id);
		setInitialisedTime(time);
		setToArriveOrDepart(status);
		setState(status);
	}
	
	/**
	 * Create a copy of a given Plane object
	 * @param plane
	 */
	public Plane(Plane plane) {
		setId(plane.id);
		setState(plane.status);
		
		setInitialisedTime(plane.initialisedTime);
		setWaitingTime(plane.waitingTime);
		
		setInitialFuelTime(plane.initialFuelTime);
		setCurrentFuelTime(plane.currentFuelTime);
		setLandingStartTime(plane.landingStartTime);
		setArrivalTime(plane.arrivalTime);
		
		setTakeOffStartTime(plane.takeOffStartTime);
		setDepartureTime(plane.departureTime);

		setCrashedTime(plane.crashedTime);
	}

	/**
	 * Create a copy of Plane object.
	 * Create a different type of plane depending on the plane.
	 * @return
	 */
	public Plane deepCopy() {
		
		if(this instanceof CommercialPlane) {
			return new CommercialPlane((CommercialPlane) this);
		} else if (this instanceof LightPlane) {
			return new LightPlane((LightPlane) this);
		} else if (this instanceof Glider) {
			return new Glider((Glider) this);
		}
		throw new Error("Unknown type");
	}
	
	/**
	 * Set a status of arrival or departure plane.
	 * @param status
	 */
	protected void setToArriveOrDepart(PlaneStatus status) {
		switch(status) {
		case TO_ARRIVE:		
			setWaitingTime(Time.TIME_ZERO);
			setInitialFuelTime(generateInitialFuelTime());
			setCurrentFuelTime(initialFuelTime);		
			break;
		case TO_DEPART:
			setWaitingTime(Time.TIME_ZERO);
			break;
		default:
			break;
		}
	}

	/**
	 * Change a staus of the plane and set values .
	 * @param state
	 * @param currentTime
	 */
	public void changeState(PlaneStatus state, Time currentTime) {
		setState(state);

		switch(state) {
		case TAKING_OFF:
			setWaitingTime(Time.TIME_ZERO);
			setTakeOffStartTime(currentTime);
			break;
		case LANDING:
			setLandingStartTime(currentTime);
			setWaitingTime(Time.TIME_ZERO);
			break;
		case DEPARTED:
			setDepartureTime(currentTime);
			setWaitingTime(null);
			break;
		case ARRIVED:
			setArrivalTime(currentTime);
			setWaitingTime(null);
			break;
		case CRASHED:
			setCrashedTime(currentTime);
			setWaitingTime(null);
			break;
		default:
			break;
		}
	}

	/**
	 * Set status of a plane.
	 * @param state
	 */
	public void setState(PlaneStatus state) {
		this.status = state;
	}

	/**
	 * Return status of the plane
	 * @return
	 */
	public PlaneStatus getState() {
		return status;
	}
	
	/**
	 * Set id of the Plane
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Get id of the Plane
	 * @return
	 */
	public int getId() {
		return id;
	}

	/**
	 * Set time that the plane was created.
	 * @param initialisedTime
	 */
	public void setInitialisedTime(Time initialisedTime) {
		this.initialisedTime = initialisedTime;
	}
	
	/**
	 * Return time that the plane was generated.
	 * @return
	 */
	public Time getInitialisedTime() {
		return initialisedTime;
	}
	
	/**
	 * Set time that the plane has been waiting.
	 * @param waitingTime
	 */
	public void setWaitingTime(Time waitingTime) {
		this.waitingTime = waitingTime;
	}

	/**
	 * Return time that the plane has been waiting.
	 * @return
	 */
	public Time getWaitingTime() {
		return waitingTime;
	}
	
	/**
	 * Set initial fuel time of the plane.
	 * @param time
	 */
	public void setInitialFuelTime(Time time) {
		this.initialFuelTime = time;
	}

	/**
	 * Return initial fuel time of the plane.
	 * @return
	 */
	public Time getInitialFuelTime() {
		return initialFuelTime;
	}

	/**
	 * Set current remaining fuel time of the plane.
	 * @param currentFuelTime
	 */
	public void setCurrentFuelTime(Time currentFuelTime) {
		this.currentFuelTime = currentFuelTime;
	}

	/**
	 * Return current remaining fuel time of the plane.
	 * @return
	 */
	public Time getCurrentFuelTime() {
		return currentFuelTime;
	}

	/**
	 * Set time that the plane started landing.
	 * @param landingStartTime
	 */
	public void setLandingStartTime(Time landingStartTime) {
		this.landingStartTime = landingStartTime;
	}

	/**
	 * Return time that the plane started landing.
	 * @return
	 */
	public Time getLandingStartTime() {
		return landingStartTime;
	}

	/**
	 * Set arrival time of the plane.
	 * @param arrivalTime
	 */
	public void setArrivalTime(Time arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	/**
	 * Return arrival time of the plane.
	 * @return
	 */
	public Time getArrivalTime() {
		return arrivalTime;
	}

	/**
	 * Set time that the plane started taking off.
	 * @param takeOffStartTime
	 */
	public void setTakeOffStartTime(Time takeOffStartTime) {
		this.takeOffStartTime = takeOffStartTime;
	}

	/**
	 * Return time that the plane started taking off.
	 * @return
	 */
	public Time getTakeOffStartTime() {
		return takeOffStartTime;
	}
	
	/**
	 * Set departure time of the plane.
	 * @param departureTime
	 */
	public void setDepartureTime(Time departureTime) {
		this.departureTime = departureTime;
	}

	/**
	 * Return departure time of the plane.
	 * @return
	 */
	public Time getDepartureTime() {
		return departureTime;
	}

	/**
	 * Set time that the plane was crashed.
	 * @param crashedTime
	 */
	public void setCrashedTime(Time crashedTime) {
		this.crashedTime = crashedTime;
	}

	/**
	 * Get time that the plane was crashed.
	 * @return
	 */
	public Time getCrashedTime() {
		return crashedTime;
	}

	/**
	 * Calculate how much time has passed since the plane was initialised
	 * to the current time.
	 * @param currentTime
	 * @return
	 */
	public Time calculateTimeSinceInitialised(Time currentTime) {
		Time timeSinceInitialised = null;
		timeSinceInitialised = currentTime.subtract(initialisedTime);
		return timeSinceInitialised;
	}

	/**
	 * Calculate current remaining fuel time based on current time 
	 * and initial fuel time.
	 * @param currentTime
	 * @return
	 */
	public Time calculateCurrentFuelTime(Time currentTime) {
		Time timeInAir = currentTime.subtract(initialisedTime);
		Time currentFuelTime = initialFuelTime.subtract(timeInAir);
		return currentFuelTime;
	}

	/**
	 * Calculate remaining landing time based on current time
	 * and landing start time.
	 * @param currentTime
	 * @return
	 */
	public Time calculateRemainingLandingTime(Time currentTime) {
		Time remainingLandingTime = Time.TIME_ZERO;
		if (status.equals(PlaneStatus.LANDING)) {
			Time landingTimeSpent = currentTime.subtract(landingStartTime);
			remainingLandingTime = getLandingDuration().subtract(landingTimeSpent);
		}
		return remainingLandingTime; 
	}

	/**
	 * Calculate remaining take off time based on current time
	 * and take off start time.
	 * @param currentTime
	 * @return
	 */
	public Time calculateRemainingTakeOffTime(Time currentTime) {
		Time remainingTakeOffTime = Time.TIME_ZERO;
		if (status.equals(PlaneStatus.TAKING_OFF)) {
			Time takeOffTimeSpent = currentTime.subtract(takeOffStartTime);
			remainingTakeOffTime = getTakeOffDuration().subtract(takeOffTimeSpent);
		}
		return remainingTakeOffTime; 
	}

	/**
	 * Generate initial fuel time randomly in a range
	 * between maximum and minimum fuel time.	
	 * @return
	 */
	private Time generateInitialFuelTime() {
		Time initialFuelTime = null;
		Random random = new Random();
		int minFuelTime = getMinFuelTime().getMinute();

		int min = minFuelTime + 1;
		int range = getMaxFuelTime().getMinute() - getMinFuelTime().getMinute();
		int minute = min + random.nextInt(range);
		initialFuelTime = new Time (0, minute, 0);
		return initialFuelTime;
	}

	/**
	 * Return time required for take off.
	 * @return
	 */
	protected abstract Time getTakeOffDuration();

	/**
	 * Return time required for landing.
	 * @return
	 */
	protected abstract Time getLandingDuration();

	/**
	 * Return minimum fuel time of the plane.
	 * @return
	 */
	protected abstract Time getMinFuelTime();

	/**
	 * Return maximum fuel time of the plane.
	 * @return
	 */
	protected abstract Time getMaxFuelTime();

	/**
	 * Return a type of plane in a form of String.
	 * @return
	 */
	public abstract String getPlaneTypeName();

	/**
	 * 
	 * @param time
	 * @return
	 */
	public String PlaneData(Time time) {
		String planeData;
		planeData = getPlaneTypeName() + " #"  + id;
		planeData += "\tSatus: " + status.toString();
		planeData += "\nInitialised time " + getInitialisedTime() + "\n"; 
		
		switch (status) {
		case TO_DEPART:
			break;
		case TO_ARRIVE:
			planeData += "Initial fuel: " + getInitialFuelTime();
			planeData += "\tRemaining fuel: " + getCurrentFuelTime() + "\n";
			break;
		case TO_RETURN:
			planeData += "Initial fuel: " + getInitialFuelTime();
			planeData += "\tRemaining fuel: " + getCurrentFuelTime() + "\n";
			break;
		case TAKING_OFF:
			planeData += "Takeoff start time: " + getTakeOffStartTime();
			planeData += "\tRemaining take off: " + calculateRemainingTakeOffTime(time) + "\n";
			break;
		case LANDING:
			planeData += "Initial fuel: " + getInitialFuelTime();
			planeData += "\tRemaining fuel: " + getCurrentFuelTime() + "\n";
			planeData += "Landing start time: " + getLandingStartTime();
			planeData += "\tRemaining landing time: " + calculateRemainingLandingTime(time) + "\n"; 
			break;
		case DEPARTED:
			planeData += "Takeoff start time: " + getTakeOffStartTime();
			planeData += "\tRemaining takeoff time: " + calculateRemainingTakeOffTime(time) + "\n"; 
			planeData += "Departure time: " + getDepartureTime() + "\n";
			break;
		case ARRIVED:
			planeData += "Takeoff start time: " + getTakeOffStartTime();
			planeData += "\tRemaining take off: " + calculateRemainingTakeOffTime(time) + "\n";
			planeData += "Landing start time: " + getLandingStartTime();
			planeData += "\tremaining landing time: " + calculateRemainingLandingTime(time) + "\n"; 
			planeData += "Arrival time: " + getArrivalTime() + "\n";	
			break;
		case CRASHED:			
			planeData += "Crashed time: " + getTakeOffStartTime();
			break;
		default:
			break;
		}
		planeData += "waiting time: " + waitingTime + "\n";

		return planeData;
	}

}
