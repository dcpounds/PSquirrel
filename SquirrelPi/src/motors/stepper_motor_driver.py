
import RPi.GPIO as GPIO
import time

class StepperMotorDriver():
    """
    Class representing a stepper motor.
    """
    CW = 0
    CCW = 255
    
    def __init__(self, pinMD, pinIn1A, pinIn2A, pinIn1B, pinIn2B, gpio_expander):
        """
        Initializes the motor
    
        pin - pin the motor is attached to
        """
        self.pinMD = pinMD
        self.pinIn1A = pinIn1A
        self.pinIn2A = pinIn2A
        self.pinIn1B = pinIn1B
        self.pinIn2B = pinIn2B
        gpio_expander.config(self.pinMD, gpio_expander.OUTPUT)
        gpio_expander.output(self.pinMD, 0)
        GPIO.setup(self.pinIn1A, GPIO.OUT)
        GPIO.setup(self.pinIn2A, GPIO.OUT)
        GPIO.setup(self.pinIn1B, GPIO.OUT)
        GPIO.setup(self.pinIn2B, GPIO.OUT)
        
    def setStep(self, out1, out2, out3, out4):
    
        GPIO.output(self.pinIn1A, out1)
        GPIO.output(self.pinIn2A, out2)
        GPIO.output(self.pinIn1B, out3)
        GPIO.output(self.pinIn2B, out4)
        
    def stop(self):
        """
        stops motor
        """
        self.setStep(0, 0, 0, 0);
        
    def write(self, direction, steps=1, delay=.002):
        """
        writes a value to the motor to drive it
        
        direction - direction for the motor to turn 
        steps - number of steps to drive the motor forward
        delay - delay between parts of steps. Total delay time = 4 * delay * steps
        """
        
        if direction == "CW": 
            for _ in range(0, steps):
                self.setStep(1, 0, 1, 0)
                time.sleep(delay)
                self.setStep(0, 1, 1, 0)
                time.sleep(delay)
                self.setStep(0, 1, 0, 1)
                time.sleep(delay)
                self.setStep(1, 0, 0, 1)
                time.sleep(delay)
        else:
            for _ in range(0, steps):
                self.setStep(1, 0, 0, 1)
                time.sleep(delay)
                self.setStep(0, 1, 0, 1)
                time.sleep(delay)
                self.setStep(0, 1, 1, 0)
                time.sleep(delay)
                self.setStep(1, 0, 1, 0)
                time.sleep(delay)