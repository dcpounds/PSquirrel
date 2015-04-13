
class StepperMotor():
    """
    Class representing a stepper motor.
    """
    CW = 0
    CCW = 255
    
    def __init__(self, pin):
        """
        Initializes the motor
    
        pin - pin the motor is attached to
        """
        self.pin = pin
        
    def stop(self):
        """
        stops motor
        """
        
    def write(self, direction, speed):
        """
        writes a value to the motor to drive it
        
        direction - direction for the motor to turn int
        speed - speed for the motor to turn at
        """
        return