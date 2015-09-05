package model.plane;

import java.util.Random;

import model.Time;

/**
 * TODO seed needs to be in certain range to produce realistic probability?
 * @author risam
 *
 */
public class PlaneFactory{
	
	private static double cProbability;
	private static double L_PROBABILITY = 0.005;
	private static double G_PROBABILITY = 0.002;
	private long seed;
	
	/**
	 * Generate and return a plane object with given id, status and intialised time
	 * based on the probabilities.
	 * @param id
	 * @param status
	 * @param time
	 * @return
	 */
	public Plane getToArrivePlane(int id, PlaneStatus status, Time time) {
		Plane newPlane = null;
		double randomNumber = generateProbability();

		if (cProbability > randomNumber) {

			if (G_PROBABILITY > randomNumber) {
				newPlane = new Glider(id, status, time);
			} else if (L_PROBABILITY > randomNumber) {
				newPlane = new LightPlane(id, status, time);
			} else {
				newPlane = new CommercialPlane(id, status, time);
			}
		} 
		return newPlane;
	}
	
	/**
	 * Set probability of commercial planes.
	 * @param probability
	 */
	public void setCProbability(double probability) {
		cProbability = probability;
	}
	
	/**
	 * Return probability of commercial planes.
	 * @return
	 */
	public double getCProbability() {
		return cProbability;
	}
	
	/**
	 * Return probability of light planes.
	 * @return
	 */
	public double getLProbability() {
		return L_PROBABILITY;
	}
	
	/**
	 * Return probability of gliders.
	 * @return
	 */
	public double getGProbability() {
		return G_PROBABILITY;
	}
	
	/**
	 * Set seed for generating random numbers.
	 * @param seed
	 */
	public void setSeed(long seed) {
		this.seed = seed;
	}
	
	/**
	 * Generate a probability between 0 and 1.
	 * @return
	 */
	private double generateProbability () {
		Random random = new Random();
		double randomNumber = random.nextDouble();
		return randomNumber;
	}
}
