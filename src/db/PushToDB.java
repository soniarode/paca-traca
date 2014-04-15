package db;
import java.sql.*;

import simulator.PacaTraca;



//This class pushes data to the DB
public class PushToDB {	
	Connection Sensor_CON;
	Statement STA;
	
	PushToDB(){
		Sensor_CON = ConnectToDB();
	}
	
	
	//Set up a connection for pushing sensor data to the DB
	private Connection ConnectToDB() {
		try {
			// Try loading the mysql driver
			Class.forName("com.mysql.jdbc.Driver");	
			// 	Create connection to DB, username and password stored in plain
			// 	here
			// eventually we will make a config file
			Sensor_CON = DriverManager.getConnection("jdbc:mysql://130.111.197.96/Paca-Traca", "remote",
					"pass");
			} catch (Exception ex) {
				System.out.println(ex);
		}
		return Sensor_CON;
	}
	public void push_data_to_DB(PacaTraca curSensor,int index)
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
//				try{
//					Sensor_CON.close();
//				}catch (Exception e){
//					System.out.println(e);
//				}
			}catch(Exception e){
				System.out.println(e);
				
			}
		}catch(Exception e){
			System.out.println(e);
		}
	}
}
