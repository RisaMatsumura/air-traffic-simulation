package controller;


import java.util.ArrayList;

import model.LogBook;
import model.PlanesData;
import model.Stats;
import model.Time;
import model.plane.Plane;

/**
 * DataHandler class transform data in various forms into a more appropriate form.
 * @author risam
 *
 */
public class DataHandler {
	
	public DataHandler() {
	}

	/**
	 * Produce a stats in a form of Stats object from a planes data
	 * @param data
	 * @return
	 */
	public Stats getStats(PlanesData data) {
		Stats stats = new Stats();

		stats.setTotalPlanes(data.getTotalPlanes());
		stats.setTotalArrived(data.getTotalArrived());
		stats.setTotalDeparted(data.getTotalDeparted());
		stats.setTotalCrashed(data.getTotalCrashed());
		stats.setTotalWaiting(data.getTotalWaitingTime());
		
		return stats;
	}

	/**
	 * Produce an averaged stats in a form of Stats object from a collection of Log books.
	 * @param logBooks
	 * @return
	 */
	public Stats getAverageStats(ArrayList<LogBook> logBooks) {
		Stats stats = new Stats();
		int count = logBooks.size();
		double totalPlanesSum = 0;
		double totalArrivedSum = 0;
		double totalDepartedSum = 0 ;
		double totalCrashedSum = 0;
		Time totalWaitingTimeSum = new Time(0);

		for (int i = 0; i < count; i++) {
			LogBook dataset = logBooks.get(i);
			PlanesData data = dataset.getLastData();
			
			totalPlanesSum += data.getTotalPlanes();
			totalArrivedSum += data.getTotalArrived();
			totalDepartedSum += data.getTotalDeparted();
			totalCrashedSum += data.getTotalCrashed();
			totalWaitingTimeSum = totalWaitingTimeSum.add(data.getTotalWaitingTime());
		}

		stats.setTotalPlanes(totalPlanesSum / count);
		stats.setTotalArrived(totalArrivedSum / count);
		stats.setTotalDeparted(totalDepartedSum / count);
		stats.setTotalCrashed(totalCrashedSum / count);
		stats.setTotalWaiting(totalWaitingTimeSum.devide(count));

		return stats;
	}
	
	/**
	 * Format stats in String to output in a text area.
	 * @return
	 */
	public String formatAverageStats(Stats stats) {
		String result = "\n---------------Averaged stats---------------\n";
		result += formatStatsBody(stats);
		result += "\n-------------------------------------------\n";
		return result;
	}
	
	/**
	 * Format logs in a log book into a text form.
	 * @param logBook
	 * @return
	 */
	public String formatLogBook(LogBook logBook) {
		String logBookText = "";
		for (int j = 0; j < logBook.getLogs().size(); j++) {
			PlanesData log = logBook.get(j);
			logBookText += formatPlanesDataLog(log);
		}
		return logBookText;
	}
	
	/**
	 * Format a planes data log into a text form
	 * @param data
	 * @return
	 */
	private String formatPlanesDataLog(PlanesData data) {
		String runwayData = formatRunwayDataHeader(data);
		runwayData += formatPlanesDataBody(data);
		runwayData += "\n-------------------------------------------\n";
		return runwayData;
	}
	
	/**
	 * Format a main body of a planes data log text
	 * @param data
	 * @return
	 */
	private String formatPlanesDataBody(PlanesData data) {
		String body = "\n---------------Plane list---------------\n";
		
		for (int i = 0; i < data.getAllPlanes().size(); i++) {
			Plane plane = data.getAllPlanes().get(i);
			int id = plane.getId();
			String type = plane.getPlaneTypeName();
			String status = plane.getState().toString();
			String initialisedTime = formatTime(plane.getInitialisedTime());
			String waitingTime = formatTime(plane.getWaitingTime());
			
			String initialFuelTime = formatTime(plane.getInitialFuelTime());
			String currentFuelTime = formatTime(plane.getCurrentFuelTime());
			
			String landingStartTime = formatTime(plane.getLandingStartTime());
			String takeOffStartTime = formatTime(plane.getTakeOffStartTime());
			String arrivalTime = formatTime(plane.getArrivalTime());
			String departureTime = formatTime(plane.getDepartureTime());
			String crashedTime = formatTime(plane.getCrashedTime());
			
			body += "\n" + type + " " + id + ": " + status;
			body += "\nInitialised time: " + initialisedTime;
			body += "\tWaiting time: " + waitingTime;
			body += "\nInitial fuel time: " + initialFuelTime;
			body += "\tCurrent fuel time: " + currentFuelTime;
			body += "\nLanding start time: " + landingStartTime;
			body += "\tArrival time: " + arrivalTime;
			body += "\ntakeoff start time: " + takeOffStartTime;
			body += "\tDeparture time: " + departureTime;
			body += "\nCrashed time: " + crashedTime;
		}
		return body;
	}
	
	// TODO: can't you change toString in Time class instead?
	private String formatTime(Time time) {
		String timeString= null;
		if (time != null) {
			timeString = time.toString();
		}
		return timeString;
	}
	
	private String formatRunwayDataHeader(PlanesData data) {
		String currentTime = data.getCurrentTime().toString();
		int totalPlanes = data.getTotalPlanes();
		int totalArrived = data.getTotalArrived();
		int totalDeparted = data.getTotalDeparted();
		int totalCrashed = data.getTotalCrashed();
		String waitingTime = data.getTotalWaitingTime().toString();
		
		String header = "";
		header += "\n===============Data at " + currentTime + "===============\n";
		header += "\nTotal number of planes: " + totalPlanes;
		header += "\nTotal number of successful landing: " + totalArrived;
		header += "\nTotal number of successful take off: " + totalDeparted;
		header += "\nTotal number of plane crash: " + totalCrashed;
		header += "\nTotal waiting time: " + waitingTime;
		header += "\n";
		
		return header;
	}
	
	public String formatStats(Stats stats, int count) {
		String statsText = "\n---------------Stats for simulaion " + count + "---------------\n";
		statsText += formatStatsBody(stats);
		return statsText;
	}

	private String formatStatsBody(Stats stats) {
		String statsBody = "";
//		statsBody += "Frequency of commercial planes: " + pFactory.getCProbability();
//		statsBody += "\nFrequency of light planes: " + pFactory.getLProbability();
//		statsBody += "\nFrequency of gliders: " + pFactory.getGProbability();
//		statsBody += "\n";
		statsBody += "\nTotal number of planes: " + stats.getTotalPlanes();
		statsBody += "\nTotal number of successful landing: " + stats.getTotalArrived();
		statsBody += "\nTotal number of successful take off: " + stats.getTotalDeparted();
		statsBody += "\nTotal number of plane crash: " + stats.getTotalCrashed();
		statsBody += "\nTotal waiting time: " + stats.getTotalWaiting();
		return statsBody;
	}
}
