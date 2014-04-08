package simulator;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
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

	private static Map<MotionEvent, Double> eventProbabilities;
	static {
		Map<MotionEvent, Double> map = new HashMap<MotionEvent, Double>();
		map.put(MotionEvent.MOVE_NORMAL, 0.40);
		map.put(MotionEvent.GRAZE, 0.40);
		map.put(MotionEvent.MOVE_FAR, 0.05);
		map.put(MotionEvent.STAND_STILL, 0.10);
		map.put(MotionEvent.LIE_STILL, 0.05);
		eventProbabilities = Collections.unmodifiableMap(map);
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
	 * 
	 * @param startLatitude the starting latitude of the alpaca
	 * @param startLongitude the starting longitude of the alpaca
	 * @param random the Random object used to randomize the alpaca's movement
	 */
	public MotionRunnable(float startLatitude, float startLongitude,
			Random random){
		currentLatitude = startLatitude;
		currentLongitude = startLongitude;
		altitude = STANDARD_ALTITUDE;
		this.random = random;
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
			if (countdownToNextEvent <= 0){
				currentEvent = chooseEvent();
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
	 *  Use event probabilities to randomly choose an event
	 */
	private MotionEvent chooseEvent(){
		
		double dice = random.nextDouble();

		if (dice < eventProbabilities.get(MotionEvent.MOVE_NORMAL)){
			countdownToNextEvent = MathUtils.randomIntBetween(1, 20, random);
			heading = random.nextDouble() * Math.PI*2;
			altitude = STANDARD_ALTITUDE;
			stepUnitMultiplier = MathUtils.randomIntBetween(1, 10, random);
			return MotionEvent.MOVE_NORMAL;
		} 
		else if (dice < (eventProbabilities.get(MotionEvent.MOVE_NORMAL) +
				eventProbabilities.get(MotionEvent.GRAZE))){
			countdownToNextEvent = MathUtils.randomIntBetween(1, 20, random);
			altitude = STANDARD_ALTITUDE;
			grazeStatus = GrazeStatus.LOWERING_HEAD;
			return MotionEvent.GRAZE;
		}
		else if (dice < (eventProbabilities.get(MotionEvent.MOVE_NORMAL) +
				eventProbabilities.get(MotionEvent.GRAZE) +
				eventProbabilities.get(MotionEvent.MOVE_FAR))){
			countdownToNextEvent = MathUtils.randomIntBetween(20, 100, random);
			heading = random.nextDouble() * Math.PI*2;
			altitude = STANDARD_ALTITUDE;
			stepUnitMultiplier = MathUtils.randomIntBetween(1, 10, random);
			return MotionEvent.MOVE_FAR;
		}
		else if (dice < (eventProbabilities.get(MotionEvent.MOVE_NORMAL) +
				eventProbabilities.get(MotionEvent.GRAZE) +
				eventProbabilities.get(MotionEvent.MOVE_FAR) +
				eventProbabilities.get(MotionEvent.STAND_STILL))){
			countdownToNextEvent = MathUtils.randomIntBetween(1, 20, random);
			altitude = STANDARD_ALTITUDE;
			return MotionEvent.STAND_STILL;
		}
		else if (dice < (eventProbabilities.get(MotionEvent.MOVE_NORMAL) +
				eventProbabilities.get(MotionEvent.GRAZE) +
				eventProbabilities.get(MotionEvent.MOVE_FAR) +
				eventProbabilities.get(MotionEvent.STAND_STILL) +
				eventProbabilities.get(MotionEvent.LIE_STILL))){
			countdownToNextEvent = MathUtils.randomIntBetween(1, 20, random);
			altitude = STANDARD_ALTITUDE - 3;
			return MotionEvent.LIE_STILL;
		}
		countdownToNextEvent = MathUtils.randomIntBetween(1, 20, random);
		altitude = STANDARD_ALTITUDE;
		return MotionEvent.STAND_STILL;
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
