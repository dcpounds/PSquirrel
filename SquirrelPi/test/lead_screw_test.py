import os
import sys
import time
sys.path.append(os.path.join(os.path.dirname(__file__), ".."));
from src.managers.drive_motor_manager import DriveMotorManager
from src.managers.sensor_manager import SensorManager
import RPi.GPIO as GPIO
 

 
if __name__ == "__main__":
    
    sensor_manager = SensorManager()
    drive_manager = DriveMotorManager(sensor_manager)
    
    drive_manager.driveLeadScrew("FORWARD", 1000)
    drive_manager.stop()
    time.sleep(5)
    drive_manager.driveLeadScrew("BACKWARD", 1000)
    drive_manager.stop()
    
    
    GPIO.cleanup()
    
    """
    A1 Pink - Red -23
    A2 Orange - Red White 18
    B1 Blue - Green White 15
    B2 Yellow - Green 14
    Mode- 24
    """
    """
    GPIO.cleanup()
    GPIO.setup(enable_pin, GPIO.OUT)
    GPIO.setup(coil_A_1_pin, GPIO.OUT)
    GPIO.setup(coil_A_2_pin, GPIO.OUT)
    GPIO.setup(coil_B_1_pin, GPIO.OUT)
    GPIO.setup(coil_B_2_pin, GPIO.OUT)
 
    GPIO.output(enable_pin, 0)
    delay = 2.5
    steps = 10000
    forward(int(delay) / 1000.0, int(steps))
    backwards(int(delay) / 1000.0, int(steps))
    setStep(0,0,0,0)
    GPIO.cleanup()
    """
    