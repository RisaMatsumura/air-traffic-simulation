package controller;

import java.util.ArrayList;

import model.LogBook;
import model.Stats;
import model.Time;
import model.plane.PlaneFactory;

/**
 * TextController handles actions from the GUI components. 
 * 
 * @author risam_000
 * 
 */

public class TextController {

	private static TextController instance = null;
	private Simulator simulator;
	private PlaneFactory pFactory;
	private RunwayManager pManager;
	private DataHandler dHandler;
	private ArrayList<LogBook> logBooks;

	private TextController() {
		pFactory = new PlaneFactory();
		simulator = new Simulator();
		dHandler = new DataHandler();
	}

	public static TextController getInstance() {
		if (instance == null) {
			instance = new TextController ();
		}
		return instance;
	}


	/**
	 * Get averaged stats for simulation that runs for 
	 * the specified duration, number of times and frequency of commercial planes.
	 * 
	 * @param duration
	 * @param repeatCount
	 * @param cProbability
	 * @return
	 */
	public String getAveragedStats(Time duration, int repeatCount, double cProbability) {
		pFactory.setCProbability(cProbability);
		simulator.repeatSimulation(repeatCount, duration);
		logBooks = simulator.getLogBooks();
		Stats averagedStats = dHandler.getAverageStats(logBooks);
		String averagedStatsText = dHandler.formatAverageStats(averagedStats);
		return averagedStatsText;	
	}

	/**
	 * Get details of the result of a simulation
	 * @return
	 */
	public String getDetails() {
		String details ="";
		for (int i = 0; i < logBooks.size(); i++) {
			LogBook logBook= logBooks.get(i);
			
			// Get overall stats for each simulation
			Stats stats = dHandler.getStats(logBook.getLastData());
			details += dHandler.formatStats(stats, i);
			
			// Get details at each timestamp
			details += dHandler.formatLogBook(logBook);
		}
		return details;
	}

	// TODO do you need it?
//	public String printNewData(RunwayData data) {
//		String newData = "\n-----Simulation Summary-----\n";
//		newData += "Time: " + data.getCurrentTime() + "\n";
//		newData += "Total number of planes: " + data.getTotalPlanes() + "\n";
//		String landingId = "N/A";
//		String takingOffId = "N/A";
//		if (data.getCurrentLanding() != null)
//			landingId = String.valueOf(data.getCurrentLanding().getId());
//		if (data.getCurrentTakeOff() != null)
//			takingOffId = String.valueOf(data.getCurrentTakeOff().getId());
//		newData += "Landing ID: " + landingId;
//		newData += "\tTaking off ID: " + takingOffId + "\n";
//		newData += "Arrived: " + data.getTotalArrived();
//		newData += "\tDeparted: " + data.getTotalDeparted() + "\n";
//		newData += "Crashed: " + data.getTotalCrashed() + "\n";
//		newData += "Total waiting time: " + data.getTotalWaitingTime();
//		newData += "\n---------------------------------------";
//		newData += "\n\n";
//		newData += pManager.getUpdatedData();
//		newData +="-----------------------------------------------------------------------------------------------\n";
//		return newData;
//	}

	/**
	 * Reset all the data so that a new simulation can be run
	 */
	public void resetSimulation() {
		logBooks = null;	
	}
}
