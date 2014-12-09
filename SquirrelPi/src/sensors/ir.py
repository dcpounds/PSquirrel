from src.sensors.sensor import Sensor

class IR(Sensor):
    
    def __init__(self, ID, pin):
        super(IR, self).__init__("IR", ID, pin)
    
    def updateValue(self):
        self.value = -1