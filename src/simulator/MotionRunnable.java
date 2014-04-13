package simulator;

import java.util.LinkedHashMap;
import java.util.Random;

/**
 * 
 * @author Sonia Rode
 *
 * Manipulates latitude, longitude, and altitude for an alpaca
 * using simulated movement.
 * 
 */
public class MotionRunnable implements Runnable {

	public static final int THREAD_SLEEP_TIME_MILLIS = 500;
	public static final double ALPACA_STEP_UNIT_GPS_DEGREES = 0.000001;

	private static final double MIN_ALTITUDE_WHILE_GRAZING = 49.5;
	private static final double MAX_ALTITUDE_WHILE_GRAZING = 50;
	private static final double STANDARD_ALTITUDE = 50;

	enum MotionEvent{
		MOVE_NORMAL,
		GRAZE,
		MOVE_FAR,
		STAND_STILL,
		LIE_STILL;	
	}

	enum GrazeStatus{
		LOWERING_HEAD, 
		LIFTING_HEAD;
	}

	private static LinkedHashMap<MotionEvent, Double> eventProbabilities;
	static {
		eventProbabilities = new LinkedHashMap<MotionEvent, Double>();
		eventProbabilities.put(MotionEvent.MOVE_NORMAL, 0.40);
		eventProbabilities.put(MotionEvent.GRAZE, 0.40);
		eventProbabilities.put(MotionEvent.MOVE_FAR, 0.05);
		eventProbabilities.put(MotionEvent.STAND_STILL, 0.10);
		eventProbabilities.put(MotionEvent.LIE_STILL, 0.05);
	}

	private MotionEvent currentEvent;
	private int countdownToNextEvent;
	private double heading;
	private double altitude;
	private GrazeStatus grazeStatus;
	private int stepUnitMultiplier;
	private volatile double currentLatitude;
	private volatile double currentLongitude;
	private volatile double previousLatitude;
	private volatile double previousLongitude;
	private volatile Random random;

	/**
	 * Creates a new MotionRunnable.
	 * @param startLatitude the starting latitude of the alpaca
	 * @param startLongitude the starting longitude of the alpaca
	 * @param random the Random object used to randomize the alpaca's movement
	 */
	public MotionRunnable(float startLatitude, float startLongitude,
			Random random){
		previousLatitude = startLatitude;
		previousLongitude = startLongitude;
		currentLatitude = startLatitude;
		currentLongitude = startLongitude;
		altitude = STANDARD_ALTITUDE;
		this.random = random;
	}

	/**
	 * Creates a new MotionRunnable with an initial latitude-longitude
	 * of 45-68.
	 * @param random the Random object used to randomize the alpaca's movement
	 */
	public MotionRunnable(Random random){
		// Set hard-coded initial position of alpaca
		this(45.000000000f, 68.000000000f, random);
	}

	/**
	 * Creates a new MotionRunnable with the given event probabilities.
	 * @param random the Random object used to randomize the alpaca's movement
	 * @param probs the probabilities to use for each MotionEvent
	 */
	public MotionRunnable(Random random, LinkedHashMap<MotionEvent, Double> probs){
		this(random);
		if (probs != null)
			eventProbabilities = probs;
	}

	/**
	 * Creates a new MotionRunnable with an initial latitude-longitude
	 * of 45-68.
	 */
	public MotionRunnable(){
		this(new Random());
	}

	@Override
	public void run() {
		while (true) {
			if (countdownToNextEvent <= 0){
				currentEvent = chooseEventAndSetup();
			}
			countdownToNextEvent--;
			switch(currentEvent)
			{
			case MOVE_NORMAL:
				move();
				break;
			case GRAZE:
				graze();
				break;
			case MOVE_FAR:
				move();
				break;
			case STAND_STILL: // do nothing
				break;
			case LIE_STILL: // do nothing
				break;
			default:
				break;
			}
			try {
				Thread.sleep(THREAD_SLEEP_TIME_MILLIS);
			} catch (InterruptedException e) {
				break;
			}

			// Sleep because the alpaca doesn't move as fast as the processor
			try {
				Thread.sleep(THREAD_SLEEP_TIME_MILLIS);
			} catch (InterruptedException e) {
				break;
			}
		}
	}

	public double getCurrentLatitude() {
		return currentLatitude;
	}

	public double getCurrentLongitude() {
		return currentLongitude;
	}

	public double getPreviousLatitude() {
		return previousLatitude;
	}

	public double getPreviousLongitude() {
		return previousLongitude;
	}

	public int getMillisBetweenMeasurements(){
		return THREAD_SLEEP_TIME_MILLIS;
	}

	public double getAltitude(){
		return altitude;
	}

	/*
	 *  Use event probabilities to randomly choose an event, and set variables
	 *  for the event
	 */
	private MotionEvent chooseEventAndSetup(){

		MotionEvent chosenEvent = 
				MathUtils.chooseEventForProbability(random.nextDouble(), 
						eventProbabilities);

		switch(chosenEvent){
		case MOVE_NORMAL:
			countdownToNextEvent = MathUtils.randomIntBetween(1, 20, random);
			heading = random.nextDouble() * Math.PI*2;
			altitude = STANDARD_ALTITUDE;
			stepUnitMultiplier = MathUtils.randomIntBetween(1, 10, random);
			break;
		case GRAZE:
			countdownToNextEvent = MathUtils.randomIntBetween(1, 20, random);
			altitude = STANDARD_ALTITUDE;
			grazeStatus = GrazeStatus.LOWERING_HEAD;
			break;
		case MOVE_FAR:
			countdownToNextEvent = MathUtils.randomIntBetween(20, 100, random);
			heading = random.nextDouble() * Math.PI*2;
			altitude = STANDARD_ALTITUDE;
			stepUnitMultiplier = MathUtils.randomIntBetween(1, 10, random);
			break;
		case STAND_STILL:
			countdownToNextEvent = MathUtils.randomIntBetween(1, 20, random);
			altitude = STANDARD_ALTITUDE;
			break;
		case LIE_STILL:
			countdownToNextEvent = MathUtils.randomIntBetween(1, 20, random);
			altitude = STANDARD_ALTITUDE - 3;
			break;
		}
		return chosenEvent;
	}

	/*
	 * Updates the latitude and longitude of the alpaca using the current
	 * heading and the stepUnitMultiplier (affects speed)
	 */
	private void move(){
		previousLatitude = currentLatitude;
		previousLongitude = currentLongitude;
		currentLatitude += Math.sin(heading)*
				ALPACA_STEP_UNIT_GPS_DEGREES*stepUnitMultiplier;
		currentLongitude += Math.cos(heading)*
				ALPACA_STEP_UNIT_GPS_DEGREES*stepUnitMultiplier;
	}

	/*
	 * Changes the alpaca's altitude reading in an up and down motion
	 * meant to mimic grazing
	 */
	private void graze(){
		// Update whether the alpaca's collar is lifting or lowering
		if (altitude <= MIN_ALTITUDE_WHILE_GRAZING){
			grazeStatus = GrazeStatus.LIFTING_HEAD;
		}
		else if (altitude >= MAX_ALTITUDE_WHILE_GRAZING){
			grazeStatus = GrazeStatus.LOWERING_HEAD;
		}

		// Change the altitude reading accordingly
		if (grazeStatus == GrazeStatus.LOWERING_HEAD){
			altitude -= 0.1;
		}
		else {
			altitude += 0.1;
		}
	}

}
