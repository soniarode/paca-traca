package general;

import simulator.AlpacaDataSimulator;

public class Alert {
	
	private AlpacaDataSimulator alpaca;
	
	public Alert(AlpacaDataSimulator alpaca){
		this.alpaca = alpaca;
	}

	// If current Latitude or Longitude is outside of the boundary, print alert!
	public static String outsideBoundary(float latCurrent, float lonCurrent) {
		Settings.getBoundaries();
		if (latCurrent > Settings.getLatMax()) {
			return "Too far North!";
		} else if (latCurrent < Settings.getLatMin()) {
			return "Too far South!";
		} else if (lonCurrent > Settings.getLonMax()) {
			return "Too far East!";
		} else if (lonCurrent < Settings.getLonMin()) {
			return "Too far West!";
		} else {
			return "";
		}
	}


	public void boundaryAlert() {
		String response = outsideBoundary(alpaca.getLatitudeDecimalDegrees(), alpaca.getLongitudeDecimalDegrees());
		if (!response.equals("")) {
			System.out.println("Alpaca out of bounds! " + response);
		}
	}
}
