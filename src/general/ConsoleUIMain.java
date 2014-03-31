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
					settings.setBoundaries();
				} 
				else if (userInput.toLowerCase().equals("show-boundaries")){
					System.out.println("Max latitude: "+settings.getLatMax());
					System.out.println("Min latitude: "+settings.getLatMin());
					System.out.println("Max longitude: "+settings.getLonMax());
					System.out.println("Min longitude: "+settings.getLonMin());
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

	public static void printConsoleCommands(){
		List<String> commands = new ArrayList<String>();
		commands.add("set-boundaries");
		commands.add("show-boundaries");
		commands.add("alpaca-location <sensor ID>");
		commands.add("exit");
		System.out.println("Valid commands:");
		for (String command: commands)
			System.out.println(command);
		System.out.println();
	}

}
