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
        self.pinEN = pinEN
        self.pinPH = pinPH
        self.gpio_expander = gpio_expander
        
        gpio_expander.config(self.pinSLP, gpio_expander.OUTPUT)
        gpio_expander.output(self.pinSLP, 1)
        GPIO.setup(self.pinEN, GPIO.OUT)
        GPIO.setup(self.pinPH, GPIO.OUT)  
        self.pwm = GPIO.PWM(self.pinEN, .000005)    
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
            GPIO.output(self.pinPH, 1)
        else:
            GPIO.output(self.pinPH, 0)
        self.pwm.start(duty)
            
        return