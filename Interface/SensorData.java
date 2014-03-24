package com.pacatraca;

public interface  SensorData {
	public  Boolean setSensorID( Integer sensorID ); // pass in sensor ID
	public  Boolean isConnected( ); // true if connected to sensor
	public  Boolean ConnectToSensor( Integer sensorID);
}
