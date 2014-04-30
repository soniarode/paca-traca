
package db;
import general.Settings;

import java.awt.List;
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
	/*
	 * Gets the boundaries and prints them to the terminal
	 */
	public void getBoundaryData(Settings settings)
	{
		float latArray[] = null;
		float lonArray[] = null;
		try{
			PreparedStatement pstmt = Sensor_CON.prepareStatement("SELECT * FROM `Paca-Traca`.`Boundaries`");
			rs = pstmt.executeQuery();
			System.out.println("Longitude:" + "\t" + "Latiude:");
			while (rs.next()){
				/* These where test cases
				 * to see if the query was working
				float lon = rs.getFloat("Longitude");
				float lat = rs.getFloat("Latitude");
				System.out.println(lon + "\t\t"+ lat);
				*/
				int index = rs.getInt("Index");
				float lon = rs.getFloat("Longitude");
				float lat = rs.getFloat("Latitude");
				latArray[index] = lat;
				lonArray[index] = lon;
				
			}
			settings.setLatArray(latArray);
			settings.setLonArray(lonArray);

		}catch(Exception e){
			System.out.println(e);
		}
	}
	public void pull_data_from_db(String sensor_id)
	{
		/*
		 * SELECT * FROM  `Sensor_Data` WHERE  `sensor_ID` =  'Fluffy6'
		 */
		String selectSQL = "SELECT * FROM Sensor_Data WHERE sensor_ID = ?";
		try{
			PreparedStatement pstmt = Sensor_CON.prepareStatement(selectSQL);
					try{
						pstmt.setString(1, sensor_id);
						try{
							rs = pstmt.executeQuery();
							try{
								pstmt.close();
							}catch (Exception e){
								System.out.println(e);
							}
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
