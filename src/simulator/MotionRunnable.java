package simulator;

/**
 * 
 * @author Sonia Rode
 *
 * Generates latitude and longitude locations for an alpaca
 * using simulated movement.
 * 
 */
public class MotionRunnable implements Runnable {
	
	private volatile float currentLatitude;
	private volatile float currentLongitude;
	private volatile float previousLatitude;
	private volatile float previousLongitude;
	
	/**
	 * 
	 * @param startLatitude the starting latitude of the alpaca
	 * @param startLongitude the starting longitude of the alpaca
	 */
	public MotionRunnable(float startLatitude, float startLongitude){
		currentLatitude = startLatitude;
		currentLongitude = startLongitude;
	}
	
	public MotionRunnable(){
		// Set hard-coded initial position of alpaca
		currentLatitude = 45.000000000f;
		currentLongitude = 68.000000000f;
	}

	@Override
	public void run() {
		while (true) {
			// For now, the alpaca just moves in a straight line northwest
			previousLatitude = currentLatitude;
			previousLongitude = currentLongitude;
			currentLatitude += 0.0001;
			currentLongitude += 0.0001;
			
			// Sleep because the alpaca doesn't move as fast as the processor
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				break;
			}
		}
	}

	public float getCurrentLatitude() {
		return currentLatitude;
	}

	public float getCurrentLongitude() {
		return currentLongitude;
	}

	public float getPreviousLatitude() {
		return previousLatitude;
	}

	public float getPreviousLongitude() {
		return previousLongitude;
	}

}
