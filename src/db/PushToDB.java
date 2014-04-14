package db;
import java.sql.*;

import simulator.PacaTraca;



//This class pushes data to the DB
public class PushToDB {	
	Connection Sensor_CON;
	Statement STA;
	
	
	
	
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
	public void push_data_to_DB(PacaTraca curSensor)
	{
		Connection Sensor = ConnectToDB();
		
		try{
			PreparedStatement pstmt = Sensor.prepareStatement("INSERT INTO `Paca-Traca`.`Sensor_Data` " +
					"(`sensor_ID`,`Latitude`,`Longitude`,`Atitude`,`Temperature`,`Pitch`,`Roll`,`Speed`,`Course`,`GPSFix`,`Number_Satellites`,`CompassHeading`,`SignalQuality`)"+
					"VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)");
			try{
				pstmt.setInt(1, 0);
				pstmt.setFloat(2, 0);
				pstmt.setFloat(3, 0);
				pstmt.setFloat(4, 0);
				pstmt.setFloat(5, 0);
				pstmt.setFloat(6, 0);
				pstmt.setFloat(7, 0);
				pstmt.setInt(8, 0);
				pstmt.setInt(9, 0);
				pstmt.setBoolean(10, false);
				pstmt.setInt(11, 0);
				pstmt.setFloat(12, 0);
				pstmt.setInt(13, 0);
				try{
					pstmt.executeUpdate();
				}catch (Exception e){
					System.out.println("First Catch");
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
