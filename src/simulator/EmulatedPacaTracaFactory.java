package simulator;

public class EmulatedPacaTracaFactory implements PacaTracaFactory {

	@Override
	public PacaTraca createPacaTraca(String sensorID) {
		return new EmulatedPacaTraca(sensorID);
	}
	
	/*
	 * Used for unit testing. Creates an EmulatedPacaTraca that changes
	 * it's speed every time it moves.
	 */
	public PacaTraca createPacaTracaSpeedChange(String sensorID) {
		return new EmulatedPacaTraca(sensorID, 1);
	}

}
