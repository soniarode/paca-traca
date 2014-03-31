package general;

import java.util.HashMap;

import simulator.PacaTraca;

/**
 * 
 * @author Sonia Rode
 *
 */
public interface PacaTracaSystem {
	
	/**
	 * Starts the UI loop
	 */
	public void startUI();
	
	/**
	 * Returns all the PacaTracas in the system.
	 * 
	 * @return hashmap of sensor ID strings to PacaTracas
	 */
	public HashMap<String, PacaTraca> getPacaTracas();

}
