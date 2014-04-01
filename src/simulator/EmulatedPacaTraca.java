package simulator;

import java.util.Random;

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
	private Random random; // used to randomize alpaca's behavior

	public EmulatedPacaTraca(String sensorID){
		this.sensorID = sensorID;
		this.random = new Random();
		this.motionSimulator = new MotionRunnable(random);
		new Thread(this.motionSimulator).start();
	}

	/*
	 * Constructor for unit tests only
	 */
	public EmulatedPacaTraca(String sensorID, double speedChangeProbability){
		this(sensorID);
		motionSimulator.setSpeedChangeProbability(speedChangeProbability);
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
		// TODO this method should return speed in feet per second,
		// but right now it's degrees per millisecond. Fix.
		float distanceGPS = Math.abs(motionSimulator.getCurrentLatitude() - 
				motionSimulator.getPreviousLatitude())
				+ Math.abs(motionSimulator.getCurrentLongitude() - 
						motionSimulator.getPreviousLongitude());
		return distanceGPS/motionSimulator.getMillisBetweenMeasurements();
	}

	@Override
	public Float getCourse() {
		float curLat = motionSimulator.getCurrentLatitude();
		float curLon = motionSimulator.getCurrentLongitude();
		float prevLat = motionSimulator.getPreviousLatitude();
		float prevLon = motionSimulator.getPreviousLongitude();

		float positiveAngleRadians = (float)Math.atan( Math.abs(curLat - prevLat) 
				/ Math.abs(curLon - prevLon));

		// Find radians to add to arctan result based on what quadrant the last
		// move was in to
		float radiansToAdd = 0; 
		if (curLat - prevLat > 0){ // Q1 or Q4
			if (curLon - prevLon > 0) // Q1
				radiansToAdd = 0; 
			else // Q4
				radiansToAdd = 3.0f/2.0f * (float)Math.PI;
		} else { // Q2 or Q3
			if (curLon - prevLon > 0) // Q2
				radiansToAdd = (float)Math.PI/2.0f;
			else // Q3
				radiansToAdd = (float)Math.PI;
		}
		return positiveAngleRadians + radiansToAdd;
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
