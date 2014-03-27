package simulator;

import java.util.HashMap;

/**
 * @author Bryan Wells
 * Very simple class to instantiate a few sensors, store them in a map
 * and then get them from the map and print out their values
 * 
 */
public class PacaTracaTestDriver {

	private HashMap< String, PacaTraca > m_sensors = new HashMap< String, PacaTraca >( );
	private String m_sensor1ID = "sensor1-ID";
	private String m_sensor2ID = "sensor2-ID";

	/**
	 * the main module
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PacaTracaTestDriver testCase = new PacaTracaTestDriver( );
		testCase.runTests();
	}
	
	/**
	 * @author Bryan Wells
	 * Create a factory (a hardware factory in this case)
	 * create some test sensors using the factory
	 * verify the created sensors
	 */
	public void runTests( ) {
		PacaTracaFactory factory = new PacaTracaHardwareFactory( );
		this.createTestSensors( factory );
		this.verifySensors(  );
	}
		
	/**
	 * @author Bryan Wells
	 * ask the factory to create our sensors (any kind will be fine)
	 * and then put these sensors into a hash map for safe keeping
	 */
	public void createTestSensors( PacaTracaFactory factory ) {
		m_sensors.put( m_sensor1ID, factory.createPacaTraca( m_sensor1ID ) );
		m_sensors.put( m_sensor2ID, factory.createPacaTraca( m_sensor2ID ) );
	}
	
	/**
	 * @author Bryan Wells
	 * using the hash map holding our sensors, get each sensor (any kind will do)
	 * and ask the sensor to give us its values as a string using the toString method
	 * print these string sensor values out and return
	 */
	public void verifySensors( ) {
		System.out.println("Sensor 1: " + m_sensors.get( m_sensor1ID ).toString( ) );
		System.out.println("Sensor 2: " + m_sensors.get( m_sensor2ID ).toString( ) );
	}
}
