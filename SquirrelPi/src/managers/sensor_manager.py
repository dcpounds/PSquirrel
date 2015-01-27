from src.sensors.encoder import Encoder
from src.sensors.potentiometer import Potentiometer
from src.sensors.current import Current
from src.sensors.gyro import Gyro
from src.sensors.ir import IR
from src.sensors.limit_switch import LimitSwitch
from src.sensors.sonar import Sonar

class SensorManager():
    """
    Class for managing reading sensor data
    """
    
    def __init__(self):
        """
        Initializes the sensor manager with the various list of sensors.
        """
        self.sensors = []
        self.sensors.append(Encoder(1, 0))
        self.sensors.append(Potentiometer(1, 0))
        self.sensors.append(Potentiometer(2, 0))
        self.sensors.append(Current(1, 0))
        self.sensors.append(Current(2, 0))
        self.sensors.append(Current(3, 0))
        self.sensors.append(Current(4, 0))
        self.sensors.append(Current(5, 0))
        self.sensors.append(Gyro(1, 0))
        self.sensors.append(IR(1, 0))
        self.sensors.append(IR(2, 0))
        self.sensors.append(LimitSwitch(1, 0))
        self.sensors.append(LimitSwitch(2, 0))
        self.sensors.append(LimitSwitch(3, 0))
        self.sensors.append(LimitSwitch(4, 0))
        self.sensors.append(LimitSwitch(5, 0))
        self.sensors.append(LimitSwitch(6, 0))
        self.sensors.append(LimitSwitch(7, 0))
        self.sensors.append(LimitSwitch(8, 0))
        self.sensors.append(LimitSwitch(9, 0))
        self.sensors.append(LimitSwitch(10, 0))
        self.sensors.append(Sonar(1, 0))
        self.sensors.append(Sonar(2, 0))
        
    def updateSensorData(self):
        """
        reads all the sensors and updates internal data
        """
        for sensor in self.sensors:
            sensor.updateValue()
        return {"sensorData":self.sensors}
    
    def getSensor(self, sensorType, sensorID):
        """
        returns the currently stored data for the given sensor
        
        sensorType - type of the sensor
        sensorID - id number of the sensor
        """
        for sensor in self.sensors:
            if sensor.sensorType == sensorType and sensor.ID == sensorID:
                return sensor