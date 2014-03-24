package simulator;

public class AlpacaDataSimulator implements SensorData, GPSData, IMUData {
	
	private Integer sensorID;
	private MotionRunnable motionSimulator;
	
	public AlpacaDataSimulator(){
		this.motionSimulator = new MotionRunnable();
		new Thread(this.motionSimulator).start();
	}
	
	/**
	 * @return a new instance of AlpacaDataSimulator
	 */
	public static AlpacaDataSimulator createInstance(){
		return new AlpacaDataSimulator();
	}

	@Override
	public Float Temperature() {
		// TODO Auto-generated method stub
		return new Float(10.0);
	}

	@Override
	public Float Altitude() {
		// TODO Auto-generated method stub
		return new Float(10.0);
	}

	@Override
	public Float Roll() {
		// TODO Auto-generated method stub
		return new Float(10.0);
	}

	@Override
	public Float Pitch() {
		// TODO Auto-generated method stub
		return new Float(10.0);
	}

	@Override
	public Float CompassHeading() {
		// TODO Auto-generated method stub
		return new Float(10.0);
	}

	@Override
	public void setSeaLevelPressure(Float pressure) {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		return new Float(10.0);
	}

	@Override
	public Float getCourse() {
		// TODO Auto-generated method stub
		return new Float(10.0);
	}

	@Override
	public Float getAltitude() {
		// TODO Auto-generated method stub
		return new Float(10.0);
	}

	@Override
	public Integer getNumSatellites() {
		// TODO Auto-generated method stub
		return new Integer(1);
	}

	@Override
	public Boolean haveFix() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public Integer getSignalQuality() {
		// TODO Auto-generated method stub
		return 5;
	}

	@Override
	public Boolean setSensorID(Integer sensorID) {
		// TODO Auto-generated method stub
		this.sensorID = sensorID;
		return true;
	}

	@Override
	public Boolean isConnected() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public Boolean ConnectToSensor(Integer sensorID) {
		// TODO Auto-generated method stub
		return (sensorID == this.sensorID);
	}

}
