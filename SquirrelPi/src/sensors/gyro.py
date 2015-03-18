from src.sensors.sensor import Sensor

class Gyro(Sensor):
    """
    Class representing a gyro.
    """
    
    def __init__(self, ID, pin):
        """
        Initialize the sensor
        
        ID - id number of sensor
        pin - pin sensor is attached to
        """
        super(Gyro, self).__init__("GYRO", ID, pin)
    
    def takeReading(self):
        """
        Update the internally stored value for the sensor.
        """
        self.value = -1