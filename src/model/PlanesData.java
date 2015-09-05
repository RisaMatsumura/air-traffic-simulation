package model;

import java.util.ArrayList;
import java.util.Observable;
import java.util.PriorityQueue;

import model.plane.Plane;
import priority.PriorityLevel2;

/**
 * PlanesData class keeps track of data of all the planes
 * generated from start of the simulation to the current time.
 * @author risam
 *
 */
public class PlanesData extends Observable{

	private ArrayList<Plane> allPlanes;
	private PriorityQueue<Plane> runwayQueue;
	private ArrayList<Plane> arrivedPlanes;
	private ArrayList<Plane> departedPlanes;
	private ArrayList<Plane> crashedPlanes;

	private Plane currentLanding;
	private Plane currentTakeOff;
	private Plane currentTowing;

	private Time currentTime;
	private Time totalWaitingTime;
	
	/**
	 * Create a PlaneData object.
	 */
	public PlanesData() {
		setupNewData();
	}
	
	/**
	 * Set up new set of data.
	 */
	private void setupNewData() {
		setAllPlanes(new ArrayList<Plane>());
		setRunwayQueue(new PriorityQueue<Plane>(100, new PriorityLevel2()));
		setArrivedPlanes(new ArrayList<Plane>());
		setDepartedPlanes(new ArrayList<Plane>());
		setCrashedPlanes(new ArrayList<Plane>());

		setCurrentLanding(null);
		setCurrentTakeOff(null);
		setTotalWaitingTime(Time.TIME_ZERO);
	}
	
	/**
	 * Copy a PlanesData object
	 * @param data
	 */
	public PlanesData(PlanesData data) {
		setCurrentTime(data.getCurrentTime());
		
		ArrayList<Plane> allPlanes = new ArrayList<Plane>();
		PriorityQueue<Plane> runwayQueue = new PriorityQueue<Plane>(100, new PriorityLevel2());
		ArrayList<Plane> arrivedPlanes = new ArrayList<Plane>();
		ArrayList<Plane> departedPlanes = new ArrayList<Plane>();
		ArrayList<Plane> crashedPlanes = new ArrayList<Plane>();
		for (Plane plane : data.allPlanes) {
			allPlanes.add(plane.deepCopy());
		}
		for (Plane plane : data.runwayQueue) {
			runwayQueue.add(plane.deepCopy());
		}
		for (Plane plane : data.arrivedPlanes) {
			arrivedPlanes.add(plane.deepCopy());
		}
		for (Plane plane : data.departedPlanes) {
			departedPlanes.add(plane.deepCopy());
		}
		for (Plane plane : data.crashedPlanes) {
			crashedPlanes.add(plane.deepCopy());
		}
		setAllPlanes(allPlanes);
		setRunwayQueue(new PriorityQueue<Plane>(runwayQueue));
		setArrivedPlanes(new ArrayList<Plane>(arrivedPlanes));
		setDepartedPlanes(new ArrayList<Plane>(departedPlanes));
		setCrashedPlanes(new ArrayList<Plane>(crashedPlanes));

		setCurrentLanding(data.getCurrentLanding());
		setCurrentTakeOff(data.getCurrentTakeOff());
		setCurrentTowing(data.getCurrentTowing());

		setTotalWaitingTime(data.getTotalWaitingTime());
	}

	/**
	 * Set a queue of planes waiting to land and take off.
	 * @param runwayQueue
	 */
	public void setRunwayQueue(PriorityQueue<Plane> runwayQueue) {
		this.runwayQueue = runwayQueue;
	}

	/**
	 * Return a queue of planes waiting to land and take off.
	 * @return
	 */
	public PriorityQueue<Plane> getRunwayQueue() {
		return runwayQueue;
	}
	
	/**
	 * Set a list of all the planes generated.
	 * @param allPlanes
	 */
	public void setAllPlanes(ArrayList<Plane> allPlanes) {
		this.allPlanes = allPlanes;
	}

	/**
	 * Return a list of all the planes generated.
	 * @return
	 */
	public ArrayList<Plane> getAllPlanes() {
		return allPlanes;
	}
	
	/**
	 * Set a list of planes that have arrived.
	 * @param arrivedPlanes
	 */
	public void setArrivedPlanes(ArrayList<Plane> arrivedPlanes) {
		this.arrivedPlanes = arrivedPlanes;
	}

	/**
	 * Return a list of planes that have arrived.
	 * @return
	 */
	public ArrayList<Plane> getArrivedPlanes() {
		return arrivedPlanes;
	}

