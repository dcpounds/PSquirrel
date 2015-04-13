from src.sensors.sensor import Sensor

class Battery(Sensor):
    """
    Class representing an encoder.
    """
    
    def __init__(self, ID, pin):
        """
        Initialize the sensor
        
        ID - id number of sensor
        pin - pin sensor is attached to
        """
        super(Battery, self).__init__("ENCODER", ID, pin)
    
    def readValue(self):
        """
        Update the internally stored value for the sensor.
        """
        self.value = -1