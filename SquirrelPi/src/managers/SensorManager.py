from src.sensors.encoder import Encoder
from src.sensors.potentiometer import Potentiometer
from src.sensors.current import Current
from src.sensors.gyro import Gyro
from src.sensors.ir import IR
from src.sensors.limitswitch import LimitSwitch
from src.sensors.sonar import Sonar

class SensorManager():
    def __init__(self):
        self.sensors = []
        self.sensors.add(Encoder(1, 0))
        self.sensors.add(Potentiometer(1, 0))
        self.sensors.add(Potentiometer(2, 0))
        self.sensors.add(Current(1, 0))
        self.sensors.add(Current(2, 0))
        self.sensors.add(Current(3, 0))
        self.sensors.add(Current(4, 0))
        self.sensors.add(Current(5, 0))
        self.sensors.add(Gyro(1, 0))
        self.sensors.add(IR(1, 0))
        self.sensors.add(IR(2, 0))
        self.sensors.add(LimitSwitch(1, 0))
        self.sensors.add(LimitSwitch(2, 0))
        self.sensors.add(LimitSwitch(3, 0))
        self.sensors.add(LimitSwitch(4, 0))
        self.sensors.add(LimitSwitch(5, 0))
        self.sensors.add(LimitSwitch(6, 0))
        self.sensors.add(LimitSwitch(7, 0))
        self.sensors.add(LimitSwitch(8, 0))
        self.sensors.add(LimitSwitch(9, 0))
        self.sensors.add(LimitSwitch(10, 0))
        self.sensors.add(Sonar(1, 0))
        self.sensors.add(Sonar(2, 0))
        
    def updateSensorData(self):
        for sensor in self.sensors:
            sensor.updateValue()
        return {"sensorData":self.sensors}
    
    def getSensor(self, sensorType, sensorID):
        for sensor in self.sensors:
            if sensor.sensorType == sensorType and sensor.ID == sensorID:
                return sensor