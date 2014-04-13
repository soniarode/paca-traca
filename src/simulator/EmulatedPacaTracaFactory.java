package simulator;

import java.util.LinkedHashMap;

import simulator.TemperatureRunnable.TemperatureEvent;
import simulator.MotionRunnable.MotionEvent;

/**
 * 
 * @author Sonia Rode
 * 
 * Factory for the EmulatedPacaTraca.
 *
 */
public class EmulatedPacaTracaFactory implements PacaTracaFactory {

	@Override
	public PacaTraca createPacaTraca(String sensorID) {
		return new EmulatedPacaTraca(sensorID);
	}
	
	/*
	 * 
	 * The remaining constructors are meant for unit testing only.
	 * 
	 */
	
	/**
	 * Creates a PacaTraca that always has a slow fever temperature event.
	 * Uses standard movement event probabilities.
	 */
	public PacaTraca createPacaTracaSlowFeverOnly(String sensorID) {
		return new EmulatedPacaTraca(sensorID, 
				probabilitiesOneEventOnly(TemperatureEvent.SLOW_FEVER, 
				TemperatureEvent.values()), null);
	}
	
	/**
	 * Creates a PacaTraca that always has a slow chill temperature event.
	 * Uses standard movement event probabilities.
	 */
	public PacaTraca createPacaTracaSlowChillOnly(String sensorID) {
		return new EmulatedPacaTraca(sensorID, 
				probabilitiesOneEventOnly(TemperatureEvent.SLOW_CHILL, 
				TemperatureEvent.values()), null);
	}
	
	/**
	 * Creates a PacaTraca that always has a sudden fever temperature event.
	 * Uses standard movement event probabilities.
	 */
	public PacaTraca createPacaTracaSuddenFeverOnly(String sensorID) {
		return new EmulatedPacaTraca(sensorID, 
				probabilitiesOneEventOnly(TemperatureEvent.SUDDEN_FEVER, 
				TemperatureEvent.values()), null);
	}
	
	/**
	 * Creates a PacaTraca that always has a sudden chill temperature event.
	 * Uses standard movement event probabilities.
	 */
	public PacaTraca createPacaTracaSuddenChillOnly(String sensorID) {
		return new EmulatedPacaTraca(sensorID, 
				probabilitiesOneEventOnly(TemperatureEvent.SUDDEN_CHILL, 
				TemperatureEvent.values()), null);
	}
	
	/**
	 * Creates a PacaTraca that always has a steady temperature.
	 * Uses standard movement event probabilities.
	 */
	public PacaTraca createPacaTracaSteadyTemperatureOnly(String sensorID) {
		return new EmulatedPacaTraca(sensorID, 
				probabilitiesOneEventOnly(TemperatureEvent.STEADY_TEMP, 
				TemperatureEvent.values()), null);
	}
	
	/**
	 * Creates a PacaTraca that is always moving.
	 * Uses standard temperature event probabilities.
	 */
	public PacaTraca createPacaTracaMoveNormalOnly(String sensorID) {
		return new EmulatedPacaTraca(sensorID, null,
				probabilitiesOneEventOnly(MotionEvent.MOVE_NORMAL, 
				MotionEvent.values()));
	}
	
	/**
	 * Creates a PacaTraca that is always grazing.
	 * Uses standard temperature event probabilities.
	 */
	public PacaTraca createPacaTracaGrazeOnly(String sensorID) {
		return new EmulatedPacaTraca(sensorID, null, 
				probabilitiesOneEventOnly(MotionEvent.GRAZE, 
				MotionEvent.values()));
	}
	
	/**
	 * Creates a PacaTraca that never moves.
	 * Uses standard temperature event probabilities.
	 */
	public PacaTraca createPacaTracaStandStillOnly(String sensorID) {
		return new EmulatedPacaTraca(sensorID, null, 
				probabilitiesOneEventOnly(MotionEvent.STAND_STILL, 
				MotionEvent.values()));
	}
	
	/*
	 * Helper method to create a map of the probabilities for each event type
	 * in the event enum, where the probability of 'onlyEvent' is 1.0
	 */
	private <T extends Enum<T>> LinkedHashMap<T, Double> probabilitiesOneEventOnly(
			T onlyEvent, T[] allEvents){
		LinkedHashMap<T, Double> eventProbabilities= 
				new LinkedHashMap<T, Double>();
		for (T event : allEvents) {
			if (event == onlyEvent)
				eventProbabilities.put(event, 1.0);
			else
				eventProbabilities.put(event, 0.0);
		}
		return eventProbabilities;
	}

}
