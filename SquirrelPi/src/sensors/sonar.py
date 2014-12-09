from src.sensors.sensor import Sensor

class Sonar(Sensor):
    
    def __init__(self, ID, pin):
        super(Sonar, self).__init__("SONAR", ID, pin)
    
    def updateValue(self):
        self.value = -1