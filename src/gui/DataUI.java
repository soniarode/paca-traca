package gui;


/**
 *
 * @author kemal
 */
import general.Alert;
import general.Settings;
import db.DBPanel;

import javax.swing.*;

import java.util.Collection;
import java.util.List;

/**
 * 
 * The main GUI class.
 *
 */
public class DataUI{

	JFrame MainWindow;
	DBPanel dbPanel;
	AlertPanel alertPanel;
	AlpacaSensorDataPanel sensorDataPanel;

	/**
	 * Create the GUI window
	 */
	public DataUI(List<String> sensorIDs, Settings settings, 
			Collection<Alert> alerts) {
		CreateUI(sensorIDs, settings, alerts);
	}

	/**
	 * Returns the sensor ID of the alpaca whose sensor data is currently
	 * displayed on the GUI.
	 */
	public String getCurrentSensorID() {
		return sensorDataPanel.getCurrentSensorID();
	}

	/*
	 * Sets up the window
	 */
	private void CreateUI(List<String> sensorIDs,
			Settings settings, Collection<Alert> alerts) {
		MainWindow = new JFrame();
		BoxLayout windowLayout = new BoxLayout(MainWindow.getContentPane(), 
				BoxLayout.Y_AXIS);
		MainWindow.setLayout(windowLayout);
		MainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Create JPanel for alpaca data
		this.sensorDataPanel = new AlpacaSensorDataPanel(sensorIDs);
		
		// Create JPanel for DB stuff
		this.dbPanel = new DBPanel();
	
		// Create JPanel for Alerts
		this.alertPanel = new AlertPanel(settings, alerts);
		
		MainWindow.getContentPane().add(sensorDataPanel);
		MainWindow.getContentPane().add(dbPanel);
		MainWindow.getContentPane().add(alertPanel);
		MainWindow.pack();
		MainWindow.setSize(840, 900);
		
		MainWindow.setVisible(true);
	}

	/**
	 * Refreshes the Alerts text area with the latest alerts.
	 */
	public void updateAlertsDisplay(){
		alertPanel.updateAlertsDisplay();
	}
	
	/**
	 * Sets the alpaca ID text field value
	 */
	public void setIDText(String id){
		sensorDataPanel.txt_ID.setText(id);
	}
	
	/**
	 * Sets the alpaca speed text field value
	 */
	public void setSpeedText(String speed){
		sensorDataPanel.txt_speed.setText(speed);
	}
	
	/**
	 * Sets the alpaca latitude text field value
	 */
	public void setLatitudeText(String lat){
		sensorDataPanel.txt_lat.setText(lat);
	}
	
	/**
	 * Sets the alpaca longitude text field value
	 */
	public void setLongitudeText(String lon){
		sensorDataPanel.txt_lon.setText(lon);
	}
	
	/**
	 * Sets the alpaca course text field value
	 */
	public void setCourseText(String course){
		sensorDataPanel.txt_course.setText(course);
	}
	
	/**
	 * Sets the alpaca temperature text field value
	 */
	public void setTemperatureText(String temp){
		sensorDataPanel.txt_temp.setText(temp);
	}
	
	/**
	 * Sets the alpaca altitude text field
	 */
	public void setAltitudeText(String alt){
		sensorDataPanel.txt_altitude.setText(alt);
	}

}
