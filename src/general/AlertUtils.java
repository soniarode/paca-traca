package general;

/**
 * 
 * @author Sonia Rode
 *
 * Utilities pertaining to the Alert class.
 * 
 */
public class AlertUtils {
	
	/**
	 * Parses the alert boundary points entered by the user and sets
	 * these in Settings. Prints error message on bad input.
	 * 
	 * @param listOfPoints - the user's input
	 * @pararm settings - the system's Settings object
	 */
	public static void processBoundaryInputs(String listOfPoints, Settings settings){
		String listOfPointsNoWhitespace = listOfPoints.replaceAll("\\s+", "");
		String cleanListOfPoints = listOfPointsNoWhitespace.substring(1, 
				listOfPointsNoWhitespace.length()-1);
		String points[] = cleanListOfPoints.split("\\)\\(");
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
	
	/**
	 * Helper method to determine if an Alert is really an Alert, or
	 * just saying the alpaca is ok/fine/normal... etc.
	 */
	public static boolean isRealAlert(String possibleAlert){
		if (possibleAlert.toLowerCase().contains("normal") || 
				possibleAlert.toLowerCase().contains("ok") ||
				possibleAlert.toLowerCase().contains("fine"))
			return false;
		return true;
	}
	
	/**
	 * Returns all the current alerts as a String.
	 * 
	 * @param alerts - the alert objects for all alpacas
	 * @return String of the current alerts
	 */
	public static String stringOfAllAlerts(Iterable<Alert> alerts){
		StringBuilder alertString = new StringBuilder();
		String curAlert;
		for (Alert alert: alerts){
			curAlert = alert.boundaryAlert();
			if (isRealAlert(curAlert))
				alertString.append(curAlert + "\n");
			curAlert = alert.temperatureAlert();
			if (isRealAlert(curAlert))
				alertString.append(curAlert + "\n");
		}
		return alertString.toString();
	}

}
