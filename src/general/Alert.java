package general;

public class Alert {

	// If current Latitude or Longitude is outside of the boundary, return True.
	// Need to update so that it displays which direction is out of bounds.
	public static Boolean outsideBoundary(float latCurrent, float lonCurrent) {
		if (latCurrent > Settings.getLatMax()
				|| latCurrent < Settings.getLatMin()
				|| lonCurrent > Settings.getLonMax()
				|| lonCurrent < Settings.getLonMin()) {
			return true;
		} else {
			return false;
		}
	}

	public static void boundaryAlert() {
		if (outsideBoundary(GPS.getLat(), GPS.getLon())) {
			System.out.println("Alpaca out of bounds!");
		}
	}

}
