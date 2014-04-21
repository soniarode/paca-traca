package gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import general.Profile;
import general.ProfileManager;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * 
 * @author Sonia Rode
 * 
 * JPanel for creating and editing Profiles.
 *
 */
public class ProfileManagerPanel extends JPanel implements ActionListener{

	private static final long serialVersionUID = 1L;

	private ProfileManager profileManager;
	private JTextField lowerTempField, upperTempField, lowerPitchField,
	upperPitchField, lowerRollField, upperRollField, lowerAltField, 
	upperAltField;
	private JButton createNewButton, saveChangesButton;
	private JComboBox profilesDropDownList;

	/**
	 * 
	 * @param profileManager the ProfileManager for the system
	 */
	public ProfileManagerPanel(ProfileManager profileManager){
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.profileManager = profileManager;
		createUI();
	}

	/*
	 * Create the components and lay everything out
	 */
	private void createUI(){

		// Create the drop down list
		JPanel profileListPanel = new JPanel();
		profileListPanel.add(new JLabel("Profile Name:"));
		profilesDropDownList = 
				new JComboBox(profileManager.allProfileNames().toArray());
		profilesDropDownList.addActionListener(this);
		profileListPanel.add(profilesDropDownList);
		this.add(profileListPanel);

		// Create the labels/text fields showing the profile values
		createTextFields();
		JPanel profileValues = new JPanel(new GridLayout(1,4));
		profileValues.add(createOneRowPanel("temperature", lowerTempField, upperTempField));
		profileValues.add(createOneRowPanel("pitch", lowerPitchField, upperPitchField));
		profileValues.add(createOneRowPanel("roll", lowerRollField, upperRollField));
		profileValues.add(createOneRowPanel("altitude", lowerAltField, upperAltField));
		add(profileValues);
		refreshTextFieldsFromCurrentProfile();

		// Create the button panel
		saveChangesButton = new JButton("Save Changes");
		saveChangesButton.addActionListener(this);
		createNewButton = new JButton("Save As New");
		createNewButton.addActionListener(this);
		JPanel savePanel = new JPanel();
		savePanel.add(saveChangesButton);
		savePanel.add(createNewButton);
		add(savePanel);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		Object eventSource = event.getSource();
		if (eventSource == createNewButton) {
			createNewProfileAction();
		}
		else if (eventSource == saveChangesButton) {
			saveChangesAction();
		}
		else if (eventSource == profilesDropDownList){
			refreshTextFieldsFromCurrentProfile();
		}
	}

	/*
	 * Called when the "Save As New" button is tapped
	 */
	private void createNewProfileAction(){
		String newProfileName = JOptionPane.showInputDialog("Enter the new Profile name"); 
		Profile newProfile = ProfileManager.createEmptyProfile();
		newProfile.setName(newProfileName);
		setProfileWithTextFields(newProfile);
		profileManager.addProfile(newProfile);
		profilesDropDownList.addItem(newProfileName);
		refreshTextFieldsFromCurrentProfile();
	}

	/*
	 * Called when the "Save Changes" button is tapped
	 */
	private void saveChangesAction(){
		String editedProfileName = (String)profilesDropDownList.getSelectedItem();
		Profile editedProfile = profileManager.getProfileWithName(editedProfileName);
		setProfileWithTextFields(editedProfile);
	}

	/*
	 * Helper method to create all the text fields.
	 */
	private void createTextFields(){
		lowerTempField = new JTextField(10);
		upperTempField = new JTextField(10);
		lowerPitchField = new JTextField(10);
		upperPitchField = new JTextField(10);
		lowerRollField = new JTextField(10);
		upperRollField = new JTextField(10);
		lowerAltField = new JTextField(10);
		upperAltField = new JTextField(10);
	}

	/*
	 * Helper method to create the layout for one Profile value bounds
	 */
	private JPanel createOneRowPanel(String valueName, JTextField lowerField,
			JTextField upperField){
		JPanel rowPanel = new JPanel();
		rowPanel.add(new JLabel("Lower "+valueName+" bound:"));
		rowPanel.add(lowerField);
		rowPanel.add(new JLabel("Upper "+valueName+" bound:"));
		rowPanel.add(upperField);
		return rowPanel;
	}

	/*
	 * Updates the text fields to show the values from the Profile whose
	 * name is currently selected in the drop down menu
	 */
	private void refreshTextFieldsFromCurrentProfile(){
		Profile currentProfile = profileManager.getProfileWithName(
				(String)profilesDropDownList.getSelectedItem());
		lowerTempField.setText(String.valueOf(currentProfile.getLowerTempBound()));
		upperTempField.setText(String.valueOf(currentProfile.getUpperTempBound()));
		lowerPitchField.setText(String.valueOf(currentProfile.getLowerPitchBound()));
		upperPitchField.setText(String.valueOf(currentProfile.getUpperPitchBound()));
		lowerRollField.setText(String.valueOf(currentProfile.getLowerRollBound()));
		upperRollField.setText(String.valueOf(currentProfile.getUpperRollBound()));
		lowerAltField.setText(String.valueOf(currentProfile.getLowerAltitudeBound()));
		upperAltField.setText(String.valueOf(currentProfile.getUpperAltitudeBound()));
	}

	/*
	 * Sets a Profile with the values from the text fields.
	 */
	private void setProfileWithTextFields(Profile profile){
		if (validateTextFieldInput()){
			profile.setLowerAltitudeBound(Float.valueOf(lowerAltField.getText()));
			profile.setUpperAltitudeBound(Float.valueOf(upperAltField.getText()));
			profile.setLowerTempBound(Float.valueOf(lowerTempField.getText()));
			profile.setUpperTempBound(Float.valueOf(upperTempField.getText()));
			profile.setLowerPitchBound(Float.valueOf(lowerPitchField.getText()));
			profile.setUpperPitchBound(Float.valueOf(upperPitchField.getText()));
			profile.setLowerRollBound(Float.valueOf(lowerRollField.getText()));
			profile.setUpperRollBound(Float.valueOf(upperRollField.getText()));
		}
		else {
			JOptionPane.showMessageDialog(this, "Bad input for one or more fields!");
		}
	}
	
	/*
	 * Returns true if the input in all the text fields is floatable
	 */
	private boolean validateTextFieldInput(){
		try {
			Float.valueOf(lowerTempField.getText());
			Float.valueOf(upperTempField.getText());
			Float.valueOf(upperPitchField.getText());
			Float.valueOf(lowerPitchField.getText());
			Float.valueOf(upperRollField.getText());
			Float.valueOf(lowerRollField.getText());
			Float.valueOf(upperAltField.getText());
			Float.valueOf(lowerAltField.getText());
		} catch (NumberFormatException e){
			return false;
		}
		return true;
	}

}
