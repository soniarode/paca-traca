package gui;

/**
 *
 * @author kemal
 */
import general.Alert;
import general.ProfileManager;
import general.Settings;
import db.DBPanel;

import javax.swing.*;

import simulator.PacaTraca;

import java.util.List;
import java.util.Collection;
import java.util.Map;

/**
 * 
 * The main GUI class.
 * 
 */
public class DataUI {

	JFrame MainWindow;
	DBPanel dbPanel;
	AlertPanel alertPanel;
	AlpacaSensorDataPanel sensorDataPanel;
	ProfileManagerPanel profilePanel;
	MapPanel mapPanel;

	/**
	 * Create the GUI window
	 */
	public DataUI(List<String> sensorIDs, Settings settings,
			Map<String, Alert> alerts, ProfileManager profileManager) {
		CreateUI(sensorIDs, settings, alerts, profileManager);
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
	private void CreateUI(List<String> sensorIDs, Settings settings,
			Map<String, Alert> alerts, ProfileManager profileManager) {
		MainWindow = new JFrame();
		MainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Create JPanel for alpaca data
		this.sensorDataPanel = new AlpacaSensorDataPanel(sensorIDs, alerts,
				profileManager);

		// Create JPanel for DB stuff
		this.dbPanel = new DBPanel();

		// Create JPanel for Alerts
		this.alertPanel = new AlertPanel(settings, alerts.values());

		// Create JPanel for Profiles
		this.profilePanel = new ProfileManagerPanel(profileManager);

		// Create JPanel for Map
		this.mapPanel = new MapPanel(settings, sensorIDs);

		// Create a container panel for the sensor data and db buttons since
		// both are on same tab
		JPanel pacaPanel = new JPanel();
		pacaPanel.setLayout(new BoxLayout(pacaPanel, BoxLayout.Y_AXIS));
		pacaPanel.add(sensorDataPanel);
		pacaPanel.add(mapPanel);
		/*
		 * @josh: change the layout of the dbPanel so that the maps and buttons
		 * can be viewed correctly
		 */
		dbPanel.setLayout(new BoxLayout(dbPanel, BoxLayout.PAGE_AXIS));

		// Create the tabbed pane
		/*
		 * @josh:made the database a tab so I can make a replay map
		 */
		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.add("Alpaca Data", pacaPanel);
		tabbedPane.add("Alerts", alertPanel);
		tabbedPane.add("Profiles", profilePanel);
		/*
		 * @Zack: Removed map from tab pane and included it in regular pane
		 * sensor data pane 
		 * tabbedPane.add("Map", mapPanel);
		 */
		tabbedPane.add("Database", dbPanel);

		MainWindow.getContentPane().add(tabbedPane);
		MainWindow.pack();
		MainWindow.setSize(850, 700);

		MainWindow.setVisible(true);
	}

	/**
	 * Refreshes the Alerts text area with the latest alerts.
	 */
	public void updateMapPanel(Collection<PacaTraca> tracas, String ID) {
		mapPanel.update(tracas, ID);
	}

	/**
	 * Refreshes the Alerts text area with the latest alerts.
	 */
	public void updateAlertsDisplay() {
		alertPanel.updateAlertsDisplay();
	}

	/**
	 * Sets the alpaca ID text field value
	 */
	public void setIDText(String id) {
		sensorDataPanel.txt_ID.setText(id);
	}

	/**
	 * Sets the alpaca speed text field value
	 */
	public void setSpeedText(String speed) {
		sensorDataPanel.txt_speed.setText(speed);
	}

	/**
	 * Sets the alpaca latitude text field value
	 */
	public void setLatitudeText(String lat) {
		sensorDataPanel.txt_lat.setText(lat);
	}

	/**
	 * Sets the alpaca longitude text field value
	 */
	public void setLongitudeText(String lon) {
		sensorDataPanel.txt_lon.setText(lon);
	}

	/**
	 * Sets the alpaca course text field value
	 */
	public void setCourseText(String course) {
		sensorDataPanel.txt_course.setText(course);
	}

	/**
	 * Sets the alpaca temperature text field value
	 */
	public void setTemperatureText(String temp) {
		sensorDataPanel.txt_temp.setText(temp);
	}

	/**
	 * Sets the alpaca altitude text field
	 */
	public void setAltitudeText(String alt) {
		sensorDataPanel.txt_altitude.setText(alt);
	}

}
