from src.sensors.sensor import Sensor

class Gyro(Sensor):
    
    def __init__(self, ID, pin):
        super(Gyro, self).__init__("GYRO", ID, pin)
    
    def updateValue(self):
        self.value = -1