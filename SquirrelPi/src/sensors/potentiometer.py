from src.sensors.sensor import Sensor

class Potentiometer(Sensor):
    
    def __init__(self, ID, pin):
        super("POTENTIOMETER", ID, pin)
    
    def updateValue(self):
        self.value = -1