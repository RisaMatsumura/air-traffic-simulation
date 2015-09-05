package tests;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import model.PlanesData;
import model.Time;
import model.Timer;
import model.plane.CommercialPlane;
import model.plane.Plane;
import model.plane.PlaneStatus;

public class PlaneManagerTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		PlanesData data = new PlanesData();
		Plane firstPlaneToLand;
		Plane secondPlaneToLand;
		Plane firstPlaneToTakeOff;
		Plane secondPlaneToTakeOff;
		
		Time firstTime = new Time(0, 2, 0);
		Time secondTime = new Time(0,4, 0);
		Time thirdTime = new Time(0, 6, 0);
		Time fourthTime = new Time(0, 8, 0);

		Time currentTime = Time.TIME_ZERO;
		Timer timer = new Timer(currentTime);
		Time stopTime = new Time(0, 20, 0);
		
		firstPlaneToTakeOff = new CommercialPlane(1, PlaneStatus.TO_DEPART, firstTime);
		firstPlaneToLand = new CommercialPlane(2, PlaneStatus.TO_ARRIVE, secondTime);
		secondPlaneToTakeOff = new CommercialPlane(3, PlaneStatus.TO_DEPART, thirdTime);
		secondPlaneToLand = new CommercialPlane(4, PlaneStatus.TO_ARRIVE, fourthTime);
		
		while(currentTime.compareTo(stopTime) == 1) {
			if (currentTime.equalTo(firstTime)) {
				data.getRunwayQueue().add(firstPlaneToTakeOff);
			}
			timer.incrementTime();
		}
		
		assertEquals(data.getRunwayQueue().peek(), firstPlaneToTakeOff);
	}

}
