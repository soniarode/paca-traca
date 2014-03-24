package com.pacatraca;

public interface IMUData {
	public Float Temperature( ); //fahrenheit
	public Float Altitude( ); // feet
	public Float Roll( ); // rotate about y (-90 to 90 degrees)
	public Float Pitch( ); // rotate about x degrees (0-180 degrees)
	public Float CompassHeading( ); // heading 0-360 degrees
	public void setSeaLevelPressure( Float pressure ); // sensor pressure at sea level in hPa
}
