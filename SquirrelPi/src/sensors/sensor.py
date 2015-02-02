
class Sensor(object):
    """
    Class representing a generic sensor.
    """
    
    def __init__(self, sensorType, ID, pin):
        """
        Initialize the sensor
        
        sensorType - type of sensor
        ID - id number of sensor
        pin - pin sensor is attached to
        """
        self.sensorType = sensorType
        self.ID = ID
        self.value = -1
        self.pin = pin
        
    def updateValue(self):
        """
        Update the internally stored value for the sensor.
        """
        self.value = -1
        
    def readValue(self):
        return self.value
    
    def toDict(self):
        """
        Convert the sensor to a dictionary.
        """
        return {"type": self.sensorType,
                "id": self.ID,
                "value": self.value}
        