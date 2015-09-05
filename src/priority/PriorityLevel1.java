package priority;


import java.util.Comparator;

import model.Time;
import model.plane.Plane;
import model.plane.PlaneStatus;

/**
 * PriorityLevel1 gives priority to arrival planes that have little remaining fuel.
 * @author risam
 *
 */
public class PriorityLevel1 implements Comparator<Plane>{

	@Override
	public int compare(Plane p1, Plane p2) {
		
		int comparisonValue = 0;

		/**
		 * TODO This part hasn't been implemented properly
		 * 		Needs to put higher priority to planes landing & has low fuel
		 * 		over planes taking off
		 * 
		 * NOTE: maybe below is okay and you just need to use two queues 
		 * and put everything in landing queue before takeoff queue
		 */
		if (p1.getState().equals(PlaneStatus.LANDING)) {
			// Compare remaining fuel
			Time p1FuelTime = p1.getCurrentFuelTime();
			Time p2FuelTime = p2.getCurrentFuelTime();
			comparisonValue = p1FuelTime.compareTo(p2FuelTime);	
		} else {
			Time p1WaitingTime = p1.getWaitingTime();
			Time p2WaitingTime = p2.getWaitingTime();
			comparisonValue = -1 * (p1WaitingTime.compareTo(p2WaitingTime));
		}
		return comparisonValue;
	}
	
	/** 
	 * Assign priority number for status of a plane.
	 * @param plane
	 * @return
	 */
	private int getPriority(Plane plane) {
		int priority = -1; 
		PlaneStatus state = plane.getState();
		return priority;
	}
	
}
