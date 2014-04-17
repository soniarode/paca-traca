package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * 
 * JPanel for displaying alpaca sensor data.
 * 
 */
public class AlpacaSensorDataPanel extends JPanel implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	
	List<String> sensorIDs;
	int currentSensorIndex;
	
	JTextField txt_ID;
	JTextField txt_sensorID;
	JTextField txt_name;
	JTextField txt_age, txt_gender;
	JTextField txt_lat, txt_lon, txt_speed, txt_course, txt_altitude, txt_temp,
			txt_pitch, txt_roll;
	
	JButton btn_Next;
	JButton btn_Prev;
	JButton btn_First;
	JButton btn_Last;
	
	/**
	 * Creates a new AlpacaSensorDataPanel
	 * 
	 * @param sensorIDs - the sensor ids of all the alpacas in the system
	 */
	public AlpacaSensorDataPanel(List<String> sensorIDs){
		this.sensorIDs = sensorIDs;
		currentSensorIndex = 0;
		createUITextFields();
		add(new JLabel("Alpaca ID: "));
		add(txt_ID);
		add(new JLabel("Sensor ID: "));
		add(txt_sensorID);
		add(new JLabel("Name: "));
		add(txt_name);
		add(new JLabel("Age: "));
		add(txt_age);
		add(new JLabel("Gender: "));
		add(txt_gender);
		add(new JLabel("Latitude: "));
		add(txt_lat);
		add(new JLabel("Longitude: "));
		add(txt_lon);
		add(new JLabel("Speed: "));
		add(txt_speed);
		add(new JLabel("Heading: "));
		add(txt_course);
		add(new JLabel("Altitude: "));
		add(txt_altitude);
		add(new JLabel("Temperature: "));
		add(txt_temp);
		add(new JLabel("Pitch: "));
		add(txt_pitch);
		add(new JLabel("Roll: "));
		add(txt_roll);
		btn_Next = new JButton("Next");
		btn_Prev = new JButton("Prev");
		btn_First = new JButton("First");
		btn_Last = new JButton("Last");
		add(btn_First);
		add(btn_Prev);
		add(btn_Next);
		add(btn_Last);
		btn_Next.addActionListener(this);
		btn_Prev.addActionListener(this);
		btn_First.addActionListener(this);
		btn_Last.addActionListener(this);
	}

	/*
	 * Creates all the text fields
	 */
	private void createUITextFields() {
		txt_ID = new JTextField(10);
		txt_sensorID = new JTextField(10);
		txt_name = new JTextField(10);
		txt_age = new JTextField(10);
		txt_gender = new JTextField(10);
		txt_lat = new JTextField(10);
		txt_lon = new JTextField(10);
		txt_speed = new JTextField(10);
		txt_course = new JTextField(10);
		txt_altitude = new JTextField(10);
		txt_temp = new JTextField(10);
		txt_pitch = new JTextField(10);
		txt_roll = new JTextField(10);
	}
	
	/**
	 * Returns the sensor id of the alpaca whose data should currently
	 * be displayed.
	 */
	public String getCurrentSensorID() {
		return sensorIDs.get(currentSensorIndex);
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		Object what = ae.getSource();
		if (what == btn_Next) {
			btn_Next_Action();
		}
		if (what == btn_Prev) {
			btn_Prev_Action();
		}
		if (what == btn_First) {
			btn_First_Action();
		}
		if (what == btn_Last) {
			btn_Last_Action();
		}
	}
	
	/**
	 * Called when the "Next" button is tapped; shows data for the next
	 * alpaca in the sensor ids list
	 */
	public void btn_Next_Action() {
		if (currentSensorIndex == sensorIDs.size() - 1) {
			currentSensorIndex = 0;
		} else {
			currentSensorIndex++;
		}
	}

	/**
	 * Called when the "Prev" button is tapped; shows data for the previous
	 * alpaca in the sensor ids list
	 */
	public void btn_Prev_Action() {
		if (currentSensorIndex == 0) {
			currentSensorIndex = sensorIDs.size() - 1;
		} else {
			currentSensorIndex--;
		}
	}

	/**
	 * Called when the "First" button is tapped; shows data for the first
	 * alpaca in the sensor ids list
	 */
	public void btn_First_Action() {
		currentSensorIndex = 0;
	}

	/**
	 * Called when the "Last" button is tapped; shows data for the last
	 * alpaca in the sensor id's list
	 */
	public void btn_Last_Action() {
		currentSensorIndex = sensorIDs.size() - 1;
	}

}
