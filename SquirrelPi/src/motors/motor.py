
class Motor():
    def __init__(self, pin):
        self.pin = pin
        
    def write(self, direction, speed):
        return