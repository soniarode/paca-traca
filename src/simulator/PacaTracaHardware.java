package simulator;

/**
 * @author Bryan Wells
 *
 * implementation of the GPS Sensor Data class
 * Provides a concrete implementation for an GPS Sensor
 * to service requests for GPS Data
 */
public class PacaTracaHardware implements PacaTraca {
	
	private String m_sensorID;
	
	/*
	 * protected default constructor
	 */
	protected PacaTracaHardware( ) {
	}
	
	/*
	 * constructor taking an AlpacaID
	 */
	public PacaTracaHardware( String sensorID ) {
		this.setSensorID( sensorID );
		//System.out.println("PacaTracaHardware constructor was called");
	}

	/* (non-Javadoc)
	 * @see com.pacatraca.PacaTraca#toString()
	 */
	@Override
	public String toString( ) {
		return this.m_sensorID;
	}
	
	/* (non-Javadoc)
	 * @see com.pacatraca.PacaTraca#getLatitudeDecimalDegrees()
	 */
	@Override
	public Float getLatitudeDecimalDegrees() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.pacatraca.getSensorID()
	 */
	@Override
	public String getSensorID( ) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/* (non-Javadoc)
	 * @see com.pacatraca.getSensorID()
	 */
	
	@Override
	public void setSensorID( String id ) {
		// TODO Auto-generated method stub
		this.m_sensorID = id;
	}

	/* (non-Javadoc)
	 * @see com.pacatraca.PacaTraca#getLongitudeDecimalDegrees()
	 */
	@Override
	public Float getLongitudeDecimalDegrees() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.pacatraca.PacaTraca#getSpeed()
	 */
	@Override
	public Float getSpeed() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.pacatraca.PacaTraca#getCourse()
	 */
	@Override
	public Float getCourse() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.pacatraca.PacaTraca#getAltitude()
	 */
	@Override
	public Float getAltitude() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.pacatraca.PacaTraca#getNumSatellites()
	 */
	@Override
	public Integer getNumSatellites() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.pacatraca.PacaTraca#haveFix()
	 */
	@Override
	public Boolean haveFix() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.pacatraca.PacaTraca#getSignalQuality()
	 */
	@Override
	public Integer getSignalQuality() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Float getTemperature() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Float getRoll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Float getPitch() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Float getCompassHeading() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setSeaLevelPressure(Float pressure) {
		// TODO Auto-generated method stub
		
	}	
}
