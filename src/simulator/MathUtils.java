package simulator;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

/**
 * 
 * @author Sonia Rode
 * 
 * Static methods for math functionality used by the simulator threads.
 * 
 */
public class MathUtils {
	
	/**
	 * Returns a random integer that is between two integers
	 */
	public static int randomIntBetween(int startInclusive, int endExclusive, 
			Random random){
		if (startInclusive >= endExclusive)
			throw new IllegalArgumentException("Start must be greater than end!");
		return startInclusive + random.nextInt(endExclusive - startInclusive);
	}
	
	/**
	 * Returns the event enum whose probability range in the 
	 * eventProbabilities map includes the diceValue. The probability range
	 * of an event == [sum of all the probabilities before the event in the
	 * map, sum of all the probabilities up to and including the event in the
	 * map]
	 * 
	 * @param diceValue a value in [0, 1.0)
	 * @param eventProbabilities maps events to their probabilities. The values
	 * 		in this map must sum to 1.0
	 * @return the chosen event
	 */
	public static <T extends Enum<T>> T chooseEventForProbability(double diceValue,
			LinkedHashMap<T, Double> eventProbabilities){
		double sumProbabilities = 0;
		// LinkedHashMap has predictable iteration order
		for (Map.Entry<T, Double> eventAndProbability : eventProbabilities.entrySet()) {
			sumProbabilities += eventAndProbability.getValue();
			if (diceValue < sumProbabilities)
				return eventAndProbability.getKey();
		}
		throw new RuntimeException("Error: Either probabilites in eventProbabilites "
				+ "do not sum to one, or a dice value > 1 was passed as an argument");
	}

}
