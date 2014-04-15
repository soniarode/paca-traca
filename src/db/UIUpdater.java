package db;

import general.Alert;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Map;

import javax.swing.SwingUtilities;

import db.PushToDB.*;
import simulator.PacaTraca;

public class UIUpdater implements Runnable {

	Map<String, PacaTraca> sensors;
	Map<String, Alert> alerts;
	DataUI ui;

	Collection<Alert> alertObjects;

	public UIUpdater(DataUI ui, Map<String, PacaTraca> tracas,
			Map<String, Alert> alerts) {
		this.ui = ui;
		this.sensors = tracas;
		this.alerts = alerts;
		alertObjects = alerts.values();
	}

	@Override
	public void run() {
		int counter = 0;
		PushToDB DB = new PushToDB();
		while (true) {
			String curSensorID = ui.getCurrentSensorID();
			PacaTraca curSensor = sensors.get(curSensorID);
			// Pull data from sensors and update gui with it
			try {
				// Updating the GUI must be done on the Swing event dispatch
				// thread
				SwingUtilities.invokeAndWait(new Runnable() {

					@Override
					public void run() {
						String curSensorID = ui.getCurrentSensorID();
						PacaTraca curSensor = sensors.get(curSensorID);
						ui.txt_ID.setText(curSensorID);
						ui.txt_lat.setText(curSensor
								.getLatitudeDecimalDegrees().toString());
						ui.txt_lon.setText(curSensor
								.getLongitudeDecimalDegrees().toString());
						ui.txt_speed.setText(curSensor.getSpeed().toString());
						ui.txt_course.setText(curSensor.getCourse().toString());
						ui.txt_temp.setText(curSensor.getTemperature()
								.toString());
						ui.txt_altitude.setText(curSensor.getAltitude()
								.toString());
					}

				});
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}

			// Messing with the alert stuff
			for (Alert alert: alertObjects){
				alert.updateAlert(curSensor.getAltitude(),
						curSensor.getLatitudeDecimalDegrees(),
						curSensor.getLongitudeDecimalDegrees());
			}

		 	// Trying to test pushing sensor data to the database.
			if (counter % 5 == 0) {
				//Still a work in progess
				DB.push_data_to_DB(curSensor,counter);
			}
			counter++;

		}
		// Was testing DB communication
		// ui.DisplayDataInUI();
	}

}
