package general;

import java.io.*;

public class Settings {
	/**
	 * I/O code copied from www.caveofprogramming.com: http://goo.gl/LV2hYk
	 */
	public static void main(String[] args) {
		// The name of the file to open.
		String fileName = "boundaries.txt";

		// This will reference one line at a time
		String line = null;

		try {
			// FileReader reads text files in the default encoding.
			FileReader fileReader = new FileReader(fileName);

			// Always wrap FileReader in BufferedReader.
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			while ((line = bufferedReader.readLine()) != null) {
				System.out.println(line);
			}

			// Always close files.
			bufferedReader.close();
		} catch (FileNotFoundException ex) {
			System.out.println("Unable to open file '" + fileName + "'");
		} catch (IOException ex) {
			System.out.println("Error reading file '" + fileName + "'");
		}
	}

	/////
	// Boundaries in Latitude and longitude Maximum and Minimum
	static float latMax, latMin, lonMax, lonMin;

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
