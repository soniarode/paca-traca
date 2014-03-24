package general;

public class Settings {
	//Boundaries in Latitude and Longitude Maximum and Minimum
	static double latMax, latMin, longMax, longMin;

	public static double getLatMax() {
		return latMax;
	}

	public static void setLatMax(double latMax) {
		Settings.latMax = latMax;
	}

	public static double getLatMin() {
		return latMin;
	}

	public static void setLatMin(double latMin) {
		Settings.latMin = latMin;
	}

	public static double getLongMax() {
		return longMax;
	}

	public static void setLongMax(double longMax) {
		Settings.longMax = longMax;
	}

	public static double getLongMin() {
		return longMin;
	}

	public static void setLongMin(double longMin) {
		Settings.longMin = longMin;
	}
}
