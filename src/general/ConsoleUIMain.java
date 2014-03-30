package general;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import simulator.*;

public class ConsoleUIMain {

	public static void main(String[] args) {
		
		Scanner console = new Scanner(System.in);
		
		// Create a single alpaca and an Alert for it
		AlpacaDataSimulator alpacaSim = AlpacaDataSimulator.createInstance();
		Settings settings = new SettingsImpl();
		settings.getBoundaries(); // read boundaries from file
		final Alert alert = new Alert(alpacaSim, settings);
		
		// Print the available commands for the user's reference
		printConsoleCommands();
		
		// Main UI loop
		String userInput;
		boolean exit = false;
		System.out.println("paca-traca >>");
		while (!exit){

			alert.boundaryAlert(); // check if the alpaca is out of bounds

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
					System.out.println("Latitude: "+alpacaSim.getLatitudeDecimalDegrees());
					System.out.println("Longitude: "+alpacaSim.getLongitudeDecimalDegrees());
				}
				else {
					System.out.println("Invalid command "+userInput);
				}
				System.out.println("paca-traca >>");
			}
		}

	}
	
	public static void printConsoleCommands(){
		List<String> commands = new ArrayList<String>();
		commands.add("set-boundaries");
		commands.add("show-boundaries");
		commands.add("alpaca-location");
		commands.add("exit");
		System.out.println("Valid commands:");
		for (String command: commands)
			System.out.println(command);
		System.out.println();
	}

}
