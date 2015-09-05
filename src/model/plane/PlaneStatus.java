package model.plane;

/**
 * PlaneStatus class provides a set of status that planes can have.
 * It also provide a method to get each status in a form of String.
 * @author risam
 *
 */
public enum PlaneStatus {
	
	// All the status that plane object can have.
	TO_DEPART, 
	TO_ARRIVE, 
	TAKING_OFF, 
	LANDING, 
	DEPARTED, 
	ARRIVED, 
	CRASHED,
	TO_TOW,
	TO_RETURN;
	
	/**
	 * Get name of status in a form of String.
	 */
	public String toString() {
		String state = "";
		switch(this) {
		case TO_DEPART: 
			state = "WAITING TO TAKE OFF";
			break;
		case TO_ARRIVE: 
			state = "WAITING TO LAND";
			break;
		case TAKING_OFF:
			state = "CURRENTLY TAKING OFF";
			break;
		case LANDING: 
			state = "CURRENTLY LANDING";
			break;
		case DEPARTED: 
			state = "DEPARTED";
			break;
		case ARRIVED: 
			state = "ARRIVED";
			break;
		case CRASHED:
			state = "CRASHED";
			break;
		case TO_TOW:
			state = "WAITING TO TAKE OFF FOR TOWING A GLIDER";
			break;
		case TO_RETURN: 
			state = "WAITING TO RETURN";
			break;
		}
		return state;
	}
}

