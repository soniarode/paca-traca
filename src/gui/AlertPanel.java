package gui;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.Date;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import general.Alert;
import general.AlertUtils;
import general.Settings;

/**
 * 
 * @author Sonia Rode
 *
 * JPanel for displaying and manipulating Alerts. 
 * 
 */
public class AlertPanel extends JPanel implements ActionListener{

	private static final long serialVersionUID = 1L;
	
	private JTextArea alertArea;         // for displaying alerts
	private JTextField boundariesField;  // farm boundary points
	private JButton setBoundariesButton;
	private Settings settings;
	private Collection<Alert> alerts;
	
	/**
	 * Create a new AlertPanel.
	 * 
	 * @param settings the Settings for the system
	 * @param alerts the Alerts for all alpacas
	 */
	public AlertPanel(Settings settings, Collection<Alert> alerts){
		this.settings = settings;
		this.alerts = alerts;
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		add(boundaryInput());
		add(new JLabel("ALERTS"));
		alertArea = new JTextArea(20, 30);
        alertArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(alertArea);
        add(scrollPane);
	}
	
	/*
	 * Creates and returns a JPanel for farm boundary points input
	 */
	private JPanel boundaryInput(){
		JPanel boundaryInput = new JPanel();
		boundaryInput.add(new JLabel("Farm boundaries:"));
		boundariesField = new JTextField(25);
		boundariesField.setText(makeBoundariesString());
		boundaryInput.add(boundariesField);
		setBoundariesButton = new JButton("Set boundaries");
		setBoundariesButton.addActionListener(this);
		boundaryInput.add(setBoundariesButton);
		return boundaryInput;
	}
	
	/**
	 * Displays all current alerts in the Alerts text area.
	 */
	public void updateAlertsDisplay(){
		String timestampString = new Date().toString() + "\n";
		displayAlertText(timestampString + AlertUtils.stringOfAllAlerts(alerts));
	}
	
	/**
	 * Displays the given string in the Alerts text area.
	 */
	public void displayAlertText(String text){
		alertArea.append(text + "\n");
		alertArea.setCaretPosition(alertArea.getDocument().getLength());
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		Object sender = event.getSource();
		if (sender == setBoundariesButton) {
			// The user may have changed the boundaries
			AlertUtils.processBoundaryInputs(boundariesField.getText(), settings);
		}
		
	}
	
	/**
	 * Gets the currently set boundaries and creates a String of coordinates
	 * from them.
	 * @return a string of lat-lon coordinates
	 */
	private String makeBoundariesString(){
		float[] latitudeValues = settings.getLatArray();
		float[] longitudeValues = settings.getLonArray();
		if (latitudeValues == null || longitudeValues == null)
			return "";
		StringBuilder boundaries = new StringBuilder();
		for (int i=0; i<latitudeValues.length; i++){
			boundaries.append(("(" + latitudeValues[i] + ", " + longitudeValues[i] + ") "));
		}
		return boundaries.toString();
	}

}
