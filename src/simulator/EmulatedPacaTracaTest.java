package simulator;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author Sonia Rode
 * 
 * Unit tests for EmulatedPacaTraca
 *
 */
public class EmulatedPacaTracaTest {
	
	public static final String SENSOR_ID = "Fluffy";
	
	EmulatedPacaTracaFactory factory;
	PacaTraca simulator;
	PacaTraca simulatorSpeedShifter;

	@Before
	public void setUp() throws Exception {
		factory = new EmulatedPacaTracaFactory();
		simulator = factory.createPacaTraca(SENSOR_ID);
		//simulatorSpeedShifter = factory.createPacaTracaSpeedChange(SENSOR_ID);
	}

	/*
	 * Test that the latitude reading changed when the alpaca is moving
	 */
	@Test
	public void getLatitudeAlpacaMovingTest() {
		
		fail("Need EmulatedPacaTraca that always moves; "
				+ "not yet implemented in factory");
		float latitude1 = simulator.getLatitudeDecimalDegrees();
		waitForAlpacaToMove();
		float latitude2 = simulator.getLatitudeDecimalDegrees();
		
		// The alpaca should have moved, so the latitude should have changed
		assertTrue("Latitudes should be different", 
				Math.abs(latitude1 - latitude2) > 0.000000000);
	}

	/*
	 * Test that the longitude reading changed when the alpaca is moving
	 */
	@Test
	public void getLongitudeAlpacaMovingTest() {
		
		fail("Need EmulatedPacaTraca that always moves; "
				+ "not yet implemented in factory");
		float longitude1 = simulator.getLongitudeDecimalDegrees();
		waitForAlpacaToMove();
		float longitude2 = simulator.getLatitudeDecimalDegrees();
		
		// The alpaca should have moved, so the longitude should have changed
		assertTrue("Longitudes should be different", 
				Math.abs(longitude1 - longitude2) > 0.000000000);
	}
	
	/*
	 * Test that the speed reading of the alpaca changes. Uses a simulator
	 * where the alpaca changed its speed each time it moves.
	 */
	@Test
	public void speedChangesTest() {
		
		fail("Need EmulatedPacaTraca that always changes speed; "
				+ "not yet implemented in factory");
		float speed1 = simulator.getSpeed();
		System.out.println("First speed is "+speed1);
		waitForAlpacaToMove();
		float speed2 = simulator.getSpeed();
		System.out.println("Second speed is "+speed2);
		
		// The alpaca should have changed speed
		assertTrue("Speeds should be different", speed1 != speed2);
	}
	
	/*
	 * Test that the direction of the alpaca changes by calculating the angle
	 * of movement on two subsequent steps and ensuring they are different.
	 */
	@Test
	public void directionChangesTest(){
		
		fail("Need EmulatedPacaTraca that always changes heading; "
				+ "not yet implemented in factory");
		float angleStep1 = simulator.getCourse();
		System.out.println("First angle is "+angleStep1);
		waitForAlpacaToMove();
		
		float angleStep2 = simulator.getCourse();
		System.out.println("Second angle is "+angleStep2);
		
		// The angle of the two steps should be different
		assertTrue("Angles should be different", angleStep1 != angleStep2);
	}
	
	private void waitForAlpacaToMove(){
		try {
			Thread.sleep(MotionRunnable.THREAD_SLEEP_TIME_MILLIS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
