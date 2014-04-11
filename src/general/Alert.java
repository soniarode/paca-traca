package general;

import simulator.PacaTraca;

public class Alert {

	private PacaTraca alpaca;
	private Settings settings;

	public Alert(PacaTraca alpaca, Settings settings) {
		this.alpaca = alpaca;
		this.settings = settings;
	}

	public Boolean OutsideBoundary(float latCurrent, float lonCurrent) {
		float latitude_Values[] = settings.getLatArray();
		float longitude_Values[] = settings.getLonArray();
		int num_Corners = settings.getNumCorners();
		/*
		 * 
		 * The algorithm was found at http://alienryderflex.com/polygon/
		 * 
		 * Works by checking how many vertical/latitudinal lines to how many
		 * lines are to the left and right of the point in question. If the
		 * number is odd then the point is within the bounds of the polygon
		 * derived from the points provided by the farmer.
		 */
		int i; // Loop variable
		int j = num_Corners - 1; // second loop variable
		boolean Inside = false;

		for (i = 0; i < num_Corners; i++) {
			if (latitude_Values[i] < latCurrent
					&& latitude_Values[j] >= latCurrent
					|| latitude_Values[j] < latCurrent
					&& latitude_Values[i] >= latCurrent) {
				if (longitude_Values[i] + (latCurrent - latitude_Values[i])
						/ (latitude_Values[j] - latitude_Values[i])
						* (longitude_Values[j] - longitude_Values[i]) < lonCurrent) {
					Inside = !Inside;
				}
			}
			j = i;
		}

		// returns True, if the point is inside the polygons created by the
		// corner points of the "farm". Returns

		return Inside;
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
		/*
		 * Leaving the old code to make sure the new stuff works first String
		 * response = outsideBoundary(alpaca.getLatitudeDecimalDegrees(),
		 * alpaca.getLongitudeDecimalDegrees()); if (!response.equals("")) {
		 * System.out.println("Alpaca out of bounds! " + response);
		 */
		if (!OutsideBoundary(alpaca.getLatitudeDecimalDegrees(),
				alpaca.getLongitudeDecimalDegrees())) {
			System.out.println("Alpaca " + alpaca.getSensorID()
					+ " out of bounds!");
		} else
			System.out.println("Alpaca " + alpaca.getSensorID()
					+ " is still Ok!");
	}

	public String temperatureAlert() {
		/*
		 * Gets the current temperature of the alpaca and returns a string with
		 * an alert of whether the alpaca is too hot, too cold or just right.
		 */
		String s;
		if (alpaca.getTemperature() > 102.5f) {
			s = ("Alpaca " + alpaca.getSensorID() + " temperature is too hot!");
		} else if (alpaca.getTemperature() < 100.5f) {
			s = ("Alpaca " + alpaca.getSensorID() + " temperature is too cold!");
		} else {
			s = ("Alpaca " + alpaca.getSensorID() + " temperature is normal!");
		}
		return s;
	}

}
