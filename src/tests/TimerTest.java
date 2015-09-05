package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import model.Time;
import model.Timer;

public class TimerTest {
	private Timer timer;
	private Time time;

	@Before
	public void setUp() throws Exception {
		time = Time.TIME_ZERO;
		timer = new Timer(time);
	}
	@Test
	public void testTimeConstructors() {
		Time firstConstruct;
		Time secondConstruct;
		
		firstConstruct = new Time(3600);
		secondConstruct= new Time(1, 0, 0);
		
		assertEquals(firstConstruct.getHour(), secondConstruct.getHour());
		assertEquals(firstConstruct.getMinute(), secondConstruct.getMinute());
		assertEquals(firstConstruct.getSecond(), secondConstruct.getSecond());
		assertEquals(firstConstruct.getTimeInSeconds(), secondConstruct.getTimeInSeconds());
		
		firstConstruct = new Time(60);
		secondConstruct= new Time(0, 1, 0);
		
		assertEquals(firstConstruct.getHour(), secondConstruct.getHour());
		assertEquals(firstConstruct.getMinute(), secondConstruct.getMinute());
		assertEquals(firstConstruct.getSecond(), secondConstruct.getSecond());
		assertEquals(firstConstruct.getTimeInSeconds(), secondConstruct.getTimeInSeconds());
		
		firstConstruct = new Time(1);
		secondConstruct= new Time(0, 0, 1);
		
		assertEquals(firstConstruct.getHour(), secondConstruct.getHour());
		assertEquals(firstConstruct.getMinute(), secondConstruct.getMinute());
		assertEquals(firstConstruct.getSecond(), secondConstruct.getSecond());
		assertEquals(firstConstruct.getTimeInSeconds(), secondConstruct.getTimeInSeconds());
		
	}
	
	@Test
	public void testTimeAtZero() {
		timer.reset();
		Time currentTime = timer.getCurrentTime();
		Time currentTime2 = new Time(0, 0, 0);
		
		assertEquals(time.getHour(), currentTime.getHour());
		assertEquals(time.getMinute(), currentTime.getMinute());
		assertEquals(time.getSecond(), currentTime.getSecond());
		assertEquals(time.getTimeInSeconds(), currentTime.getTimeInSeconds());
		
		assertEquals(time.getHour(), currentTime2.getHour());
		assertEquals(time.getMinute(), currentTime2.getMinute());
		assertEquals(time.getSecond(), currentTime2.getSecond());
		assertEquals(time.getTimeInSeconds(), currentTime2.getTimeInSeconds());
	}
	
	@Test
	public void testAdd() {	
		Time currentTime;
		Time additionalTime;
		Time expectedTime;
		Time newTime;
		
		currentTime = new Time(0);
		additionalTime = new Time(0, 0, 30);	
		expectedTime = new Time(0, 0, 30);
		newTime = currentTime.add(additionalTime);
		assertEquals(newTime.getHour(), expectedTime.getHour());
		assertEquals(newTime.getMinute(), expectedTime.getMinute());
		assertEquals(newTime.getSecond(), expectedTime.getSecond());
		assertEquals(newTime.getTimeInSeconds(), expectedTime.getTimeInSeconds());	
		
		currentTime = new Time(0, 30, 0);
		additionalTime = new Time(0, 30, 30);
		expectedTime = new Time(1, 0, 30);
		newTime = currentTime.add(additionalTime);
		assertEquals(newTime.getHour(), expectedTime.getHour());
		assertEquals(newTime.getMinute(), expectedTime.getMinute());
		assertEquals(newTime.getSecond(), expectedTime.getSecond());
		assertEquals(newTime.getTimeInSeconds(), expectedTime.getTimeInSeconds());	
	
	}
	
	@Test
	public void testSubtract() {	
		Time currentTime;
		Time subtractedTime;
		Time expectedTime;
		Time newTime;
		
		currentTime = new Time(0, 0, 30);
		subtractedTime = new Time(0, 0, 30);	
		expectedTime = new Time(0, 0, 0);
		newTime = currentTime.subtract(subtractedTime);
		assertEquals(newTime.getHour(), expectedTime.getHour());
		assertEquals(newTime.getMinute(), expectedTime.getMinute());
		assertEquals(newTime.getSecond(), expectedTime.getSecond());
		assertEquals(newTime.getTimeInSeconds(), expectedTime.getTimeInSeconds());	
		
		currentTime = new Time(0, 10, 0);
		subtractedTime = new Time(0, 5, 30);
		expectedTime = new Time(0, 4, 30);
		newTime = currentTime.subtract(subtractedTime);
		assertEquals(newTime.getHour(), expectedTime.getHour());
		assertEquals(newTime.getMinute(), expectedTime.getMinute());
		assertEquals(newTime.getSecond(), expectedTime.getSecond());
		assertEquals(newTime.getTimeInSeconds(), expectedTime.getTimeInSeconds());	
	
	}
	
	@Test
	public void testCompareTo() {
		Time currentTime;
		Time futureTime;
		
		currentTime = new Time(0, 0, 0);
		futureTime = new Time(0, 5, 0);
		assertEquals(currentTime.compareTo(futureTime), -1);
		assertEquals(futureTime.compareTo(currentTime), 1);
		assertEquals(currentTime.compareTo(currentTime), 0);
	}
	
	@Test
	public void testTimeIncrement() {
		Time currentTime;
		Time expectedTime;
		currentTime = timer.getCurrentTime();
		expectedTime = new Time(0, 0, 0);
		assertTrue(currentTime.equalTo(expectedTime));
		
		timer.incrementTime();
		currentTime = timer.getCurrentTime();
		expectedTime = new Time(0, 0, 30);
		assertTrue(currentTime.equalTo(expectedTime));
	}
	

}
