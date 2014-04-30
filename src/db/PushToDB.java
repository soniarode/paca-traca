package db;
import general.Settings;

import java.sql.*;

import simulator.PacaTraca;



//This class pushes data to the DB
/*
 * Author Joshua Kostura
 * Populates the DB with information based on the alpaca sensor data
 */
public class PushToDB {	
	Connection Sensor_CON;
	
	public PushToDB(){
		Sensor_CON = ConnectToDB();
	}
	
	
	//Set up a connection for pushing sensor data to the DB
	private Connection ConnectToDB() {
		try {
			// Try loading the mysql driver
			Class.forName("com.mysql.jdbc.Driver");	
			Sensor_CON = DriverManager.getConnection("jdbc:mysql://130.111.197.96/Paca-Traca", "remote",
					"pass");
			} catch (Exception ex) {
				System.out.println(ex);
		}
		return Sensor_CON;
	}
	/* 
	 *  This function pushes sensor data to the DB
	 */
	public void pushSensorData(PacaTraca curSensor,int index)
	{

		
		try{
			PreparedStatement pstmt = Sensor_CON.prepareStatement("INSERT INTO `Paca-Traca`.`Sensor_Data` " +
					"(`index`,`sensor_ID`,`Latitude`,`Longitude`,`Altitude`,`Temperature`,`Pitch`,`Roll`,`Speed`,`Course`,`GPSFix`,`Number_Satellites`,`CompassHeading`,`SignalQuality`)"+
					"VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			try{
				pstmt.setNull(1, index);
				pstmt.setString(2,curSensor.getSensorID());
				pstmt.setFloat(3, curSensor.getLatitudeDecimalDegrees());
				pstmt.setFloat(4, curSensor.getLongitudeDecimalDegrees());
				pstmt.setFloat(5, curSensor.getAltitude());
				pstmt.setFloat(6, curSensor.getTemperature());
				pstmt.setFloat(7, (float) 2.0/*curSensor.getPitch()*/);
				pstmt.setFloat(8, (float) 2.0/*curSensor.getRoll()*/);
				pstmt.setFloat(9, curSensor.getSpeed());
				pstmt.setFloat(10, (float) 2.0/*curSensor.getCourse()*/);
				pstmt.setBoolean(11, false);
				pstmt.setInt(12,curSensor.getNumSatellites());
				pstmt.setFloat(13, curSensor.getCompassHeading() );
				pstmt.setInt(14, curSensor.getSignalQuality() );
				try{
					pstmt.executeUpdate();
				}catch (Exception e){
					System.out.println(e);
				}
			}catch(Exception e){
				System.out.println(e);
				
			}
		}catch(Exception e){
			System.out.println(e);
		}
	}
	/*
	 * Pushes the boundaries to the DB
	 */
	public void pushBoundaryData(float lon,float  lat)
	{
		// INSERT INTO `Paca-Traca`.`Boundaries`(`Index`,`Longitude`,`Latitude`)VALUES(NULL,?,?)
		try{
			PreparedStatement pstmt = Sensor_CON.prepareStatement("INSERT INTO `Paca-Traca`.`Boundaries`(`Index`, `Longitude`,`Latitude`)VALUES(NULL,?,?)");
			try{
				pstmt.setFloat(1,lon);
				pstmt.setFloat(2,lat);
				try{
					pstmt.executeUpdate();
				}catch(Exception e){
					System.out.println(e);
				}
			}catch(Exception e){
				System.out.println(e);
			}
		}catch(Exception e){
			System.out.println(e);
		}
	}

}
