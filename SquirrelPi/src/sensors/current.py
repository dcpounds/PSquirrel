from src.sensors.sensor import Sensor

class Current(Sensor):
    
    def __init__(self, ID, pin):
        super("CURRENT", ID, pin)
    
    def updateValue(self):
        self.value = -1