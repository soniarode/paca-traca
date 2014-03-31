package general;

import simulator.EmulatedPacaTracaFactory;

/**
 * 
 * @author Sonia Rode
 *
 * Main entry point of the app.
 */
public class PacaTracaMain {
	
	public static final int NUM_ALPACAS = 10;

	public static void main(String[] args) {
		PacaTracaSystem system = new PacaTracaSystemImpl(
				new EmulatedPacaTracaFactory(), NUM_ALPACAS);
		
		system.startUI();
	}

}
