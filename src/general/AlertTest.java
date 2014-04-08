package general;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import simulator.PacaTraca;

/**
 * @author Zachery Schiller Unit tests for EmulatedPacaTraca
 */

public class AlertTest {

	Alert alertTest;
	PacaTraca alpaca;
	Settings settings;

	@Before
	public void setUp() throws Exception {
		alertTest = new Alert(alpaca, settings);
	}

	@Test
	public void testOutsideBoundary() {
		float latitude_Values[] = settings.getLatArray();
		float longitude_Values[] = settings.getLonArray();
		int num_Corners = settings.getNumCorners();

		/*
		 * Test that there are more than two boundary points
		 */
		assertTrue("Should be more than 2 boundary points", num_Corners > 2);

		float testLatIn = latitude_Values[0];
		float testLonIn = longitude_Values[0];

		/*
		 * Test that each of the boundary values are within lat and lon
		 */
		for (int i = 1; i < num_Corners; i++) {
			assertTrue("Latitude and longitude should be within -90 and 90",
					((latitude_Values[i]) > -90.000000000)
							&& (latitude_Values[i]) < 90.000000000
							&& (longitude_Values[i]) > -90.000000000
							&& (longitude_Values[i]) < 90.000000000);
			testLatIn = testLatIn + latitude_Values[i];
			testLonIn = testLonIn + longitude_Values[i];
		}

		testLatIn = testLatIn / num_Corners;
		testLonIn = testLonIn / num_Corners;

		/*
		 * Test that a point found by averaging boundaries is within boundaries
		 */
		assertTrue("Average of boundaries should be in bounds.",
				(alertTest.OutsideBoundary(testLatIn, testLonIn)) == true);
	}
}
