package simulator;

public class MotionRunnable implements Runnable {
	
	private volatile float currentLatitude;
	private volatile float currentLongitude;
	private volatile float previousLatitude;
	private volatile float previousLongitude;
	
	public MotionRunnable(){
		// Set initial position of alpaca
		currentLatitude = 45.0f;
		currentLongitude = 68.0f;
	}

	@Override
	public void run() {
		while (true) {
			// For now, the alpaca just moves in a straight line northwest
			previousLatitude = currentLatitude;
			previousLongitude = currentLongitude;
			currentLatitude += 0.001;
			currentLongitude += 0.001;
			
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
