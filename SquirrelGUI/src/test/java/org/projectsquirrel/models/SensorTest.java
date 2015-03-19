package org.projectsquirrel.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests for {@link Sensor}.
 * @author Alex Stylos
 *
 */
public class SensorTest {
	
	private SensorType sensorPotentiometer;
	private SensorType sensorEncoder;
	private SensorType sensorSonar;
	private SensorType sensorGyro;
	private SensorType sensorLimitSwitch;
	private SensorType sensorCurrent;
	private SensorType sensorIr;
	private Sensor sensor;

	@Before
	public void setup(){
		sensorPotentiometer = SensorType.POTENTIOMETER;
		sensorEncoder = SensorType.ENCODER;
		sensorSonar = SensorType.ULTRASONIC;
		sensorGyro = SensorType.GYRO;
		sensorLimitSwitch = SensorType.LIMITSWITCH;
		sensorCurrent = SensorType.CURRENTSENSOR;
		sensorIr = SensorType.IR;
		sensor = new Sensor(sensorPotentiometer, 1, 1000);
	}
	
	@Test
	public void testGetValueReturnsProperValue(){
		float testValue = sensor.getValue();
		assertEquals(testValue, 1000);
	}
	
	@Test
	public void testGetSensorIdReturnsProperId(){
		int testSensorId = sensor.getSensorId();
		assertEquals(testSensorId, 1);
	}
	
	@Test
	public void testGetSensorTypeReturnsProperType(){
		SensorType testSensorType = sensor.getSensorType();
		assertEquals(testSensorType, sensorPotentiometer);
	}
	
	@Test
	public void testFromJsonReturnsProperObject(){
		Sensor testSensor = Sensor.fromJson("{\"sensorType\":\"ENCODER\",\"sensorId\":0,\"value\":100}");
		assertTrue((testSensor.getSensorId() == 0) && (testSensor.getSensorType() == sensorEncoder) &&
					(testSensor.getValue() == 100));
	}
}
