package simulator;

public class EmulatedPacaTracaFactory implements PacaTracaFactory {

	@Override
	public PacaTraca createPacaTraca(String sensorID) {
		return new EmulatedPacaTraca(sensorID);
	}

}
