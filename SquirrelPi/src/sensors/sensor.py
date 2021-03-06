
class Sensor(object):
    """
    Class representing a generic sensor.
    """
    
    def __init__(self, pin):
        """
        Initialize the sensor
        
        sensorType - type of sensor
        ID - id number of sensor
        pin - pin sensor is attached to
        """
        self.value = -1
        self.pin = pin
        
    def readValue(self):
        """
        Read the sensor value
        """
    
    def toDict(self):
        """
        Convert the sensor to a dictionary.
        """
        return {"type": self.sensorType,
                "id": self.ID,
                "value": self.value}
        