package controller;


import java.util.ArrayList;

import model.LogBook;
import model.Time;

/**
 * Simulator class repeats simulation for a certain number of times
 * in order to average the results.
 * It also keeps a set of LogBook for each simulation,
 * each LogBook stores a log at each time stamp
 * @author risam
 *
 */
public class Simulator {
	private RunwayManager pManager;
	private ArrayList<LogBook> logBooks;
	
	/**
	 * Create a Simulator object
	 */
	public Simulator() {
		pManager = new RunwayManager();
	}
	
	/**
	 * Run simulation for a given duration of times
	 * and a given number of times to average the stats
	 * @param count
	 * @param simulationDuration
	 */
	public void repeatSimulation(int count, Time simulationDuration) {
		logBooks = new ArrayList<LogBook>();
		for (int i = 0; i < count; i++) {
			// TODO put the first one back after testing
			pManager.generatePlanes(simulationDuration);
			logBooks.add(pManager.getLogBook());
		}
	}
	
	/**
	 * Get a set of LogBooks that has all the information
	 * collected by simulations run for specified number of times
	 * @return
	 */
	public ArrayList<LogBook> getLogBooks() {
		return logBooks;
	}
	
}

