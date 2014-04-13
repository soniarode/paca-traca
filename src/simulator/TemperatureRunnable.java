package simulator;

import java.util.LinkedHashMap;
import java.util.Random;


/**
 * 
 * @author Sonia Rode
 *
 * Simulates alpaca temperature changes.
 * 
 */
public class TemperatureRunnable implements Runnable {
	
	public static final int THREAD_SLEEP_TIME_MILLIS = 500;

	enum TemperatureEvent{
		SLOW_FEVER,
		SUDDEN_FEVER,
		SLOW_CHILL,
		SUDDEN_CHILL,
		STEADY_TEMP;	
	}
	
	private static LinkedHashMap<TemperatureEvent, Double> eventProbabilities;
	static {
		eventProbabilities = new LinkedHashMap<TemperatureEvent, Double>();
		eventProbabilities.put(TemperatureEvent.SLOW_FEVER, 0.20);
		eventProbabilities.put(TemperatureEvent.SUDDEN_FEVER, 0.15);
		eventProbabilities.put(TemperatureEvent.SLOW_CHILL, 0.20);
		eventProbabilities.put(TemperatureEvent.SUDDEN_CHILL, 0.15);
		eventProbabilities.put(TemperatureEvent.STEADY_TEMP, 0.30);
    }

	private TemperatureEvent currentEvent;
	private int countdownToNextEvent;
	private volatile float temperature;
	private volatile Random random;

	/**
	 * Creates a new TemperatureRunnable
	 * @param random used to randomize the alpaca's temperature behavior.
	 */
	public TemperatureRunnable(Random random){
		this.random = random;
		temperature = 100.4f;
		countdownToNextEvent = 0;
	}
	
	/**
	 * Creates a new TemperatureRunnable with the given event probabilities.
	 * @param random used to randomize the alpaca's temperature behavior
	 * @param probs the probabilities to use for each TemperatureEvent
	 */
	public TemperatureRunnable(Random random, LinkedHashMap<TemperatureEvent, Double> probs){
		this(random);
		if (probs != null)
			eventProbabilities = probs;
	}

	@Override
	public void run() {
		while (true){
			if (countdownToNextEvent <= 0){
				currentEvent = chooseEventAndSetup();
			}
			countdownToNextEvent--;
			switch(currentEvent)
			{
			case SLOW_CHILL:
				temperature -= .01;
				break;
			case SLOW_FEVER:
				temperature += .01;
				break;
			case STEADY_TEMP:
				break;
			case SUDDEN_CHILL:
				temperature -= 2;
				break;
			case SUDDEN_FEVER:
				temperature += 2;
				break;
			default:
				break;
			}
			try {
				Thread.sleep(THREAD_SLEEP_TIME_MILLIS);
			} catch (InterruptedException e) {
				break;
			}
		}
	}
	
	public float getTemperature(){
		return temperature;
	}

	/*
	 *  Use event probabilities to randomly choose an event, and set variables
	 *  for the event
	 */
	private TemperatureEvent chooseEventAndSetup(){

		TemperatureEvent chosenEvent = 
				MathUtils.chooseEventForProbability(random.nextDouble(), 
						eventProbabilities);
		switch(chosenEvent){
		
		case SLOW_FEVER:
			countdownToNextEvent = MathUtils.randomIntBetween(10, 50, random);
			break;
		case SUDDEN_FEVER:
			countdownToNextEvent = 1;
			break;
		case SLOW_CHILL:
			countdownToNextEvent = MathUtils.randomIntBetween(10, 50, random);
			break;
		case SUDDEN_CHILL:
			countdownToNextEvent = 1;
			break;
		case STEADY_TEMP:
			countdownToNextEvent = MathUtils.randomIntBetween(10, 50, random);
			break;
		}
		return chosenEvent;
	}

}
