package simulator;

/**
 * 
 * @author Sonia Rode
 *
 * Concrete implementation of PacaTraca that provides
 * simulated sensor data.
 * 
 */
public class EmulatedPacaTraca implements PacaTraca {
	
	private String sensorID;
	private MotionRunnable motionSimulator;
	
	public EmulatedPacaTraca(String sensorID){
		this.sensorID = sensorID;
		this.motionSimulator = new MotionRunnable();
		new Thread(this.motionSimulator).start();
	}

	@Override
	public Float getLatitudeDecimalDegrees() {
		return motionSimulator.getCurrentLatitude();
	}

	@Override
	public Float getLongitudeDecimalDegrees() {
		return motionSimulator.getCurrentLongitude();
	}

	@Override
	public Float getSpeed() {
		// TODO real implementation
		return new Float(10.0);
	}

	@Override
	public Float getCourse() {
		// TODO real implementation
		return new Float(10.0);
	}

	@Override
	public Float getAltitude() {
		// TODO real implementation
				return new Float(10.0);
	}

	@Override
	public Integer getNumSatellites() {
		// TODO real implementation
				return new Integer(1);
	}

	@Override
	public Boolean haveFix() {
		// TODO real implementation
				return new Boolean(true);
	}

	@Override
	public Integer getSignalQuality() {
		// TODO real implementation
		return new Integer(1);
	}

	@Override
	public Float getTemperature() {
		// TODO real implementation
		return new Float(10.0);
	}

	@Override
	public Float getRoll() {
		// TODO real implementation
		return new Float(10.0);
	}

	@Override
	public Float getPitch() {
		// TODO real implementation
		return new Float(10.0);
	}

	@Override
	public Float getCompassHeading() {
		// TODO real implementation
		return new Float(10.0);
	}

	@Override
	public void setSeaLevelPressure(Float pressure) {
		// TODO real implementation
	}

	@Override
	public String getSensorID() {
		return this.sensorID;
	}

	@Override
	public void setSensorID(String ID) {
		this.sensorID = ID;
	}

}
