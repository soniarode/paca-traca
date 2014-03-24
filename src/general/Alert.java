package general;

public class Alert {

	//If current Latitude or Longitude is outside of the boundary, return True.
	public static Boolean boundaryAlert(double latCurrent, double longCurrent) {
		if (latCurrent > Settings.getLatMax() || 
				latCurrent < Settings.getLatMin() || 
				longCurrent > Settings.getLongMax()
				|| longCurrent < Settings.getLongMin()) {
			return true;
		} else {
			return false;
		}
	}
}
