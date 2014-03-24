package general;

public class GPS {
	//Need to have this updating regularly as the alpacas move
	static float lat, lon;
	
	public static float getLat() {
		return lat;
	}

	public void setLat(float lat) {
		GPS.lat = lat;
	}

	public static float getLon() {
		return lon;
	}

	public void setLon(float lon) {
		GPS.lon = lon;
	}
	
}
