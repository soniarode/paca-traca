package general;

public class Settings {

	// Boundaries in Latitude and longitude Maximum and Minimum
	static float latMax, latMin, lonMax, lonMin;

	public static float getLatMax() {
		return latMax;
	}

	public static void setLatMax(float latMax) {
		Settings.latMax = latMax;
	}

	public static float getLatMin() {
		return latMin;
	}

	public static void setLatMin(float latMin) {
		Settings.latMin = latMin;
	}

	public static float getLonMax() {
		return lonMax;
	}

	public static void setLonMax(float lonMax) {
		Settings.lonMax = lonMax;
	}

	public static float getLonMin() {
		return lonMin;
	}

	public static void setLonMin(float lonMin) {
		Settings.lonMin = lonMin;
	}
}
