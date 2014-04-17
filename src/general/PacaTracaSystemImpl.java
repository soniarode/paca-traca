package general;

import gui.AlertUpdater;
import gui.DataUI;
import gui.UIUpdater;

import java.util.ArrayList;
import java.util.HashMap;

import simulator.PacaTraca;
import simulator.PacaTracaFactory;

/**
 * 
 * @author Sonia Rode
 *
 * Sets up the major objects in the system, and starts various threads.
 * 
 */
public class PacaTracaSystemImpl implements PacaTracaSystem {
	
	HashMap<String, PacaTraca> pacaTracas;
	Settings settings;
	HashMap<String, Alert> pacaTracaAlerts;
	DataUI dataUI;
	UIUpdater uiUpdater;
	
	/**
	 * Starts up the GUI and the background threads.
	 * 
	 * @param alpacaFactory - factory for creating PacaTracas
	 * @param numAlpacas - the number of alpacas to put in the system
	 */
	public PacaTracaSystemImpl(PacaTracaFactory alpacaFactory, int numAlpacas){
		
		// Create the requested number of PacaTracas
		pacaTracas = new HashMap<String, PacaTraca>();
		for (int i=0; i<numAlpacas; i++){
			String sensorID = "Fluffy" + Integer.toString(i);
			pacaTracas.put(sensorID, alpacaFactory.createPacaTraca(sensorID));
		}
		
		// Create a Settings object to use throughout the system
		settings = new SettingsImpl();	
		settings.getBoundaries(); // Initializes the Settings object
		
		// Create an Alert object for each PacaTraca
		pacaTracaAlerts = new HashMap<String, Alert>();
		for (String pacaID : pacaTracas.keySet()){
			PacaTraca pacaTracaForKey = pacaTracas.get(pacaID);
			pacaTracaAlerts.put(pacaID, new Alert(pacaTracaForKey, settings));
		}
		
		// Create the GUI
		dataUI = new DataUI(new ArrayList<String>(pacaTracas.keySet()), settings, 
				pacaTracaAlerts.values());
		
		// Create threads to update the GUI with live sensor data and alerts
		uiUpdater = new UIUpdater(dataUI, pacaTracas, pacaTracaAlerts);
		Thread uiUpdateThread = new Thread(uiUpdater);
		uiUpdateThread.start();
		Thread alertUpdateThread = new Thread(new AlertUpdater(dataUI));
		alertUpdateThread.start();
	}

	@Override
	public void startUI() {
		new ConsoleUIMain(pacaTracas, pacaTracaAlerts, settings).run();
	}

	@Override
	public HashMap<String, PacaTraca> getPacaTracas() {
		return pacaTracas;
	}

}
