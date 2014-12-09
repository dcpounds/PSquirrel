from src.sensors.sensor import Sensor

class Current(Sensor):
    
    def __init__(self, ID, pin):
        super(Current, self).__init__("CURRENT", ID, pin)
    
    def updateValue(self):
        self.value = -1