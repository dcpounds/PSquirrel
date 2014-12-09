from src.sensors.sensor import Sensor

class Potentiometer(Sensor):
    
    def __init__(self, ID, pin):
        super(Potentiometer, self).__init__("POTENTIOMETER", ID, pin)
    
    def updateValue(self):
        self.value = -1