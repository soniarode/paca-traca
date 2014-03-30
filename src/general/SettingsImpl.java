package general;

import java.io.*;

public class SettingsImpl implements Settings{

	// Boundaries in Latitude and longitude Maximum and Minimum
	private float latMax, latMin, lonMax, lonMin;

	public SettingsImpl(){

	}

	// Get the boundaries from the boundaries.txt file
	public void getBoundaries() {
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
	public void setBoundaries(){
		try {
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
			System.out.println("Error writing to file");
		}
	}

	public float getLatMax() {
		return latMax;
	}

	public void setLatMax(float latMax) {
		this.latMax = latMax;
	}

	public float getLatMin() {
		return latMin;
	}

	public void setLatMin(float latMin) {
		this.latMin = latMin;
	}

	public float getLonMax() {
		return lonMax;
	}

	public void setLonMax(float lonMax) {
		this.lonMax = lonMax;
	}

	public float getLonMin() {
		return lonMin;
	}

	public void setLonMin(float lonMin) {
		this.lonMin = lonMin;
	}
}
