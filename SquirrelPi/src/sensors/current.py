from src.sensors.sensor import Sensor

class Current(Sensor):
    """
    Class representing a current sensor.
    """
    
    def __init__(self, ID, pin):
        """
        Initialize the sensor
        
        ID - id number of sensor
        pin - pin sensor is attached to
        """
        super(Current, self).__init__("CURRENT", ID, pin)
    
    def takeReading(self):
        """
        Update the internally stored value for the sensor.
        """
        self.value = -1