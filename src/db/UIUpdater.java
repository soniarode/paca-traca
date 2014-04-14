package db;

import java.util.Map;

import simulator.PacaTraca;

public class UIUpdater implements Runnable{

	Map<String, PacaTraca> sensors;
	DataUI ui;
	

	public UIUpdater(DataUI ui, Map<String, PacaTraca> tracas){
		this.ui = ui;
		this.sensors = tracas;
	}

	@Override
	public void run() {
		String curSensorID;
		PacaTraca curSensor;
//		while (true){
//			curSensorID = ui.getCurrentSensorID();
//			curSensor = sensors.get(curSensorID);
//			ui.txt_ID.setText(curSensorID);
//			ui.txt_lat.setText(curSensor.getLatitudeDecimalDegrees().toString());
//			ui.txt_lon.setText(curSensor.getLongitudeDecimalDegrees().toString());
//			ui.txt_speed.setText(curSensor.getSpeed().toString());
//			ui.txt_course.setText(curSensor.getCourse().toString());
//			ui.txt_temp.setText(curSensor.getTemperature().toString());
//			ui.txt_altitude.setText(curSensor.getAltitude().toString());
//			
//		}
		//Was testing DB communication
		ui.DisplayDataInUI();
	}



}
