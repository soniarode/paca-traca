package simulator;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class EmulatedPacaTracaTest {
	
	public static final String SENSOR_ID = "Fluffy";
	
	EmulatedPacaTracaFactory factory;
	PacaTraca simulator;
	PacaTraca simulatorSpeedShifter;

	@Before
	public void setUp() throws Exception {
		factory = new EmulatedPacaTracaFactory();
		simulator = factory.createPacaTraca(SENSOR_ID);
		simulatorSpeedShifter = factory.createPacaTracaSpeedChange(SENSOR_ID);
	}

	/*
	 * Test that the latitude reading changed when the alpaca is moving
	 */
	@Test
	public void getLatitudeAlpacaMovingTest() {
		float latitude1 = simulator.getLatitudeDecimalDegrees();
		try {
			Thread.sleep(MotionRunnable.MAX_MILLIS_BETWEEN_LOCATION_CHANGE);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		float latitude2 = simulator.getLatitudeDecimalDegrees();
		
		// The alpaca should have moved, so the latitude should have changed
		assertTrue("Latitudes should be different", latitude1 != latitude2);
	}

	/*
	 * Test that the longitude reading changed when the alpaca is moving
	 */
	@Test
	public void getLongitudeAlpacaMovingTest() {
		float longitude1 = simulator.getLongitudeDecimalDegrees();
		try {
			Thread.sleep(MotionRunnable.MAX_MILLIS_BETWEEN_LOCATION_CHANGE);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		float longitude2 = simulator.getLatitudeDecimalDegrees();
		
		// The alpaca should have moved, so the longitude should have changed
		assertTrue("Longitudes should be different", longitude1 != longitude2);
	}
	
	/*
	 * Test that the speed reading of the alpaca changes. Uses a simulator
	 * where the alpaca changed its speed each time it moves.
	 */
	@Test
	public void speedChangesTest() {
		float speed1 = simulatorSpeedShifter.getSpeed();
		System.out.println("First speed is "+speed1);
		try {
			Thread.sleep(MotionRunnable.MAX_MILLIS_BETWEEN_LOCATION_CHANGE);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		float speed2 = simulatorSpeedShifter.getSpeed();
		System.out.println("Second speed is "+speed2);
		
		// The alpaca should have changed speed
		assertTrue("Speeds should be different", speed1 != speed2);
	}
}
