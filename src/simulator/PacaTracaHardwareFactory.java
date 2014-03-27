package simulator;

public class PacaTracaHardwareFactory implements PacaTracaFactory {

	@Override
	public PacaTraca createPacaTraca(String sensorID) {
		// TODO Auto-generated method stub
		return new PacaTracaHardware( sensorID );
	}
}
