package controller;



import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;

import model.LogBook;
import model.PlanesData;
import model.Time;
import model.Timer;
import model.plane.Glider;
import model.plane.LightPlane;
import model.plane.Plane;
import model.plane.PlaneFactory;
import model.plane.PlaneStatus;

/**
 * RunwayManager class manages all the flights and their data
 * @author risam
 *
 */
public class RunwayManager extends Observable{

	private LogBook logBook;
	private PlanesData latestData, log;
	private PlaneFactory pFactory;

	private Timer timer;
	private Time currentTime;
	
	/**
	 * Create a Runway Manager
	 */
	public RunwayManager() {
		pFactory = new PlaneFactory();
	}

	/**
	 * Get a Logbook of the runway data
	 * @return
	 */
	public LogBook getLogBook() {
		return logBook;
	}

	/**
	 * Generate planes for specified duration of time
	 */
	public void generatePlanes(Time duration) {
		prepareData();

		while (currentTime.compareTo(duration) <= 0) {
			
			latestData.setCurrentTime(currentTime);

			generateDepartureAndArrival();
			setNewCurrentLandingOrTakeOff();
			log = new PlanesData(latestData);
			logBook.add(log);
			//			data.notifyChange();
			
			currentTime = timer.incrementTime();
			updateCurrentLanding();
			updateCurrentTakingOff();
			updateArrivalAndDepartureQueues();
		}
	}

	/**
	 * Prepare data before generating planes
	 */
	private void prepareData() {
		// Set current time to 0 and set up a timer with the current time.
		currentTime = Time.TIME_ZERO;
		timer = new Timer(currentTime);

		latestData = new PlanesData();
		logBook = new LogBook();

		pFactory.setSeed(generateSeed());
	}

	/**
	 * Generate a departure and/or arrival plane.
	 * The order depends on randomly generated probability.
	 */
	private void generateDepartureAndArrival() {
		PlaneStatus[] status = {PlaneStatus.TO_ARRIVE, PlaneStatus.TO_DEPART};

		int index = new Random().nextInt(1);
		
		generatePlane(status[index]);
		generatePlane(status[(status.length-1)-index]);
	}

	/**
	 * Generate a Plane object based on a probability and a state.
	 * @param state TODO
	 */
	private void generatePlane(PlaneStatus state) { 
		int id = latestData.getTotalPlanes() + 1;
		
		// Use the Factory class to generate a plane
		Plane newPlane = pFactory.getToArrivePlane(id, state, currentTime);

		if (newPlane != null) {
			latestData.getRunwayQueue().add(newPlane);
			latestData.getAllPlanes().add(newPlane);
		}
	}

	/**
	 * If there is no plane currently landing/taking off,
	 * set the highest priority plane to landing/taking off.
	 */
	private void setNewCurrentLandingOrTakeOff() {
		Plane currentLanding = latestData.getCurrentLanding();
		Plane currentTakeoff = latestData.getCurrentTakeOff();

		// Check no plane is currently landing/taking off
		if (currentLanding == null && currentTakeoff == null) {

			// Check if any plane is waiting to land/take off
			if (latestData.getRunwayQueue().size() > 0) {
				
				// Set a plane with the highest priority in the queue to landing/take off
				Plane plane = latestData.getRunwayQueue().poll();
				PlaneStatus status = plane.getState();

				// If waiting to arrive/return, set to a currently landing
				if (status.equals(PlaneStatus.TO_ARRIVE) || status.equals(PlaneStatus.TO_RETURN)) {
					plane.changeState(PlaneStatus.LANDING, currentTime);
					latestData.setCurrentLanding(plane);
				}
				// If waiting to depart, set to a currently taking off
				else if (status.equals(PlaneStatus.TO_DEPART)){
					plane.changeState(PlaneStatus.TAKING_OFF, currentTime);
					latestData.setCurrentTakeOff(plane);
				}
			}
		} 
	}

