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
	public void push_data_to_DB(PacaTraca curSensor)
	{

		
		try{
			PreparedStatement pstmt = Sensor_CON.prepareStatement("INSERT INTO `Paca-Traca`.`Sensor_Data` " +
					"(`sensor_ID`,`Latitude`,`Longitude`,`Altitude`,`Temperature`,`Pitch`,`Roll`,`Speed`,`Course`,`GPSFix`,`Number_Satellites`,`CompassHeading`,`SignalQuality`)"+
					"VALUES (?,?,?,?,?,?,?,?,?,?,?,?)");
			try{
				//pstmt.setInt(1, 0);
				pstmt.setFloat(1, 0);
				pstmt.setFloat(2, 0);
				pstmt.setFloat(3, 0);
				pstmt.setFloat(4, 0);
				pstmt.setFloat(5, 0);
				pstmt.setFloat(6, 0);
				pstmt.setInt(7, 0);
				pstmt.setInt(8, 0);
				pstmt.setBoolean(9, false);
				pstmt.setInt(10, 0);
				pstmt.setFloat(11, 0);
				pstmt.setInt(12, 0);
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
