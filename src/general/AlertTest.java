package general;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import simulator.PacaTraca;

/**
 * @author Zachery Schiller Unit tests for Alert Class
 */

public class AlertTest {

	Alert alertTest;
	PacaTraca alpaca;
	Settings settings;

	@Before
	public void setUp() throws Exception {
		// Need to create an alpaca to change settings on for test
		settings = new SettingsImpl();
		float[] latitudes = { 44.9f, 44.925f, 44.9f, 44.925f };
		settings.setLatArray(latitudes);
		float[] longitudes = { -68.66f, -68.66f, -68.68f, -68.68f };
		settings.setLonArray(longitudes);
		alertTest = new Alert(alpaca, settings, new ProfileManager().getDefaultProfile());
	}

	@Test
	public void testInsideBoundary() {
		/*
		 * Test that a point within specified boundaries returns in bounds.
		 */
		assertTrue("Should be in bounds",
				alertTest.OutsideBoundary(44.9125f, -68.67f));
	}

	@Test
	public void testOutsideBoundary() {
		/*
		 * Test that a point within specified boundaries returns in bounds.
		 */
		assertFalse("Should be out of bounds",
				alertTest.OutsideBoundary(48.1f, -70.2f));
	}

	@Test
	public void testHighTemperature() {
		/*
		 * Test that a high temperature returns high from test
		 */
		// Need to set temperature above 102.5f
		assertTrue("Should return high temperature",
				alertTest.temperatureAlert() == "high");
	}

	@Test
	public void testLowTemperature() {
		/*
		 * Test that a low temperature returns low from test
		 */
		// Need to set temperature below 100.5f
		assertTrue("Should return low temperature",
				alertTest.temperatureAlert() == "low");
	}

	@Test
	public void testNotDown() {
		/*
		 * Test that a normal altitude returns normal from test
		 */
		// Need to set current and previous altitude to the same
		assertTrue("Should return normal",
				alertTest.downAlert() == "Alpaca is fine!");
	}

	@Test
	public void testDown() {
		/*
		 * Test that a abrupt change in altitude returns alert from test
		 */
		// Need to set current and previous altitude to differ > 0.001
		assertTrue("Should return with a problem",
				alertTest.downAlert() == "Alpaca moved too suddenly!");
	}

}
