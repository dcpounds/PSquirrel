from src.sensors.sensor import Sensor

class StrainGauge(Sensor):
    """
    Class representing a current sensor.
    """
    
    def __init__(self, ID, pin, spi):
        """
        Initialize the sensor
        
        ID - id number of sensor
        pin - pin sensor is attached to
        """
        super(StrainGauge, self).__init__("StrainGauge", ID, pin)
        self.spi = spi
    
    def readValue(self):
        """
        Update the internally stored value for the sensor.
        """
        self.value = -1