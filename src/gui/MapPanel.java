package gui;

import general.Settings;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JPanel;

import simulator.PacaTraca;

/**
 * 
 * @author Zack Schiller
 * 
 *         Map panel for displaying the map of the alpacas. Updates with their
 *         current locations and other sensor data. Current alpaca is colored
 *         differently to show where it is.
 * 
 */
// Create a class Point which we will use to draw the alpacas on the map
class Point {
	float lat, lon;
	Boolean current;

	public Point(float lat, float lon, Boolean current) {
		this.lat = lat;
		this.lon = lon;
		this.current = current;
	}
}

// Create a class BoundaryLine for each of the boundary lines to be drawn
class BoundaryLine {
	float x1, x2, y1, y2;

	public BoundaryLine(float x1, float y1, float x2, float y2) {
		this.x1 = x1;
		this.x2 = x2;
		this.y1 = y1;
		this.y2 = y2;
	}

	// Actually calls the paint method to put the lines in the map
	public void paint(Graphics g) {
		g.drawLine((int) this.x1, (int) this.y1, (int) this.x2, (int) this.y2);
	}
}

/**
 * 
 * JPanel for displaying alpacas on map.
 * 
 */
public class MapPanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;

	// Change the size of the map here
	private static final int MAP_WIDTH = 400;
	private static final int MAP_HEIGHT = 400;

	private static float LATMAX, LATMIN, LONMAX, LONMIN, WIDTH, WIDTH_RATIO,
			HEIGHT, HEIGHT_RATIO;

	Map<String, Point> alpacaDots;
	List<String> sensorIDs;
	ArrayList<BoundaryLine> boundaries;

	// Alpaca Dot properties
	private float dotRadius = 4.0f; // Dot's radius

	public MapPanel(Settings settings, List<String> sensorIDs) {
		this.sensorIDs = sensorIDs;
		alpacaDots = new HashMap<String, Point>();
		for (String sensorID : sensorIDs) {
			alpacaDots.put(sensorID, new Point(LATMAX, LONMAX, false));
		}
		this.setPreferredSize(new Dimension(MAP_WIDTH, MAP_HEIGHT));
		LATMAX = 45.0008f;// settings.getLatMax();
		LONMAX = 68.0008f;// settings.getLonMax();
		LATMIN = 44.9992f;// settings.getLatMin();
		LONMIN = 67.9992f;// settings.getLonMin();
		WIDTH = (LONMAX - LONMIN) + 0.00001f; // Added bit is to see the bounds
		HEIGHT = (LATMAX - LATMIN) + 0.00001f; // Added bit is to see the bounds
		WIDTH_RATIO = (MAP_WIDTH / WIDTH);
		HEIGHT_RATIO = (MAP_HEIGHT / HEIGHT);

		// Create boundary lines
		boundaries = new ArrayList<BoundaryLine>();
		float[] bounds = { (HEIGHT_RATIO * (LATMAX - LATMIN)),
				(WIDTH_RATIO * (LONMAX - LONMIN)), (HEIGHT_RATIO * 0.00001f),
				(WIDTH_RATIO * 0.00001f) };
		this.boundaries.add(new BoundaryLine(bounds[0], bounds[1], bounds[0],
				bounds[3]));
		this.boundaries.add(new BoundaryLine(bounds[0], bounds[1], bounds[2],
				bounds[1]));
		this.boundaries.add(new BoundaryLine(bounds[2], bounds[1], bounds[2],
				bounds[3]));
		this.boundaries.add(new BoundaryLine(bounds[0], bounds[3], bounds[2],
				bounds[3]));
	}

	// Update the locations of the AlpacaDots
	public void update(Collection<PacaTraca> sensors, String ID) {
		for (PacaTraca sensor : sensors) {
			Point point = alpacaDots.get(sensor.getSensorID());
			point.lat = (sensor.getLatitudeDecimalDegrees() - LATMIN)
					* (HEIGHT_RATIO);
			point.lon = (sensor.getLongitudeDecimalDegrees() - LONMIN)
					* (WIDTH_RATIO);
			if (sensor.getSensorID() == ID) {
				point.current = true;
			} else {
				point.current = false;
			}
		}

		repaint();
	}

	/** Custom rendering codes for drawing the JPanel */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g); // Paint background

		// Draw the box
		g.setColor(Color.GRAY);
		g.fillRect(0, 0, MAP_WIDTH, MAP_HEIGHT);

		// Draw the dot
		for (Point point : alpacaDots.values()) {
			if (point.current == true) {
				g.setColor(Color.BLUE);
				dotRadius = 6.0f;
			} else {
				g.setColor(Color.WHITE);
				dotRadius = 5.0f;
			}

			g.fillOval((int) (point.lon - dotRadius),
					(int) (point.lat - dotRadius), (int) (2 * dotRadius),
					(int) (2 * dotRadius));

		}

		// Draw boundary lines
		for (BoundaryLine line : boundaries) {
			g.setColor(Color.RED);
			line.paint(g);
		}

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}

}
