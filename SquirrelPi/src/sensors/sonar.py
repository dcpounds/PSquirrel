from src.sensors.sensor import Sensor

class Sonar(Sensor):
    """
    Class representing a sonar sensor.
    """
    
    def __init__(self, ID, pin):
        """
        Initialize the sensor
        
        ID - id number of sensor
        pin - pin sensor is attached to
        """
        super(Sonar, self).__init__("SONAR", ID, pin)
    
    def updateValue(self):
        """
        Update the internally stored value for the sensor.
        """
        self.value = -1