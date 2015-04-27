import os
import sys
import time
sys.path.append(os.path.join(os.path.dirname(__file__), ".."));
from src.managers.drive_motor_manager import DriveMotorManager
from src.managers.sensor_manager import SensorManager
import RPi.GPIO as GPIO

if __name__ == '__main__':
    
    sensor_manager = SensorManager()
    drive_manager = DriveMotorManager(sensor_manager)
   
    drive_manager.detachClaws("BOTTOM")
    time.sleep(5)
    drive_manager.attachClaws("BOTTOM")
   
    GPIO.cleanup()