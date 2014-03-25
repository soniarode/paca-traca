package general;

import simulator.AlpacaDataSimulator;

public class Alert {
	
	private AlpacaDataSimulator alpaca;
	
	public Alert(AlpacaDataSimulator alpaca){
		this.alpaca = alpaca;
	}

	// If current Latitude or Longitude is outside of the boundary, return True.
	// Need to update so that it displays which direction is out of bounds.
	public static Boolean outsideBoundary(float latCurrent, float lonCurrent) {
		if (latCurrent > Settings.getLatMax()
				|| latCurrent < Settings.getLatMin()
				|| lonCurrent > Settings.getLonMax()
				|| lonCurrent < Settings.getLonMin()) {
			return true;
		} else {
			return false;
		}
	}

	public void boundaryAlert() {
		if (outsideBoundary(alpaca.getLatitudeDecimalDegrees(), alpaca.getLongitudeDecimalDegrees())) {
			System.out.println("Alpaca out of bounds!");
		}
	}

}
