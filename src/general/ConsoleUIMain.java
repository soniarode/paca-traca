package general;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import simulator.*;

public class ConsoleUIMain {

	Scanner console;
	HashMap<String, Alert> alerts;
	HashMap<String, PacaTraca> pacaTracas;
	Settings settings;

	public ConsoleUIMain(HashMap<String, PacaTraca> pacaTracas,
			HashMap<String, Alert> alerts, Settings settings){

		console = new Scanner(System.in);
		this.settings = settings;
		this.pacaTracas = pacaTracas;
		this.alerts = alerts;
	}

	public void run(){

		// Print the available commands for the user's reference
		printConsoleCommands();

		// Main UI loop
		String userInput;
		boolean exit = false;
		System.out.println("paca-traca >>");
		while (!exit){

			boundaryAlerts(); // check if any alapcas are out of bounds

			// Get user input
			if (console.hasNext()){
				userInput = console.next();

				if (userInput.toLowerCase().equals("exit")){
					exit = true;
				}
				else if (userInput.toLowerCase().equals("set-boundaries")){
					// The user should have entered the points like
					// (1,2),(3,4),(5,6)...,(6,3)
					// Whitespace is ok.
					String listOfPoints = console.next();
					processBoundaryInputs(listOfPoints);
					//settings.setBoundaries();
				} 
				else if (userInput.toLowerCase().equals("show-boundaries")){
					float[] latitudeValues = settings.getLatArray();
					float[] longitudeValues = settings.getLonArray();
					System.out.print("Boundary points: ");
					for (int i=0; i<latitudeValues.length; i++){
						System.out.print("(" + latitudeValues[i] + ", " + longitudeValues[i] + ") ");
					}
					System.out.println();
					//System.out.println("Max latitude: "+settings.getLatMax());
					//System.out.println("Min latitude: "+settings.getLatMin());
					//System.out.println("Max longitude: "+settings.getLonMax());
					//set-System.out.println("Min longitude: "+settings.getLonMin());
				}
				else if (userInput.toLowerCase().equals("alpaca-location")){
					printAlpacaLocation(console.next());
				}
				else {
					System.out.println("Invalid command "+userInput);
				}
				System.out.println("paca-traca >>");
			}
		}
	}

	private void boundaryAlerts(){
		for (Alert alert: alerts.values()){
			alert.boundaryAlert();
		}
	}

	private void printAlpacaLocation(String ID){
		PacaTraca thePaca = pacaTracas.get(ID);
		if (thePaca == null){
			System.out.println("Error: Invalid sensor ID");
		}
		else {
			System.out.println("Latitude: "+thePaca.getLatitudeDecimalDegrees());
			System.out.println("Longitude: "+thePaca.getLongitudeDecimalDegrees());
		}
	}
	
	/*
	 * Parses the boundary points input entered by the user and sets
	 * these in Settings. Prints error message on bad input.
	 */
	private void processBoundaryInputs(String listOfPoints){
		String listOfPointsNoWhitespace = listOfPoints.replaceAll("\\s+", "");
		String cleanListOfPoints = listOfPointsNoWhitespace.substring(1, 
				listOfPointsNoWhitespace.length()-1);
		String points[] = cleanListOfPoints.split("\\),\\(");
		if (points.length < 3){
			System.out.println("Error: Must enter at least 3 points!");
		}
		else {
			float[] latValues = new float[points.length];
			float[] lonValues = new float[points.length];
			try {
				for (int i=0; i<points.length; i++){
					String xy[] = points[i].split(",");
					latValues[i] = Float.valueOf(xy[0]);
					lonValues[i] = Float.valueOf(xy[1]);
				}
				settings.setLatArray(latValues);
				settings.setLonArray(lonValues);
			} catch (RuntimeException e){
				System.out.println("Error: Bad input for boundary points.");
			}
		}
	}

	public static void printConsoleCommands(){
		List<String> commands = new ArrayList<String>();
		commands.add("set-boundaries (x1, y1),(x2, y2),...,(xn, yn)");
		commands.add("show-boundaries");
		commands.add("alpaca-location <sensor ID>");
		commands.add("exit");
		System.out.println("Valid commands:");
		for (String command: commands)
			System.out.println(command);
		System.out.println();
	}

}
