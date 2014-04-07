package simulator;

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
	
	/*
	 * Creates an EmulatedPacaTraca that changes it's speed every time it moves.
	 */
	public PacaTraca createPacaTracaSpeedChange(String sensorID) {
		return new EmulatedPacaTraca(sensorID, 1);
	}
	
	public PacaTraca createPacaTracaSlowTemperatureDrop(String sensorID) {
		// TODO this is unfinished
		return new EmulatedPacaTraca(sensorID, null);
	}

}
