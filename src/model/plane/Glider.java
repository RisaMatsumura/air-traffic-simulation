package model.plane;

import model.Time;

public class Glider extends Plane{

	// Time required for landing and take off
	private static Time LANDING_DURATION = new Time(0, 4, 0);
	private static Time TAKEOFF_DURATION = new Time(0, 3, 0);
	
	// Minimum and maximum values for the remaining fuel time
	private static Time MIN_FUEL_TIME = new Time(0, 20, 0);
	private static Time MAX_FUEL_TIME = new Time (0, 40, 0);
	
	// A light plane which tows a glider
	private LightPlane towingPlane;

	public Glider(int id, PlaneStatus state, Time time) {
		super(id, state, time);
		if (state.equals(PlaneStatus.TO_DEPART)) {
			// generate a light plane that tows the glider
			setTowingPlane(new LightPlane(id+1, PlaneStatus.TO_TOW, time));
		}
	}
	
	/**
	 * Copy constructor for Glider
	 * @param plane
	 */
	public Glider(Glider plane) {
		super(plane);
		setTowingPlane(plane.towingPlane);
	}
	
	public void setTowingPlane(LightPlane towingPlane) {
		this.towingPlane = towingPlane;
	}

	public LightPlane getTowingPlane() {
		return towingPlane;
	}
	
	@Override
	protected Time getTakeOffDuration() {
		return TAKEOFF_DURATION;
	}

	@Override
	protected Time getLandingDuration() {
		return LANDING_DURATION;
	}

	@Override
	protected Time getMinFuelTime() {
		return MIN_FUEL_TIME;
	}

	@Override
	protected Time getMaxFuelTime() {
		return MAX_FUEL_TIME;
	}

	@Override
	public String getPlaneTypeName() {
		return "GLIDER";
	}	

}
