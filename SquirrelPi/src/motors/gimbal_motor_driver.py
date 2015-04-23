import RPi.GPIO as GPIO

class GimbalMotorDriver():
    """
    Class representing a motor.
    """
    
    def __init__(self, pinSLP, pinEN, pinPH, gpio_expander):
        """
        Initializes the motor
    
        pinSLP - pin for disabling brake (pin always set high)
        PinIn1 - pin for direction (EN)
        PinIn2 - pin for PWM (PH)
        """
        self.pinSLP = pinSLP
        self.pinIn1 = pinEN
        self.pinIn2 = pinPH
        self.gpio_expander = gpio_expander
        
        gpio_expander.config(self.pinSLP, gpio_expander.OUTPUT)
        gpio_expander.output(self.pinSLP, 1)
        GPIO.setup(self.pinIn1, GPIO.OUT)
        GPIO.setup(self.pinIn2, GPIO.OUT)  
        self.pwm = GPIO.PWM(pinEN, .000005)    
        self.stop()  
        
    def stop(self):
        """
        stops motor
        """
        self.pwm.stop()
        
    def write(self, direction, duty=100):
        """
        writes a value to the motor to drive it
        
        direction - direction for the motor to turn int
        speed - speed for the motor to turn at
        """
        
        
        if direction == "CW":
            GPIO.output(self.pinIn2, 1)
        else:
            GPIO.output(self.pinIn2, 0)
        self.pwm.start(duty)
            
        return