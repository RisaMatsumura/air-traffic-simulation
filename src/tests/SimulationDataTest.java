package tests;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import model.PlanesData;
import model.Time;
import model.plane.CommercialPlane;
import model.plane.Plane;
import model.plane.PlaneStatus;

public class SimulationDataTest {
	
	private PlanesData data;

	@Before
	public void setUp() throws Exception {
		data = new PlanesData();
		data.resetPlanesData();
	}

	@Test
	public void test() {
	}
	
	
	@Test
	public void testNumberOfPlanes() {
		Time firstTime = new Time(0, 0, 0);	
		Time secondTime = new Time(0, 0, 1);
		Plane firstToLandPlane;
		Plane secondToLandPlane;
		Plane firstToTakeOffPlane;
		Plane secondToTakeOffPlane;
		firstToLandPlane = new CommercialPlane(1, PlaneStatus.TO_ARRIVE, firstTime);
		secondToLandPlane = new CommercialPlane(0, PlaneStatus.TO_ARRIVE, secondTime);
		firstToTakeOffPlane = new CommercialPlane(1, PlaneStatus.TO_DEPART, firstTime);
		secondToTakeOffPlane = new CommercialPlane(0, PlaneStatus.TO_DEPART, secondTime);
		
		data.getRunwayQueue().add(firstToLandPlane);
		assertEquals(data.getTotalLanding(), 1);
//		assertEquals(data.getTotalTakeOff(), 0);
		data.getRunwayQueue().add(secondToLandPlane);
		assertEquals(data.getTotalLanding(), 2);
//		assertEquals(data.getTotalTakeOff(), 0);
	}
	

}
