package simulator;

import java.util.Random;

/*
 * Static methods for math functionality used by the simulator threads
 */
public class MathUtils {
	
	public static int randomIntBetween(int startInclusive, int endExclusive, 
			Random random){
		if (startInclusive >= endExclusive)
			throw new IllegalArgumentException("Start must be greater than end!");
		return startInclusive + random.nextInt(endExclusive - startInclusive);
	}

}
