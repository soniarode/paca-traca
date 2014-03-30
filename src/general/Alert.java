package general;

import simulator.AlpacaDataSimulator;

public class Alert {

	private AlpacaDataSimulator alpaca;
	private Settings settings;

	public Alert(AlpacaDataSimulator alpaca, Settings settings) {
		this.alpaca = alpaca;
		this.settings = settings;
	}

	// If current Latitude or Longitude is outside of the boundary, print alert!
	public String outsideBoundary(float latCurrent, float lonCurrent) {
		settings.getBoundaries();
		if (latCurrent > settings.getLatMax()) {
			return "Too far North!";
		} else if (latCurrent < settings.getLatMin()) {
			return "Too far South!";
		} else if (lonCurrent > settings.getLonMax()) {
			return "Too far East!";
		} else if (lonCurrent < settings.getLonMin()) {
			return "Too far West!";
		} else {
			return "";
		}
	}

	public void boundaryAlert() {
		String response = outsideBoundary(alpaca.getLatitudeDecimalDegrees(),
				alpaca.getLongitudeDecimalDegrees());
		if (!response.equals("")) {
			System.out.println("Alpaca out of bounds! " + response);
		}
	}
}
