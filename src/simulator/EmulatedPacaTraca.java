package simulator;

import java.util.LinkedHashMap;
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
	
	private static final int FEET_PER_LATITUDE_DEGREE = 364320;
	private static final int RADIUS_OF_EARTH_FEET = 20925525;

	private String sensorID;
	private MotionRunnable motionSimulator;
	private TemperatureRunnable temperatureSimulator;
	private volatile Random random; // used to randomize alpaca's behavior

	/*
	 * Standard constructor
	 */
	public EmulatedPacaTraca(String sensorID){
		this.sensorID = sensorID;
		this.random = new Random();
		this.motionSimulator = new MotionRunnable(random);
		this.temperatureSimulator = new TemperatureRunnable(random);
		new Thread(this.motionSimulator).start();
		new Thread(this.temperatureSimulator).start();
	}

	/*
	 * Constructor where event probabilities are specified
	 */
	public EmulatedPacaTraca(String sensorID, 
			LinkedHashMap<TemperatureRunnable.TemperatureEvent, Double> tempProbabilities,
			LinkedHashMap<MotionRunnable.MotionEvent, Double> motionProbabilities){
		this.sensorID = sensorID;
		this.random = new Random();
		this.motionSimulator = new MotionRunnable(random, motionProbabilities);
		this.temperatureSimulator = new TemperatureRunnable(random, tempProbabilities);
		new Thread(this.motionSimulator).start();
		new Thread(this.temperatureSimulator).start();
	}


	@Override
	public Float getLatitudeDecimalDegrees() {
		return (float)motionSimulator.getCurrentLatitude();
	}

	@Override
	public Float getLongitudeDecimalDegrees() {
		return (float)motionSimulator.getCurrentLongitude();
	}

	@Override
	public Float getSpeed() {
		// Convert latitude delta to feet
		double curLat = motionSimulator.getCurrentLatitude();
		double deltaLatDegrees = Math.abs(curLat - 
				motionSimulator.getPreviousLatitude());
		double deltaLatFeet = deltaLatDegrees*FEET_PER_LATITUDE_DEGREE;
		
		// Convert longitude delta to feet
		double deltaLonDegrees = Math.abs(motionSimulator.getCurrentLongitude() - 
				motionSimulator.getPreviousLongitude());
		double feetPerLonDegree = Math.PI/180 * RADIUS_OF_EARTH_FEET * Math.cos(curLat);
		double deltaLonFeet = deltaLonDegrees * feetPerLonDegree;
		
		// Find the hypotenuse == distance between previous and current location
		float distance = (float)Math.sqrt(Math.pow(deltaLatFeet, 2) + 
				Math.pow(deltaLonFeet, 2));
		
		// Divide by the number of seconds between measurements
		float speed = distance/(motionSimulator.getMillisBetweenMeasurements()/1000.0f);

		return speed;
		
	}

	@Override
	public Float getCourse() {
		double curLat = motionSimulator.getCurrentLatitude();
		double curLon = motionSimulator.getCurrentLongitude();
		double prevLat = motionSimulator.getPreviousLatitude();
		double prevLon = motionSimulator.getPreviousLongitude();
		
		// If the longitude delta is 0 we'll get a divide-by-zero; this means
		// a heading straight north or south (90 or 270 degrees)
		if (Math.abs(curLon - prevLon) < 0.000000000000000){
			if (curLat > prevLat)
				return 90.0f;
			else
				return 270.0f;
		}

		float positiveAngleDegrees = (float)((180/Math.PI) * 
				Math.atan(Math.abs(curLat - prevLat) / Math.abs(curLon - prevLon)));

		// Find degrees to add to arctan result based on what quadrant the last
		// move was in to
		float degreesToAdd = 0; 
		if (curLat - prevLat > 0){ // Q1 or Q4
			if (curLon - prevLon > 0) // Q1
				degreesToAdd = 0; 
			else // Q4
				degreesToAdd = 270; 
		} else { // Q2 or Q3
			if (curLon - prevLon > 0) // Q2
				degreesToAdd = 90;
			else // Q3
				degreesToAdd = 180;
		}
		if (!Float.isNaN(positiveAngleDegrees))
			return positiveAngleDegrees + degreesToAdd;
		return 0f;
	}

	@Override
	public Float getAltitude() {
		return (float)motionSimulator.getAltitude();
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
		return temperatureSimulator.getTemperature();
	}

	@Override
	public Float getRoll() {
		return (float)motionSimulator.getRoll();
	}

	@Override
	public Float getPitch() {
		return (float)motionSimulator.getPitch();
	}

	@Override
	public Float getCompassHeading() {
		return getCourse();
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
