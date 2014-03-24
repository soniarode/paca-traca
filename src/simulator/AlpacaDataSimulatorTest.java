package simulator;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class AlpacaDataSimulatorTest {
	
	AlpacaDataSimulator simulator;

	@Before
	public void setUp() throws Exception {
		simulator = AlpacaDataSimulator.createInstance();
	}

	/*
	 * Test that the latitude reading changed when the alpaca is moving
	 */
	@Test
	public void getLatitudeAlpacaMovingTest() {
		float latitude1 = simulator.getLatitudeDecimalDegrees();
		try {
			Thread.sleep(200);
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
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		float longitude2 = simulator.getLatitudeDecimalDegrees();
		
		// The alpaca should have moved, so the longitude should have changed
		assertTrue("Longitudes should be different", longitude1 != longitude2);
	}
}
