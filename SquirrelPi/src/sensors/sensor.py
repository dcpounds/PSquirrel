
class Sensor():
    
    def __init__(self, sensorType, ID, pin):
        self.sensorType = sensorType
        self.ID = ID
        self.value = -1
        self.pin = pin
        
    def updateValue(self):
        self.value = -1
    
    def toDict(self):
        return {"type": self.sensorType,
                "id": self.ID,
                "value": self.value}
        