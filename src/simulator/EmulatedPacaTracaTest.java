package simulator;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author Sonia Rode
 * 
 * Unit tests for EmulatedPacaTraca.
 * Indirectly tests MotionRunnable and TemperatureRunnable.
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
	}

	/*
	 * Test that either the latitude or longitude reading changed when the 
	 * alpaca is moving
	 */
	@Test
	public void testLatLonChangesWhileMoving() {
		
		simulator = factory.createPacaTracaMoveNormalOnly(SENSOR_ID);
		float latitude1 = simulator.getLatitudeDecimalDegrees();
		float longitude1 = simulator.getLongitudeDecimalDegrees();
		waitForAlpacaToMove(1);
		float latitude2 = simulator.getLatitudeDecimalDegrees();
		float longitude2 = simulator.getLatitudeDecimalDegrees();
		
		// The alpaca should have moved, so the latitude or longitude should 
		// have changed
		assertTrue("Either latitude or longitude should be different", 
				(Math.abs(longitude1 - longitude2) > 0.000000000) ||
				Math.abs(latitude1 - latitude2) > 0.000000000);
	}
	
	/*
	 * Test that the speed reading is positive when the alpaca is moving
	 */
	@Test 
	public void testSpeedIsPositiveWhileMoving() {
		
		simulator = factory.createPacaTracaMoveNormalOnly(SENSOR_ID);
		waitForAlpacaToMove(1);
		float speed = simulator.getSpeed();
		assertTrue("Speed is "+speed+"; should be positive", speed > 0.0);
	}
	
	/*
	 * Test the the altitude reading changes while the alpaca is grazing.
	 */
	@Test
	public void testAltitudeChangesWhileGrazing() {
		
		simulator = factory.createPacaTracaGrazeOnly(SENSOR_ID);
		float altitude1 = simulator.getAltitude();
		waitForAlpacaToMove(3);
		float altitude2 = simulator.getAltitude();
		
		// The alpaca's altitude should have changed
		assertTrue("Altitudes are "+altitude1+" and "+altitude2+
				"; they should be different", Math.abs(altitude1 - altitude2) > 0.00);
	}
	
	/*
	 * Test the the latitude and longitude stay the same while the alpaca is
	 * standing still
	 */
	@Test
	public void testLatLongSameWhileAlpacaStandingStill() {
		
		simulator = factory.createPacaTracaStandStillOnly(SENSOR_ID);
		float latitude1 = simulator.getLatitudeDecimalDegrees();
		float longitude1 = simulator.getLongitudeDecimalDegrees();
		waitForAlpacaToMove(1);
		float latitude2 = simulator.getLatitudeDecimalDegrees();
		float longitude2 = simulator.getLongitudeDecimalDegrees();
		assertTrue("Latitude and longitude should not have changed.", 
				(Math.abs(longitude1 - longitude2) <= 0.000000000) &&
				Math.abs(latitude1 - latitude2) <= 0.000000000);
	}
	
	/*
	 * Test that the speed reading is 0 when the alpaca is standing still.
	 */
	@Test
	public void testSpeedZeroWhileAlpacaStandlingStill() {
		
		simulator = factory.createPacaTracaStandStillOnly(SENSOR_ID);
		waitForAlpacaToMove(1);
		float speed = simulator.getSpeed();
		assertTrue("Speed is "+speed+"; should be zero", speed == 0);
		
	}
	
	/*
	 * Test that the temperature reading increases when the alpaca has a fever.
	 */
	@Test
	public void testTemperatureIncreasesWhileFever() {
		
		// Test the slow fever event
		simulator = factory.createPacaTracaSlowFeverOnly(SENSOR_ID);
		float temp1 = simulator.getTemperature();
		waitForAlpacaTemperatureChange(1);
		float temp2 = simulator.getTemperature();
		assertTrue("Temperatures are "+temp1+" and "+temp2+
				"; it should have increased", temp2 - temp1 > 0.00);
		
		// Test the sudden fever event
		simulator = factory.createPacaTracaSuddenFeverOnly(SENSOR_ID);
		temp1 = simulator.getTemperature();
		waitForAlpacaTemperatureChange(1);
		temp2 = simulator.getTemperature();
		assertTrue("Temperatures are "+temp1+" and "+temp2+
				"; it should have increased", temp2 - temp1 > 0.00);
	}
	
	/*
	 * Test that the temperature reading decreases when the alpaca has chills.
	 */
	@Test
	public void testTemperatureDecreasesWhileChills() {
		
		// Test the slow chills event
		simulator = factory.createPacaTracaSlowChillOnly(SENSOR_ID);
		float temp1 = simulator.getTemperature();
		waitForAlpacaTemperatureChange(1);
		float temp2 = simulator.getTemperature();
		assertTrue("Temperatures are "+temp1+" and "+temp2+
				"; it should have decreased", temp1 - temp2 > 0.00);
		
		// Test the sudden chills event
		simulator = factory.createPacaTracaSuddenChillOnly(SENSOR_ID);
		temp1 = simulator.getTemperature();
		waitForAlpacaTemperatureChange(1);
		temp2 = simulator.getTemperature();
		assertTrue("Temperatures are "+temp1+" and "+temp2+
				"; it should have decreased", temp1 - temp2 > 0.00);
	}
	
	/*
	 * Helper method for tests on alpaca movement
	 */
	private void waitForAlpacaToMove(int numMoves){
		try {
			Thread.sleep(MotionRunnable.THREAD_SLEEP_TIME_MILLIS * numMoves);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Helper method for tests on alpaca temperature changes
	 */
	private void waitForAlpacaTemperatureChange(int numChanges){
		try {
			Thread.sleep(TemperatureRunnable.THREAD_SLEEP_TIME_MILLIS * numChanges);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
