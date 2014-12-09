from src.sensors.sensor import Sensor

class LimitSwitch(Sensor):
    
    def __init__(self, ID, pin):
        super(LimitSwitch, self).__init__("LIMITSWITCH", ID, pin)
    
    def updateValue(self):
        self.value = -1