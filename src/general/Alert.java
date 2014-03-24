package general;

public class Alert {
	// Need to define variables for boundaries including max/min Latitude and
	// max/min Longitude. Should probably be in Settings class and pulled here.
	static double latMax, latMin, longMax, longMin;

	//If current Latitude or Longitude is outside of the boundary, return True.
	public static Boolean boundaryAlert(double latCurrent, double longCurrent) {
		if (latCurrent > latMax || latCurrent < latMin || longCurrent > longMax
				|| longCurrent < longMin) {
			return true;
		} else {
			return false;
		}
	}
}
