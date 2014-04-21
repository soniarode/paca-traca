package general;

/**
 * 
 * @author Sonia Rode
 * 
 * A Profile sets the upper and lower bounds for normal sensor values for an 
 * alpaca. When sensor values are outside the range of an alpaca's Profile,
 * an alert is created.
 *
 */
public class Profile {
	
	private float lowerTempBound, upperTempBound;
	private float lowerPitchBound, upperPitchBound;
	private float lowerRollBound, upperRollBound;
	private float lowerAltitudeBound, upperAltitudeBound;
	private String name;
	
	/**
	 * Create a new Profile. The initial bounds values are set to default 
	 * values.
	 * 
	 * @param profileName the name of the profile
	 */
	public Profile(String profileName){
		this.name = profileName;
		lowerTempBound = 99.5f;
		upperTempBound = 102.1f;
		lowerRollBound = 0;
		upperRollBound = 360;
		lowerPitchBound = 0;
		upperPitchBound = 360;
		lowerAltitudeBound = 0;
		upperAltitudeBound = 5000;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getLowerTempBound() {
		return lowerTempBound;
	}
	
	public float getUpperTempBound() {
		return upperTempBound;
	}
	
	public float getLowerPitchBound() {
		return lowerPitchBound;
	}
	
	public float getUpperPitchBound() {
		return upperPitchBound;
	}
	
	public float getLowerRollBound() {
		return lowerRollBound;
	}
	
	public float getUpperRollBound() {
		return upperRollBound;
	}
	
	public float getLowerAltitudeBound() {
		return lowerAltitudeBound;
	}
	
	public float getUpperAltitudeBound() {
		return upperAltitudeBound;
	}
	
	public void setLowerTempBound(float lowerTempBound) {
		this.lowerTempBound = lowerTempBound;
	}
	
	public void setUpperTempBound(float upperTempBound) {
		this.upperTempBound = upperTempBound;
	}
	
	public void setLowerPitchBound(float lowerPitchBound) {
		this.lowerPitchBound = lowerPitchBound;
	}
	
	public void setUpperPitchBound(float upperPitchBound) {
		this.upperPitchBound = upperPitchBound;
	}
	
	public void setLowerRollBound(float lowerRollBound) {
		this.lowerRollBound = lowerRollBound;
	}
	
	public void setUpperRollBound(float upperRollBound) {
		this.upperRollBound = upperRollBound;
	}
	
	public void setLowerAltitudeBound(float lowerAltitudeBound) {
		this.lowerAltitudeBound = lowerAltitudeBound;
	}
	
	public void setUpperAltitudeBound(float upperAltitudeBound) {
		this.upperAltitudeBound = upperAltitudeBound;
	}
	
	

}
