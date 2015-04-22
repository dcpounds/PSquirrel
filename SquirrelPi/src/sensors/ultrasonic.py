from src.sensors.sensor import Sensor

class Ultrasonic(Sensor):
    """
    Class representing a sonar sensor.
    """
    
    def __init__(self, ID, pin):
        """
        Initialize the sensor
        
        ID - id number of sensor
        pin - pin sensor is attached to
        """
        super(Ultrasonic, self).__init__(pin)
    
    def readValue(self):
        """
        Update the internally stored value for the sensor.
        """
        self.value = -1