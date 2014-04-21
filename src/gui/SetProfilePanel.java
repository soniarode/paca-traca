package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import general.Alert;
import general.ProfileManager;

/**
 * 
 * @author Sonia Rode
 * 
 * The JPanel that displays what profile is set for the alpaca whose
 * sensor data is currently being displayed. Allows the user to change
 * the profile for the currently displayed alpaca.
 *
 */
public class SetProfilePanel extends JPanel implements ActionListener{

	private static final long serialVersionUID = 1L;
	
	Map<String, Alert> pacaAlerts;
	JButton changeProfileButton;
	JLabel profileLabel, chooseProfileLabel;
	JPanel dialogPanel;
	JComboBox profilesComboBox;
	String currentPacaID;
	ProfileManager profileManager;
	
	/**
	 * 
	 * @param initialPacaID the sensor id of the alpaca whose sensor data is
	 *   initially displaed
	 * @param pacaAlerts map of sensor ids to Alerts
	 * @param profileManager the ProfileManager for the system
	 */
	public SetProfilePanel(String initialPacaID, Map<String, Alert> pacaAlerts,
			ProfileManager profileManager){
		
		currentPacaID = initialPacaID;
		this.pacaAlerts = pacaAlerts;
		this.profileManager = profileManager;
		
		add(makeProfilePanel(initialPacaID));
		
		changeProfileButton = new JButton("Change Profile");
		changeProfileButton.addActionListener(this);
		add(changeProfileButton);
		
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == changeProfileButton){
			// Make the label show the ID of the paca whose Profile is changing
			chooseProfileLabel = new JLabel("Choose Profile for "+currentPacaID);
			
			// Show dialog asking user to choose a profile
			profilesComboBox = new JComboBox(profileManager.allProfileNames().toArray());
			dialogPanel = new JPanel();
			chooseProfileLabel = new JLabel("Choose a Profile.");
			dialogPanel.add(chooseProfileLabel);
			dialogPanel.add(profilesComboBox);
			int userOption = JOptionPane.showConfirmDialog(null, dialogPanel, 
					"Choose Profile", JOptionPane.OK_CANCEL_OPTION);
			if (userOption == JOptionPane.OK_OPTION){
				// Set the current sensorID's alert to use this profile
				pacaAlerts.get(currentPacaID).setProfile(profileManager.getProfileWithName(
						(String)profilesComboBox.getSelectedItem()));
				// Update the label showing the current profile
				profileLabel.setText((String)profilesComboBox.getSelectedItem());
			}
		}
	}
	
	/**
	 * Updates the Profile label to show the Profile from the alpaca with
	 * the given sensor id
	 * 
	 * @param currentPacaID the sensor id of the alpaca whose profile name
	 * should be displayed
	 */
	public void setCurrentPaca(String currentPacaID){
		this.currentPacaID = currentPacaID;
		profileLabel.setText(pacaAlerts.get(currentPacaID).getProfile().getName());
	}
	
	/*
	 * Makes the JPanel that displays the Profile name of the current alpaca
	 */
	private JPanel makeProfilePanel(String initialPacaID){
		JPanel panel = new JPanel();
		panel.add(new JLabel("Current Profile: "));
		profileLabel = new JLabel();
		setCurrentPaca(initialPacaID);
		panel.add(profileLabel);
		return panel;	
	}
	


}
