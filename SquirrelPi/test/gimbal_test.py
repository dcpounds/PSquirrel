import os
import sys
import time
sys.path.append(os.path.join(os.path.dirname(__file__), ".."));
from src.managers.drive_motor_manager import DriveMotorManager
from src.managers.sensor_manager import SensorManager
import RPi.GPIO as GPIO

def main():
    
    sensor_manager = SensorManager()
    drive_manager = DriveMotorManager(sensor_manager)
    
    for _ in range(0, 10):
        try:
            drive_manager.driveToAngleValue("PITCH", 5)
        except KeyboardInterrupt:
            break
    GPIO.cleanup()

if __name__ == '__main__':
    main()