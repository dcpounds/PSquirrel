from src.sensors.sensor import Sensor

class Potentiometer(Sensor):
    """
    Class representing a potentiometer.
    """
    
    def __init__(self, ID, pin):
        """
        Initialize the sensor
        
        ID - id number of sensor
        pin - pin sensor is attached to
        """
        super(Potentiometer, self).__init__("POTENTIOMETER", ID, pin)
    
    def takeReading(self):
        """
        Update the internally stored value for the sensor.
        """
        self.value = -1