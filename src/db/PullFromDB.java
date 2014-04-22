
package db;
import java.sql.*;

/*
 * Author Joshua Kostura
 * This Class is designed to pull information from the DB
 * The beginning implementation for our replay feature. 
 */
public class PullFromDB {

	Connection Sensor_CON;
	Statement STA;
	ResultSet rs;
	
	public PullFromDB(){
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
	public void pull_data_from_db(String sensor_id)
	{
		/*
		 * SELECT * FROM  `Sensor_Data` WHERE  `sensor_ID` =  'Fluffy6'
		 */
		try{
			PreparedStatement pstmt = Sensor_CON.prepareStatement("SELECT * FROM `Paca-Traca`.Sensor_Data` WHERE `sensor_ID` = ?");
					try{
						pstmt.setString(1, sensor_id);
						try{
							rs = pstmt.executeQuery();
						}catch(Exception e)
						{
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