	/**
	 * If there is a plane currently landing, update it's data
	 * If the remaining landing time reached 0, add it to the arrived list
	 */
	private void updateCurrentLanding() {

		Plane currentLanding = latestData.getCurrentLanding();
		if (currentLanding != null) {
			Time remainingLandingTime = currentLanding.calculateRemainingLandingTime(currentTime);

			// Change the status if landing is completed
			if (remainingLandingTime.equalTo(Time.TIME_ZERO)) {
				currentLanding.changeState(PlaneStatus.ARRIVED, currentTime);
				latestData.setCurrentLanding(null);
				latestData.getRunwayQueue().remove(currentLanding);
				latestData.getArrivedPlanes().add(currentLanding);
			} 
			// Change the status if landing fuel ran out before landing completed
			else {
				Time currentFuelTime = currentLanding.calculateCurrentFuelTime(currentTime);

				if (currentFuelTime.compareTo(Time.TIME_ZERO)!= 1) {
					currentLanding.changeState(PlaneStatus.CRASHED, currentTime);
					latestData.getCrashedPlanes().add(currentLanding);
//					latestData.getRunwayQueue().remove(currentLanding);
					latestData.setCurrentLanding(null);
				}
			}
		} 
	}

	/**
	 * Check if a current take off has completed its take off
	 * and store 
	 */		
	private void updateCurrentTakingOff() {
		Plane currentTakeoff = latestData.getCurrentTakeOff();

		// Check a plane is currently taking off
		if (currentTakeoff != null){

			Time remainingTakeOffTime = currentTakeoff.calculateRemainingTakeOffTime(currentTime);

			// If take off is completed, change the status and add
			if (remainingTakeOffTime.equalTo(Time.TIME_ZERO)) {
				currentTakeoff.changeState(PlaneStatus.DEPARTED, currentTime);
				latestData.setCurrentTakeOff(null);
//				latestData.getRunwayQueue().remove(currentTakeoff);
				latestData.getDepartedPlanes().add(currentTakeoff);
				
				// Set the towing light plane to return if glider has taken off
				if (currentTakeoff instanceof Glider) {
					Glider towedGlider = (Glider) currentTakeoff;
					LightPlane towingPlane = towedGlider.getTowingPlane();
					towingPlane.changeState(PlaneStatus.TO_RETURN, towingPlane.getMaxReturnTime());
					latestData.getRunwayQueue().add(towingPlane);
				}
			}
		}
	}

	/**
	 * Update planes data currently in the runway queue
	 */
	private void updateArrivalAndDepartureQueues() {

		Time totalWaiting = Time.TIME_ZERO;
		Time timeSinceInitialised;
		ArrayList<Plane> crashedPlanes = new ArrayList<Plane>();

		for(Plane plane: latestData.getRunwayQueue()) {

			// Update arrival planes
			if (plane.getState().equals(PlaneStatus.TO_ARRIVE) || plane.getState().equals(PlaneStatus.TO_RETURN)) {
				Time currentFuelTime = (plane.calculateCurrentFuelTime(currentTime));
				plane.setCurrentFuelTime(currentFuelTime);

				// Update the arrival plane if there is no fuel left
				if (currentFuelTime.compareTo(Time.TIME_ZERO)!= 1) {
					plane.changeState(PlaneStatus.CRASHED, currentTime);
					latestData.getCrashedPlanes().add(plane);
					// Store in a list of crashed plane for later removal
					crashedPlanes.add(plane);
				} 

				// Update the arrival plane if there is no problem
				else {
					timeSinceInitialised = plane.calculateTimeSinceInitialised(currentTime);
					plane.setWaitingTime(timeSinceInitialised);
					totalWaiting = totalWaiting.add(plane.getWaitingTime());
				}
			} 
			// Update departure planes
			else{
				timeSinceInitialised = plane.calculateTimeSinceInitialised(currentTime);
				plane.setWaitingTime(timeSinceInitialised);
				totalWaiting = totalWaiting.add(plane.getWaitingTime());
			}
		}
		latestData.getRunwayQueue().removeAll(crashedPlanes);	
		latestData.setTotalWaitingTime(totalWaiting);
	}

	/**
	 * Generate seed for probability
	 * @return
	 */
	private long generateSeed() {
		Random random = new Random();
		return random.nextLong();
	}

	/**
	 * 
	 * @return
	 */
	public String getUpdatedData() {
		String updatedData = latestData.generateLog();
		return updatedData;
	}

}
