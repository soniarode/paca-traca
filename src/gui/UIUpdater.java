package gui;

import general.Alert;
import general.Settings;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Map;

import javax.swing.SwingUtilities;

import db.PushToDB;
import db.PullFromDB;
import simulator.PacaTraca;

/**
 * Run loop that continually updates the sensor data text fields in the GUI with
 * the latest sensor data from the hardware.
 */
public class UIUpdater implements Runnable {

	Map<String, PacaTraca> sensors;
	Map<String, Alert> alerts;
	DataUI ui;
	MapPanel map;

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
		
		PushToDB DB = new PushToDB();
		PullFromDB PDB = new PullFromDB();

		while (true) {
			String curSensorID = ui.getCurrentSensorID();
			PacaTraca curSensor = sensors.get(curSensorID);
			int counter = 0;
			// Pull data from sensors and update GUI with it
			try {
				// Updating the GUI must be done on the Swing event dispatch
				// thread
				SwingUtilities.invokeAndWait(new Runnable() {

					@Override
					public void run() {
						String curSensorID = ui.getCurrentSensorID();
						PacaTraca curSensor = sensors.get(curSensorID);
						ui.setIDText(curSensorID);
						ui.setLatitudeText(curSensor
								.getLatitudeDecimalDegrees().toString());
						ui.setLongitudeText(curSensor
								.getLongitudeDecimalDegrees().toString());
						ui.setSpeedText(curSensor.getSpeed().toString());
						ui.setCourseText(curSensor.getCourse().toString());
						ui.setTemperatureText(curSensor.getTemperature()
								.toString());
						ui.setAltitudeText(curSensor.getAltitude().toString());
						ui.setPitchText(curSensor.getPitch().toString());
						ui.setRollText(curSensor.getRoll().toString());
						ui.updateMapPanel(sensors.values(),curSensorID);
					}

				});
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}

			// Messing with the alert stuff
			for (Alert alert : alertObjects) {
				alert.updateAlert(curSensor.getAltitude(),
						curSensor.getLatitudeDecimalDegrees(),
						curSensor.getLongitudeDecimalDegrees());
			}

			/*
			 * @josh we now update data about each alpaca ever time
			 * the while loop runs.
			 */
			for (PacaTraca sensor : sensors.values() )
			{
				DB.pushSensorData(sensor,counter);
				counter++;
			}



		}

	}

}
