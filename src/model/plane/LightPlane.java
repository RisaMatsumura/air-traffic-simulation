package model.plane;

import model.Time;

/**
 * LightPlane
 * 
 * @author risam
 *
 */
public class LightPlane extends Plane{

	// Minimum and maximum values for the remaining fuel time
	private static Time MIN_FUEL_TIME = new Time(0, 10, 0);
	private static Time MAX_FUEL_TIME = new Time (0, 20, 0);
	private static Time MAX_RETURN_TIME = new Time(0, 20, 0);

	// Time required for landing and take off
	protected static Time TAKEOFF_DURATION = new Time(0, 2, 0);
	protected static Time LANDING_DURATION = new Time(0, 3, 0);

	public LightPlane(int id, PlaneStatus state, Time time) {
		super(id, state, time);
	}

	/**
	 * Copy constructor for LightPlane
	 * @param plane
	 */
	public LightPlane(LightPlane plane) {
		super(plane);
	}

	@Override
	protected void setToArriveOrDepart(PlaneStatus state) {
		super.setToArriveOrDepart(state);
		switch(state) {
		case TO_TOW:
			setWaitingTime(Time.TIME_ZERO);
			break;
		default:
			break;
		}
	}

	@Override
	public void changeState(PlaneStatus state, Time currentTime) {
		super.changeState(state, currentTime);
		switch(state) {
		case TO_RETURN:
			LightPlane plane = (LightPlane) this;
			setInitialFuelTime(plane.getMaxReturnTime());
			setCurrentFuelTime(initialFuelTime);
			break;
		default:
			break;
		}

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

	public Time getMaxReturnTime() {
		return MAX_RETURN_TIME;
	}

	@Override
	public String getPlaneTypeName() {
		return "LIGHT PLANE";
	}
}
