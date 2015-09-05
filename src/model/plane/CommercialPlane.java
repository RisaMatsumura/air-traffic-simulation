package model.plane;

import model.Time;

/**
 * Commercial plane takes 3 minute to land and 2 minute to take off.
 * Commercial planes that are due to land will arrive with a random amount of fuel 
 * corresponding to an additional flying time between 20 and 40 minutes 
 * @author risam
 */
public class CommercialPlane extends Plane{

	// Minimum and maximum values for the remaining fuel time
	private static Time MIN_FUEL_TIME = new Time(0, 20, 0);
	private static Time MAX_FUEL_TIME = new Time (0, 40, 0);

	// Time required for landing and take-off
	private static Time LANDING_DURATION = new Time(0, 3, 0);
	private static Time TAKEOFF_DURATION = new Time(0, 2, 0);

	public CommercialPlane(int id, PlaneStatus state, Time time) {
		super(id, state, time);
	}

	public CommercialPlane(CommercialPlane plane) {
		super(plane);
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
		return "COMMERCIAL PLANE";
	}
}
