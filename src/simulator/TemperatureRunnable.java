package simulator;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 
 * @author Sonia Rode
 *
 * Simulates alpaca temperature changes.
 * 
 */
public class TemperatureRunnable implements Runnable {
	
	private static final int THREAD_SLEEP_TIME_MILLIS = 500;

	enum TemperatureEvent{
		SLOW_FEVER,
		SUDDEN_FEVER,
		SLOW_CHILL,
		SUDDEN_CHILL,
		STEADY_TEMP;	
	}
	
	private static Map<TemperatureEvent, Double> eventProbabilities;
	static {
        Map<TemperatureEvent, Double> map = new HashMap<TemperatureEvent, Double>();
        map.put(TemperatureEvent.SLOW_FEVER, 0.20);
        map.put(TemperatureEvent.SUDDEN_FEVER, 0.15);
        map.put(TemperatureEvent.SLOW_CHILL, 0.20);
        map.put(TemperatureEvent.SUDDEN_CHILL, 0.15);
        map.put(TemperatureEvent.STEADY_TEMP, 0.30);
        eventProbabilities = Collections.unmodifiableMap(map);
    }

	private TemperatureEvent currentEvent;
	private int countdownToNextEvent;
	private volatile float temperature;
	private volatile Random random;

	public TemperatureRunnable(Random random){
		this.random = random;
		temperature = 100.4f;
		countdownToNextEvent = 0;
	}
	
	/*
	 * Constructor for unit tests only
	 */
	public TemperatureRunnable(Random random, Map<TemperatureEvent, Double> probs){
		this(random);
		eventProbabilities = probs;	
	}

	@Override
	public void run() {
		while (true){
			if (countdownToNextEvent <= 0){
				currentEvent = chooseEvent();
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

	private TemperatureEvent chooseEvent(){
		// Use event probabilities to randomly choose an event
		double dice = random.nextDouble();
		
		if (dice < eventProbabilities.get(TemperatureEvent.SLOW_FEVER)){
			countdownToNextEvent = randomIntBetween(10, 500);
			return TemperatureEvent.SLOW_FEVER;
		} 
		else if (dice < (eventProbabilities.get(TemperatureEvent.SLOW_FEVER) +
						eventProbabilities.get(TemperatureEvent.SUDDEN_FEVER))){
			countdownToNextEvent = 1;
			return TemperatureEvent.SUDDEN_FEVER;
		}
		else if (dice < (eventProbabilities.get(TemperatureEvent.SLOW_FEVER) +
						eventProbabilities.get(TemperatureEvent.SUDDEN_FEVER) +
						eventProbabilities.get(TemperatureEvent.SLOW_CHILL))){
			countdownToNextEvent = randomIntBetween(10, 500);
			return TemperatureEvent.SLOW_CHILL;
		}
		else if (dice < (eventProbabilities.get(TemperatureEvent.SLOW_FEVER) +
				eventProbabilities.get(TemperatureEvent.SUDDEN_FEVER) +
				eventProbabilities.get(TemperatureEvent.SLOW_CHILL) +
				eventProbabilities.get(TemperatureEvent.SUDDEN_CHILL))){
			countdownToNextEvent = 1;
			return TemperatureEvent.SUDDEN_CHILL;
		}
		countdownToNextEvent = randomIntBetween(10, 500);
		return TemperatureEvent.STEADY_TEMP;
	}
	
	private int randomIntBetween(int startInclusive, int endExclusive){
		if (startInclusive >= endExclusive)
			throw new IllegalArgumentException("Start must be greater than end!");
		return startInclusive + random.nextInt(endExclusive - startInclusive);
	}

}
