package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import model.Time;
import model.plane.CommercialPlane;
import model.plane.Plane;
import model.plane.PlaneStatus;

public class PlaneTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testConstructors() {
		Time currentTime;
		currentTime = new Time(0);	
		Plane firstConstructor;
		Plane secondConstructor;
		Plane thirdConstructor;
		firstConstructor = new CommercialPlane(1, PlaneStatus.LANDING, currentTime);
		
		
	}

	@Test
	public void testGetRemainingLandingTime() {
		Time landingStartTime = new Time(0, 5, 0);
		Time currentTime = new Time(0, 7, 0);	
		Time expectedRemainingLandingTime = new Time(0, 1, 0);
		
		Plane plane = new CommercialPlane(1, PlaneStatus.LANDING, currentTime);
		plane.setLandingStartTime(landingStartTime);
		assertTrue(plane.calculateRemainingLandingTime(currentTime).equalTo(expectedRemainingLandingTime));
		
	}
	
	@Test
	public void testGetRemainingTakeOffTime() {
		Time currentTime = new Time(0, 6, 0);
		Time takeOffStartTime = new Time (0, 5, 0);
		Time expectedRemainingLandingTime = new Time(0, 1, 0);
		
		Plane plane = new CommercialPlane(1, PlaneStatus.TAKING_OFF, currentTime);
		plane.setTakeOffStartTime(takeOffStartTime);
		assertTrue(plane.calculateRemainingTakeOffTime(currentTime).equalTo(expectedRemainingLandingTime));
		
	}
	
	@Test
	public void testGetCurrentFuelTime() {
		Time initialisedTime = new Time(0, 0, 0);
		Time currentTime = new Time(0, 10, 0);
		Time initialFuelTime = new Time (0, 30, 0);
		Time expectedRemainingFuelTime = new Time(0, 20, 0);
		
		Plane plane = new CommercialPlane(1, PlaneStatus.TO_ARRIVE, initialisedTime);
		plane.setInitialFuelTime(initialFuelTime);
		assertTrue(plane.calculateCurrentFuelTime(currentTime).equalTo(expectedRemainingFuelTime));
		
	}
	
	@Test
	public void testChangeState() {
		Time initialisedTime = new Time(0, 0, 0);
		Time currentTime = new Time(0, 10, 0);
		Time initialFuelTime = new Time (0, 30, 0);
		Time expectedRemainingFuelTime = new Time(0, 20, 0);
		
		Plane firstPlane = new CommercialPlane(1, PlaneStatus.TO_DEPART, initialisedTime);
		Plane secondPlane = new CommercialPlane(2, PlaneStatus.TO_ARRIVE, initialisedTime);
		assertEquals(firstPlane.getState(), PlaneStatus.TO_DEPART);
		assertTrue(firstPlane.getWaitingTime().equalTo(initialisedTime));
		assertEquals(secondPlane.getState(), PlaneStatus.TO_ARRIVE);
		assertTrue(secondPlane.getInitialFuelTime() != null);
		assertTrue(secondPlane.getWaitingTime().equalTo(initialisedTime));
		
	}
	
	

}
