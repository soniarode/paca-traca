package db;

import java.util.Map;
import db.PushToDB.*;

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
		int counter = 0;
		String curSensorID;
		PacaTraca curSensor;
		
		while (true){
			curSensorID = ui.getCurrentSensorID();
			curSensor = sensors.get(curSensorID);
			ui.txt_ID.setText(curSensorID);
			ui.txt_lat.setText(curSensor.getLatitudeDecimalDegrees().toString());
			ui.txt_lon.setText(curSensor.getLongitudeDecimalDegrees().toString());
			ui.txt_speed.setText(curSensor.getSpeed().toString());
			ui.txt_course.setText(curSensor.getCourse().toString());
			ui.txt_temp.setText(curSensor.getTemperature().toString());
			ui.txt_altitude.setText(curSensor.getAltitude().toString());
			if (counter % 5 == 0)
			{
				//push_data_to_DB(curSensor);
			}
			
		}
		//Was testing DB communication
		//ui.DisplayDataInUI();
	}



}
