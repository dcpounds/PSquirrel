from src.sensors.sensor import Sensor

class Sonar(Sensor):
    
    def __init__(self, ID, pin):
        super("SONAR", ID, pin)
    
    def updateValue(self):
        self.value = -1