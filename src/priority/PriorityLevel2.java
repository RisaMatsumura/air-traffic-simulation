package priority;


import java.util.Comparator;

import model.plane.Plane;
import model.plane.PlaneStatus;

/**
 * PriorityLevel2 gives priority to arrival planes.
 * @author risam
 *
 */
public class PriorityLevel2 implements Comparator<Plane>{

	@Override
	public int compare(Plane p1, Plane p2) {
		
		int firstPriority = getPriority(p1);
		int secondPriority = getPriority(p2);
		
		if (firstPriority > secondPriority) {
			return -1;
		} else if (firstPriority < secondPriority) {
			return 1;
		} else {
			return 0;
		}
	}
	
	/** 
	 * Assign priority number for status of a plane.
	 * @param plane
	 * @return
	 */
	private int getPriority(Plane plane) {
		int priority = -1; 
		PlaneStatus state = plane.getState();
		if (state.equals(PlaneStatus.TO_ARRIVE) || state.equals(PlaneStatus.TO_RETURN)) {
			priority = 1;
		} 
		return priority;
	}
	
}
