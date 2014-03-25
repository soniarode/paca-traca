package general;

import java.io.*;

public class Settings {

	// Boundaries in Latitude and longitude Maximum and Minimum
	static float latMax, latMin, lonMax, lonMin;

	// Get the boundaries from the boundaries.txt file
	public static void getBoundaries() {
		// The name of the file that the boundaries are kept in
		String fileName = "boundaries.txt";

		String line = null;
		try {
			FileReader fileReader = new FileReader(fileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			// Read in boundaries and set them
			while ((line = bufferedReader.readLine()) != null) {
				String[] data = line.split("[:]");
				if (data[0].equals("latMax")) {
					setLatMax(Float.valueOf(data[1]));
				} else if (data[0].equals("latMin")) {
					setLatMin(Float.valueOf(data[1]));
				} else if (data[0].equals("lonMax")) {
					setLonMax(Float.valueOf(data[1]));
				} else if (data[0].equals("lonMin")) {
					setLonMin(Float.valueOf(data[1]));
				}
			}
			bufferedReader.close();
		} catch (FileNotFoundException ex) {
			System.out.println("Unable to open file '" + fileName + "'");
		} catch (IOException ex) {
			System.out.println("Error reading file '" + fileName + "'");
		}
	}

	// Set the boundaries from user input and save to file
	public static void setBoundaries() throws IOException {
		// Get input from user for max and min lat and lon
		BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(System.in));
		System.out.println("Please enter the max latitude: ");
		String s = bufferedReader.readLine();
		setLatMax(Float.valueOf(s));
		System.out.println("Please enter the min latitude: ");
		s = bufferedReader.readLine();
		setLatMin(Float.valueOf(s));
		System.out.println("Please enter the max longitude: ");
		s = bufferedReader.readLine();
		setLonMax(Float.valueOf(s));
		System.out.println("Please enter the min longitude: ");
		s = bufferedReader.readLine();
		setLonMin(Float.valueOf(s));

		// Store the max and min lat and lon into boundaries.txt
		String fileName = "boundaries.txt";
		try {
			FileWriter fileWriter = new FileWriter(fileName);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			bufferedWriter.write("latMax:" + getLatMax());
			bufferedWriter.newLine();
			bufferedWriter.write("latMin:" + getLatMin());
			bufferedWriter.newLine();
			bufferedWriter.write("lonMax:" + getLonMax());
			bufferedWriter.newLine();
			bufferedWriter.write("lonMin:" + getLonMin());
			bufferedWriter.close();
		} catch (IOException ex) {
			System.out.println("Error writing to file '" + fileName + "'");
		}
	}

	public static float getLatMax() {
		return latMax;
	}

	public static void setLatMax(float latMax) {
		Settings.latMax = latMax;
	}

	public static float getLatMin() {
		return latMin;
	}

	public static void setLatMin(float latMin) {
		Settings.latMin = latMin;
	}

	public static float getLonMax() {
		return lonMax;
	}

	public static void setLonMax(float lonMax) {
		Settings.lonMax = lonMax;
	}

	public static float getLonMin() {
		return lonMin;
	}

	public static void setLonMin(float lonMin) {
		Settings.lonMin = lonMin;
	}
}
