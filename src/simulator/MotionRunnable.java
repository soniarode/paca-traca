package simulator;

import java.util.Random;

/**
 * 
 * @author Sonia Rode
 *
 * Generates latitude and longitude locations for an alpaca
 * using simulated movement.
 * 
 */
public class MotionRunnable implements Runnable {
	
	public static final int MAX_MILLIS_BETWEEN_LOCATION_CHANGE = 3100;
	public static final float ALPACA_STEP_UNIT_GPS_DEGREES = 0.0001f;
	
	private volatile float currentLatitude;
	private volatile float currentLongitude;
	private volatile float previousLatitude;
	private volatile float previousLongitude;
	private Random random;
	private int millisBetweenLocationChange;
	private double speedChangeProbability;
	
	/**
	 * 
	 * @param startLatitude the starting latitude of the alpaca
	 * @param startLongitude the starting longitude of the alpaca
	 * @param random the Random object used to randomize the alpaca's movement
	 */
	public MotionRunnable(float startLatitude, float startLongitude,
			Random random){
		currentLatitude = startLatitude;
		currentLongitude = startLongitude;
		this.random = random;
		millisBetweenLocationChange = 500; // initially the alpaca changes
		                                   // its location every .5 seconds
		speedChangeProbability = 0.5; // the alpaca changes it's speed half the
									  // time it moves
	}
	
	public MotionRunnable(Random random){
		// Set hard-coded initial position of alpaca
		this(45.000000000f, 68.000000000f, random);
	}
	
	public MotionRunnable(){
		this(new Random());
	}

	@Override
	public void run() {
		while (true) {
			// Randomly change the alpaca's direction by dividing the step unit
			// size between the x and y directions.
			double angleToMove = Math.PI*2 * random.nextDouble();
			double deltaX = Math.cos(angleToMove)*ALPACA_STEP_UNIT_GPS_DEGREES;
			double deltaY = Math.sin(angleToMove)*ALPACA_STEP_UNIT_GPS_DEGREES;
			
			// Update the alpaca's location
			previousLatitude = currentLatitude;
			previousLongitude = currentLongitude;
			currentLatitude += deltaX;
			currentLongitude += deltaY;
			//currentLatitude += 0.0001;
			//currentLongitude += 0.0001;
			
			// Randomly change the alpaca's speed
			changeSpeedWithProbability(speedChangeProbability);
			
			// Sleep because the alpaca doesn't move as fast as the processor
			try {
				Thread.sleep(millisBetweenLocationChange);
			} catch (InterruptedException e) {
				break;
			}
		}
	}

	public float getCurrentLatitude() {
		return currentLatitude;
	}

	public float getCurrentLongitude() {
		return currentLongitude;
	}

	public float getPreviousLatitude() {
		return previousLatitude;
	}

	public float getPreviousLongitude() {
		return previousLongitude;
	}
	
	public int getMillisBetweenMeasurements(){
		return millisBetweenLocationChange;
	}
	
	/*
	 * Sets the probability that the alpaca's speed will change at a
	 * time step.
	 */
	public void setSpeedChangeProbability(double probability){
		speedChangeProbability = probability;
	}
	
	private void changeSpeedWithProbability(double probability){
		if (random.nextDouble() < probability){
			// Change speed; pick some value in [100, 3100] millis between
			// location change
			millisBetweenLocationChange = 100 + random.nextInt(3000);
		}
	}

}
