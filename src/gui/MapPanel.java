package gui;

import general.Settings;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JPanel;

/**
 * 
 * JPanel for displaying alpacas on map.
 * 
 */
public class MapPanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;

	private static final int MAP_WIDTH = 600;
	private static final int MAP_HEIGHT = 600;

	private static float LATMAX, LATMIN, LONMAX, LONMIN, WIDTH,
			WIDTH_RATIO, HEIGHT, HEIGHT_RATIO;

	List<String> sensorIDs;
	int currentSensorIndex;

	// Alpaca Dot properties
	private float dotRadius = 10; // Dot's radius
	private float dotX = dotRadius + 580; // Dot's center (x, y)
	private float dotY = dotRadius + 580;

	public MapPanel(Settings settings, List<String> sensorIDs) {
		this.sensorIDs = sensorIDs;
		this.setPreferredSize(new Dimension(MAP_WIDTH, MAP_HEIGHT));
		LATMAX = 46.0f;// settings.getLatMax();
		LONMAX = 69.0f;// settings.getLonMax();
		LATMIN = 44.0f;// settings.getLatMin();
		LONMIN = 67.0f;// settings.getLonMin();
		WIDTH = (LONMAX - LONMIN);
		HEIGHT = (LATMAX - LATMIN);
		System.out.println(sensorIDs.get(0));
		WIDTH_RATIO = (MAP_WIDTH / WIDTH);
		HEIGHT_RATIO = (MAP_HEIGHT / HEIGHT);

	}

	public void update(String id, float lat, float lon) {
		dotX = (int) ((lon - LONMIN)*(WIDTH_RATIO));
		dotY = (int) ((lat - LATMIN)*(HEIGHT_RATIO));
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
		g.setColor(Color.WHITE);
		g.fillOval((int) (dotX - dotRadius), (int) (dotY - dotRadius),
				(int) (2 * dotRadius), (int) (2 * dotRadius));

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}

}
