package simulator;

// interface to GPS data for a given Alpaca
public interface GPSData {
	public Float getLatitudeDecimalDegrees( ); // negative == South
	public Float getLongitudeDecimalDegrees( ); // negative == West	
	public Float getSpeed( ); // feet per second
	public Float getCourse( ); // decimal degrees
	public Float getAltitude( ); // feet
	public Integer getNumSatellites( ); // number of satellites GPS found
	public Boolean haveFix( ); // true if GPS has a fix on at least one satellite
	public Integer getSignalQuality( ); // 1-5; 5 is best quality
}

