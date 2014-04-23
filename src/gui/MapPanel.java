package gui;

import general.Settings;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JPanel;

import simulator.PacaTraca;

class Point{
	float lat, lon;
	public Point(float lat, float lon){
		this.lat = lat;
		this.lon = lon;
	}	
}

/**
 * 
 * JPanel for displaying alpacas on map.
 * 
 */
public class MapPanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;

	private static final int MAP_WIDTH = 600;
	private static final int MAP_HEIGHT = 600;

	private static float LATMAX, LATMIN, LONMAX, LONMIN, WIDTH, WIDTH_RATIO,
			HEIGHT, HEIGHT_RATIO;
	Map<String, Point> alpacaDots;

	List<String> sensorIDs;

	// Alpaca Dot properties
	private float dotRadius = 5.0f; // Dot's radius

	public MapPanel(Settings settings, List<String> sensorIDs) {
		this.sensorIDs = sensorIDs;
		alpacaDots = new HashMap<String, Point>();
		for (String sensorID : sensorIDs) {
			alpacaDots.put(sensorID, new Point(LATMAX, LONMAX));
		}
		this.setPreferredSize(new Dimension(MAP_WIDTH, MAP_HEIGHT));
		LATMAX = 45.0008f;// settings.getLatMax();
		LONMAX = 68.0008f;// settings.getLonMax();
		LATMIN = 44.9992f;// settings.getLatMin();
		LONMIN = 67.9992f;// settings.getLonMin();
		WIDTH = (LONMAX - LONMIN);
		HEIGHT = (LATMAX - LATMIN);
		WIDTH_RATIO = (MAP_WIDTH / WIDTH);
		HEIGHT_RATIO = (MAP_HEIGHT / HEIGHT);

	}

	public void update(Collection<PacaTraca> sensors) {
		for (PacaTraca sensor : sensors) {
			Point point = alpacaDots.get(sensor.getSensorID());
			point.lat = (sensor.getLatitudeDecimalDegrees() - LATMIN) * (HEIGHT_RATIO);
			point.lon = (sensor.getLongitudeDecimalDegrees() - LONMIN) * (WIDTH_RATIO);
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
			g.setColor(Color.WHITE);
			g.fillOval((int) (point.lon - dotRadius),
					(int) (point.lat - dotRadius),
					(int) (2 * dotRadius), (int) (2 * dotRadius));
			
		}

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}

}
