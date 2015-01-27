
class Motor():
    """
    Class representing a motor.
    """
    
    def __init__(self, pin):
        """
        Initializes the motor
    
        pin - pin the motor is attached to
        """
        self.pin = pin
        
    def write(self, direction, speed):
        """
        writes a value to the motor to drive it
        
        direction - direction for the motor to turn int
        speed - speed for the motor to turn at
        """
        return