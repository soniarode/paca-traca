package general;

public interface Settings {


	
	public void getBoundaries();
	public void setBoundaries();
	public float getLatMax();
	
	
	public float[] getLatArray();

	public float[] getLonArray();
	
	public int getNumCorners();
	
	public void setLatArray(float[] latArray);
	
	public void setLonArray(float[] lonArray);

	public void setLatMax(float latMax);

	public float getLatMin();

	public void setLatMin(float latMin);

	public float getLonMax();

	public void setLonMax(float lonMax);

	public float getLonMin();

	public void setLonMin(float lonMin);
}
