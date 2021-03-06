from src.sensors.sensor import Sensor

class LimitSwitch(Sensor):
    """
    Class representing a limit switch.
    """
    
    def __init__(self, ID, pin):
        """
        Initialize the sensor
        
        ID - id number of sensor
        pin - pin sensor is attached to
        """
        super(LimitSwitch, self).__init__(pin)
    
    def readValue(self):
        """
        Update the internally stored value for the sensor.
        """
        self.value = -1