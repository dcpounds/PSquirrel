import RPi.GPIO as GPIO

class ClawMotorDriver():
    """
    Class representing a motor.
    """
    
    def __init__(self, pinMD, pinIn1, pinIn2, gpio_expander):
        """
        Initializes the motor
    
        pinMD - pin for determining the mode
        PinIn1 - pin for direction (EN)
        PinIn2 - pin for PWM (PH)
        """
        self.pinMD = pinMD
        self.pinIn1 = pinIn1
        self.pinIn2 = pinIn2
        self.gpio_expander = gpio_expander
        
        gpio_expander.config(self.pinMD, gpio_expander.OUTPUT)
        gpio_expander.output(self.pinMD, 1)
        GPIO.setup(self.pinIn1, GPIO.OUT)
        GPIO.setup(self.pinIn2, GPIO.OUT)  
        GPIO.output(self.pinIn2, 0)  
        self.stop()   
        
    def stop(self):
        """
        stops motor
        """
        GPIO.output(self.pinIn1, 0)
        
    def write(self):
        """
        drives the motor (only in 1 direction and speed for claws)
        """
        GPIO.output(self.pinIn1, 1)
            
        return