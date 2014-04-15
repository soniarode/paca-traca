package gui;

import java.lang.reflect.InvocationTargetException;

import javax.swing.SwingUtilities;

import db.DataUI;

/**
 * 
 * @author Sonia Rode
 * 
 * Thread that updates the GUI with Alerts at a regular interval
 *
 */
public class AlertUpdater implements Runnable {
	
	private static final int THREAD_SLEEP_TIME_MILLIS = 10000;
	
	DataUI gui;
	
	public AlertUpdater(DataUI gui){
		this.gui = gui;
	}

	@Override
	public void run() {
		while(true){
			// Updating the GUI must be done on the Swing event dispatch thread
			try {
				SwingUtilities.invokeAndWait(new Runnable(){

					@Override
					public void run() {
						gui.updateAlertsDisplay();
					}
					
				});
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			} catch (InvocationTargetException e1) {
				e1.printStackTrace();
			}
			
			try {
				Thread.sleep(THREAD_SLEEP_TIME_MILLIS);
			} catch (InterruptedException e) {
				break;
			}
		}
	}

}