	/**
	 * Set a list of planes that have departed.
	 * @param departedPlanes
	 */
	public void setDepartedPlanes(ArrayList<Plane> departedPlanes) {
		this.departedPlanes = departedPlanes;
	}

	/**
	 * Return a list of planes that have departed.
	 * @return
	 */
	public ArrayList<Plane> getDepartedPlanes() {
		return departedPlanes;
	}

	/**
	 * Set a list of planes that have crashed.
	 * @param crashedPlanes
	 */
	public void setCrashedPlanes(ArrayList<Plane> crashedPlanes) {
		this.crashedPlanes = crashedPlanes;
	}

	/**
	 * Return a list of planes that have crashed.
	 * @return
	 */
	public ArrayList<Plane> getCrashedPlanes() {
		return crashedPlanes;
	}

	/**
	 * Set a plane that is currently landing.
	 * @param currentLanding
	 */
	public void setCurrentLanding(Plane currentLanding) {
		this.currentLanding = currentLanding;
	}

	/**
	 * Return a plane that is currently landing.
	 * @return
	 */
	public Plane getCurrentLanding() {
		return currentLanding;
	}

	/**
	 * Set a plane that is currently taking off.
	 * @param currentTakeOff
	 */
	public void setCurrentTakeOff(Plane currentTakeOff) {
		this.currentTakeOff = currentTakeOff;
	}

	/**
	 * Return a plane that is currently taking off.
	 * @return
	 */
	public Plane getCurrentTakeOff() {
		return currentTakeOff;
	}

	/**
	 * Set a plane that is currently towing a glider.
	 * @param currentTowing
	 */
	public void setCurrentTowing (Plane currentTowing) {
		this.currentTakeOff = currentTowing;
	}

	/**
	 * Return a plane that is currently towing a glider.
	 * @return
	 */
	public Plane getCurrentTowing() {
		return currentTowing;
	}
	
	/**
	 * Set current time of the PlanesData.
	 * @param time
	 */
	public void setCurrentTime(Time time) {
		this.currentTime = time;
	}

	/**
	 * Return current time of the PlanesData.
	 * @return
	 */
	public Time getCurrentTime() {
		return currentTime;
	}
	
	/**
	 * Set total sum of time that all the arrival and departure planes
	 * are waiting to land and take off.
	 * @param totalWaiting
	 */
	public void setTotalWaitingTime(Time totalWaiting) {
		this.totalWaitingTime = totalWaiting;
	}

	/**
	 * Return total sum of time that all the arrival and departure planes
	 * are waiting to land and take off.
	 * @return
	 */
	public Time getTotalWaitingTime() {
		return totalWaitingTime;
	}
	
	/**
	 * Return a number of planes generated.
	 * @return
	 */
	public int getTotalPlanes() {
		return allPlanes.size();
	}

	/**
	 * Return a number of planes waiting to land or take off.
	 * @return
	 */
	public int getTotalLanding() {
		return runwayQueue.size();
	}

	/**
	 * Return a number of planes that have arrived.
	 * @return
	 */
	public int getTotalArrived() {
		return arrivedPlanes.size();
	}

	/**
	 * Return a number of planes that have departed.
	 * @return
	 */
	public int getTotalDeparted() {
		return departedPlanes.size();
	}

	/**
	 * Return a number of planes that have crashed.
	 * @return
	 */
	public int getTotalCrashed() {
		return crashedPlanes.size();
	}

	public void notifyChange() {
		setChanged();
		notifyObservers(this);
	}

	/**
	 * Provide a PlanesData in a form of String.
	 * @return
	 */
	public String generateLog() {
		String log = "";
		log += "\nLog at " + currentTime.toString() + "---------------------";
		log += "\nTotal plane: " + getTotalPlanes();
		log += "\nTotal arrived: " + getTotalArrived();
		log += "\tTotal departed: " + getTotalDeparted();
		log += "\tTotal crashed: " + getTotalCrashed();
		log += "\tTotal waiting: " + getTotalWaitingTime();
		log += "\n\nA list of all planes-------\n";

		if (allPlanes.size() > 0) {
			for(Plane plane: allPlanes) {
				log += plane.PlaneData(currentTime);
				log += "\n";
			}
			if(runwayQueue.size() > 0) {
				log += "\n------ Planes in runway queue -----\n";
				for(Plane runwayPlane: runwayQueue) {
					log += runwayPlane.PlaneData(currentTime);
					log += "\n";
				}
			}
		}
		else {
			log += "There is no plane";
			log += "\n";
		}
		return log;
	}
	
	/**
	 * Reset PlanesData
	 */
	public void resetPlanesData() {
		setupNewData();
	}

}
