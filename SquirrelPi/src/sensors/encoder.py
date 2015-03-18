from src.sensors.sensor import Sensor

class Encoder(Sensor):
    """
    Class representing an encoder.
    """
    
    def __init__(self, ID, pin):
        """
        Initialize the sensor
        
        ID - id number of sensor
        pin - pin sensor is attached to
        """
        super(Encoder, self).__init__("ENCODER", ID, pin)
    
    def takeReading(self):
        """
        Update the internally stored value for the sensor.
        """
        self.value = -1