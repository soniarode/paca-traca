package db;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * 
 * @author Josh Kostura
 * 
 * JPanel with buttons for adding and deleting alpacas from the database.
 *
 */
public class DBPanel extends JPanel implements ActionListener{

	private static final long serialVersionUID = 1L;
	
	Connection CON;
	Connection Insert_CON;
	Statement Insert_STA;
	Statement STA;
	ResultSet RES;
	JButton btn_Insert = new JButton("Add New Alpaca");
	JButton btn_Delete = new JButton("Remove Current Alpaca");
	JFrame InsertWindow;
	JPanel insertPanel = new JPanel();
	
	//Insert Labels
	JLabel lbl_Alpaca_ID, lbl_Name, lbl_Gender, 
	 lbl_Num_Children, lbl_Weight, lbl_Height, 
	 lbl_Age, lbl_Mother, lbl_Father;
	
	//Insert Textfields
	JTextField txt_Alpaca_ID, txt_Name, txt_Gender, 
	 txt_Num_Children, txt_Weight, txt_Height, 
	 txt_Age, txt_Mother, txt_Father;

	public DBPanel(){
		// initialize the connection to MySQL database
		ConnectToDB();
		// create statement, execute SQL query against the DB and obtain
		// ResultSet
		SelectDataFromDB();
		add(btn_Insert);
		add(btn_Delete);
		btn_Insert.addActionListener(this);
		btn_Delete.addActionListener(this);
	}

	public Connection getDBconnection() {
		return CON;
	}

	private void ConnectToDB() {
		try {
			// Try loading the mysql driver
			Class.forName("com.mysql.jdbc.Driver");
			// Create connection to DB, username and password stored in plain
			// here
			// eventually we will make a config file
			CON = DriverManager.getConnection("jdbc:mysql://130.111.197.96/Paca-Traca", "remote",
					"pass");
			Insert_CON = DriverManager.getConnection("jdbc:mysql://130.111.197.96/Paca-Traca","remote","pass");
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

	private void SelectDataFromDB() {
		try {
			// create statement object, execute SQL on DB and getting the RS
			// object back
			STA = CON.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			String query = "SELECT * FROM Alpaca_Data";
			RES = STA.executeQuery(query);

		} catch (Exception ex) {
			System.out.println(ex);
		}
	}


	public void btn_Insert_Action(){
		int result = 0;
		int alpaca_ID = 0;
		String name = "";
		String gender ="";
		int children = 0;
		float weight = 0;
		float height = 0;
		int age = 0;
		String mother = "";
		String father = "";
		result = createInsertUI();
		if(result == JOptionPane.OK_OPTION){
			alpaca_ID = Integer.parseInt(txt_Alpaca_ID.getText());
			name = txt_Name.getText();
			gender = txt_Gender.getText();
			children = Integer.parseInt(txt_Num_Children.getText());
			weight = Float.parseFloat(txt_Weight.getText());
			height = Float.parseFloat(txt_Height.getText());
			age = Integer.parseInt(txt_Age.getText());
			mother = txt_Mother.getText();
			father = txt_Father.getText();
		}
		//Functionality I was working on to add a alpaca to the DB, communication with DB is working 

		try{
			PreparedStatement pstmt = Insert_CON.prepareStatement("INSERT INTO `Paca-Traca`.`Alpaca_Data` (`Alpaca_ID`, `Name`, `Gender`,  `Children`, `Weight`, `Height`, `Age`, `Mother`, `Father`)" +	
					"VALUES (?,?,?,?,?,?,?,?,?)");

			try {
				pstmt.setInt(1,alpaca_ID);
				pstmt.setString(2,name);
				pstmt.setString(3,gender);
				pstmt.setInt(4,children);
				pstmt.setFloat(5,weight);
				pstmt.setFloat(6,height);
				pstmt.setInt(7,age);
				pstmt.setString(8,mother);
				pstmt.setString(9,father);
				try{
					pstmt.executeUpdate();
				}catch (Exception e){
					System.out.println("First Catch");
					System.out.println(e);
				}

			}catch(Exception e){
				System.out.println("2 Catch");
				System.out.println(e);
			}
		}catch (Exception e)
		{
			System.out.println("3 Catch");
			System.out.println(e);
		}	
		//query the database again to get an updated list of alpaca's
		SelectDataFromDB();
	}

	public void btn_Delete_Action(){

		int delete_id = Integer.parseInt(JOptionPane.showInputDialog("Insert the ID number of the alpaca to delete?"));

		try {
			PreparedStatement pstmt = Insert_CON.prepareStatement("DELETE FROM `Paca-Traca`.`Alpaca_Data` WHERE `Alpaca_Data`.`Alpaca_ID` = ?;");
			try {
				pstmt.setInt(1,delete_id);
			}catch(SQLException e){
				System.out.println(e);
			}
			try {
				pstmt.executeUpdate();
			}catch (SQLException e){
				System.out.println(e);
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		SelectDataFromDB();

	}

	private int createInsertUI(){
		createUITextFieldsAdd();
		createUILabelsAdd();
		insertPanel.add(lbl_Alpaca_ID);
		insertPanel.add(txt_Alpaca_ID);
		insertPanel.add( lbl_Name);
		insertPanel.add( txt_Name);
		insertPanel.add(lbl_Gender);
		insertPanel.add(txt_Gender);
		insertPanel.add(lbl_Num_Children);
		insertPanel.add(txt_Num_Children);
		insertPanel.add(lbl_Weight);
		insertPanel.add(txt_Weight);
		insertPanel.add(lbl_Height );
		insertPanel.add(txt_Height );
		insertPanel.add(lbl_Age );
		insertPanel.add(txt_Age );
		insertPanel.add(lbl_Mother);
		insertPanel.add(txt_Mother);
		insertPanel.add(lbl_Father);
		insertPanel.add(txt_Father);

		int result = JOptionPane.showConfirmDialog(null,insertPanel,"Please enter fields for the new alpaca",
				JOptionPane.OK_CANCEL_OPTION);
		insertPanel.removeAll();
		return result;

	}

	private void createUILabelsAdd(){
		lbl_Alpaca_ID = new JLabel("Alpaca ID: ");
		lbl_Name = new JLabel("Name: ");
		lbl_Gender = new JLabel("Gender: ");
		lbl_Num_Children = new JLabel("Number of Children: ");
		lbl_Weight = new JLabel("Weight: ");
		lbl_Height = new JLabel("Height: ");
		lbl_Age = new JLabel("Age: ");
		lbl_Mother = new JLabel("Mother: ");
		lbl_Father = new JLabel("Father: ");

	}

	private void createUITextFieldsAdd(){
		txt_Alpaca_ID= new JTextField(10);
		txt_Name= new JTextField(10);
		txt_Gender= new JTextField(10);
		txt_Num_Children= new JTextField(10);
		txt_Weight= new JTextField(10);
		txt_Height= new JTextField(10);
		txt_Age= new JTextField(10);
		txt_Mother= new JTextField(10);
		txt_Father= new JTextField(10);

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Object what = arg0.getSource();
		if (what == btn_Insert){
			btn_Insert_Action();
		}
		if (what == btn_Delete){
			btn_Delete_Action();
		}
	}
}
