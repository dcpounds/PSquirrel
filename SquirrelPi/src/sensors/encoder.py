from src.sensors.sensor import Sensor

class Encoder(Sensor):
    
    def __init__(self, ID, pin):
        super(Encoder, self).__init__("ENCODER", ID, pin)
    
    def updateValue(self):
        self.value = -1