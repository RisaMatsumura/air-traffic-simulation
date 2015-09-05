package model;

import java.util.ArrayList;

/**
 * LogBook keeps all the logs of planes data at each time increment.
 * 
 * @author risam
 *
 */
public class LogBook{
	
	ArrayList<PlanesData> logs;
	
	/**
	 * Create a LogBook object with an new ArrayList of PlanesData
	 */
	public LogBook() {
		logs = new ArrayList<PlanesData>();
	}

	/**
	 * Get the LogBook
	 * @return
	 */
	public ArrayList<PlanesData> getLogs() {
		return logs;
	}
	
	/**
	 * Add a PlanesData object to the LogBook
	 * @param data
	 */
	public void add(PlanesData data) {
		logs.add(data);
	}
	
	/**
	 * Get a PlanesData object with an index specified in the parameter
	 * @param i
	 * @return
	 */
	public PlanesData get(int i) {
		return logs.get(i);
	}
	
	/**
	 * Get the last PlanesData stored in the LogBook.
	 * The last PlanesData is the overall result of a simulation.
	 * @return
	 */
	public PlanesData getLastData() {
		return logs.get(logs.size()-1);
	}

}
