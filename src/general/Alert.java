package general;

public class Alert {

	// If current Latitude or Longitude is outside of the boundary, print alert!
	public static String outsideBoundary(float latCurrent, float lonCurrent) {
		Settings.getBoundaries();
		if (latCurrent > Settings.getLatMax()) {
			return "Too far North!";
		} else if (latCurrent < Settings.getLatMin()) {
			return "Too far South!";
		} else if (lonCurrent > Settings.getLonMax()) {
			return "Too far East!";
		} else if (lonCurrent < Settings.getLonMin()) {
			return "Too far West!";
		} else {
			return "";
		}
	}

	// Currently getting lat and lon from GPS class, need to get that from
	// interface
	public static void boundaryAlert() {
		String response = outsideBoundary(GPS.getLat(), GPS.getLon());
		if (!response.equals("")) {
			System.out.println("Alpaca out of bounds! " + response);
		}
	}
}
