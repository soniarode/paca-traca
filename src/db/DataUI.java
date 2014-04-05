package db;
/*
 * Tried to create a simple UI, connect to DB and select/display some 
 * data into UI
 * 
 */

/**
 *
 * @author kemal
 */
import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DataUI implements ActionListener {
  
    //define global variables
    Connection CON;
    Statement STA;
    ResultSet RES;
    
    //define UI global variables
    
    JFrame MainWindow;
    JLabel lbl_ID;
    JLabel lbl_sensorID;
    JLabel lbl_name;
    JLabel lbl_age, lbl_gender;
    JLabel lbl_lat, lbl_lon, lbl_speed, lbl_course, lbl_altitude, lbl_temp,
    	lbl_pitch, lbl_roll;
    JTextField txt_ID;       
    JTextField txt_sensorID;    
    JTextField txt_name;   
    JTextField txt_age, txt_gender; 
    JTextField txt_lat, txt_lon, txt_speed, txt_course, txt_altitude, txt_temp,
    	txt_pitch, txt_roll;
    JButton btn_Next=new JButton("Next");
    JButton btn_Prev=new JButton("Prev");
    JButton btn_First=new JButton("First");
    JButton btn_Last=new JButton("Last");
    

    public DataUI() {
        //initialize the connection to MySQL database
        ConnectToDB();
        //create statement, execute SQL query against the DB and obtain ResultSet
        SelectDataFromDB();
        //Initialize GUI (MainWindow, text fields, labels and buttons for navigating through returned ResultSet)
        CreateUI();
        //Bind the fields from DB to our GUI
        DisplayDataInUI();
    }
    
    public Connection getDBconnection(){
    	return CON;
    }
    

    private void ConnectToDB() {
        try
        {
            //Try loading the mysql driver
            Class.forName("com.mysql.jdbc.Driver");
            // Create connection to DB, username and password stored in plain here
            // eventually we will make a config file
            CON= DriverManager.getConnection("jdbc:mysql://blah","blah","blah");
        }
        catch(Exception ex)
        {
            System.out.println(ex);
        } 
    }

    private void SelectDataFromDB() 
    {
        try
        {
            //create statement object, execute SQL on DB and getting the RS object back
            STA=CON.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            String query="SELECT * FROM alpaca_test";
            RES=STA.executeQuery(query);
            
        }
        catch(Exception ex)
        {
            System.out.println(ex);
        } 
    }
    
    private void CreateUI() 
    {
        MainWindow=new JFrame();
        MainWindow.setSize(640, 300);
        MainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        createUILabels();
        createUITextFields();
        
        JPanel InfoPanel=new JPanel();
        InfoPanel.add(lbl_ID);
        InfoPanel.add(txt_ID);
        InfoPanel.add(lbl_sensorID);
        InfoPanel.add(txt_sensorID);
        InfoPanel.add(lbl_name);
        InfoPanel.add(txt_name);
        InfoPanel.add(lbl_age);
        InfoPanel.add(txt_age);
        InfoPanel.add(lbl_gender);
        InfoPanel.add(txt_gender);
        InfoPanel.add(lbl_lat);
        InfoPanel.add(txt_lat);
        InfoPanel.add(lbl_lon);
        InfoPanel.add(txt_lon);
        InfoPanel.add(lbl_speed);
        InfoPanel.add(txt_speed);
        InfoPanel.add(lbl_course);
        InfoPanel.add(txt_course);
        InfoPanel.add(lbl_altitude);
        InfoPanel.add(txt_altitude);
        InfoPanel.add(lbl_temp);
        InfoPanel.add(txt_temp);
        InfoPanel.add(lbl_pitch);
        InfoPanel.add(txt_pitch);
        InfoPanel.add(lbl_roll);
        InfoPanel.add(txt_roll);
        InfoPanel.add(btn_First);
        InfoPanel.add(btn_Prev);
        InfoPanel.add(btn_Next);
        InfoPanel.add(btn_Last);
        
        btn_Next.addActionListener(this);
        btn_Prev.addActionListener(this);
        btn_First.addActionListener(this);
        btn_Last.addActionListener(this);
        
        MainWindow.add(InfoPanel);
        MainWindow.setVisible(true);

    }
    
    private void createUILabels(){
        lbl_ID=new JLabel("Alpaca ID: ");
        lbl_sensorID=new JLabel("Sensor ID: ");
        lbl_name=new JLabel("Name: ");
        lbl_age=new JLabel("Age: ");
        lbl_gender = new JLabel("Gender: ");
        lbl_lat =  new JLabel("Latitude: ");
        lbl_lon =  new JLabel("Longitude: ");
        lbl_speed = new JLabel("Speed: "); 
        lbl_course  = new JLabel("Heading: "); 
        lbl_altitude  = new JLabel("Altitude: "); 
        lbl_temp  = new JLabel("Temperature: ");
        lbl_pitch = new JLabel("Pitch: "); 
        lbl_roll = new JLabel("Roll: ");
    }
    
    private void createUITextFields(){
        txt_ID=new JTextField(10);
        txt_sensorID=new JTextField(10);
        txt_name=new JTextField(10);
        txt_age=new JTextField(10);
        txt_gender=new JTextField(10);
        txt_lat=new JTextField(10);
        txt_lon=new JTextField(10); 
        txt_speed=new JTextField(10); 
        txt_course=new JTextField(10); 
        txt_altitude=new JTextField(10); 
        txt_temp=new JTextField(10);
    	txt_pitch=new JTextField(10); 
    	txt_roll=new JTextField(10);
    }
    
    private void DisplayDataInUI() 
    {
        try
        {
            RES.next();
            txt_ID.setText(RES.getString("ID"));
            txt_sensorID.setText(RES.getString("Sensor_ID"));
            txt_name.setText(RES.getString("Name"));
            txt_age.setText(RES.getString("Age"));
        }
        catch(Exception ex)
        {
            System.out.println(ex);
        } 
        
        
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        Object what=ae.getSource();
        if (what==btn_Next)
        {btn_Next_Action();}
        if (what==btn_Prev)
        {btn_Prev_Action();}
        if (what==btn_First)
        {btn_First_Action();}
        if (what==btn_Last)
        {btn_Last_Action();}
    }

    public void btn_Next_Action() 
    {
         try
        {
            if (RES.next())
            {
                txt_ID.setText(RES.getString("ID"));
                txt_sensorID.setText(RES.getString("Sensor_ID"));
                txt_name.setText(RES.getString("Name"));
                txt_age.setText(RES.getString("Age"));  
            }
            else
            {
                RES.previous();
                JOptionPane.showMessageDialog(null,"This is the last record!");
            }
        }
        catch(Exception ex)
        {
            System.out.println(ex);
        } 

    }

    public void btn_Prev_Action() 
    {
         try
        {
            if (RES.previous())
            {
                txt_ID.setText(RES.getString("ID"));
                txt_sensorID.setText(RES.getString("Sensor_ID"));
                txt_name.setText(RES.getString("Name"));
                txt_age.setText(RES.getString("Age"));  
            }
            else
            {
                RES.next();
                JOptionPane.showMessageDialog(null,"This is the first record!");
            }
        }
        catch(Exception ex)
        {
            System.out.println(ex);
        } 
    }

    public void btn_First_Action() 
    {
        try
        {
            RES.first();
            txt_ID.setText(RES.getString("ID"));
            txt_sensorID.setText(RES.getString("Sensor_ID"));
            txt_name.setText(RES.getString("Name"));
            txt_age.setText(RES.getString("Age"));  
            
        }
        catch(Exception ex)
        {
            System.out.println(ex);
        } 
    }

    public void btn_Last_Action() 
    {
        try
        {
            RES.last();
            txt_ID.setText(RES.getString("ID"));
            txt_sensorID.setText(RES.getString("Sensor_ID"));
            txt_name.setText(RES.getString("Name"));
            txt_age.setText(RES.getString("Age"));  
            
        }
        catch(Exception ex)
        {
            System.out.println(ex);
        }
    }

    
    
    
    
    
    
    
}
