import os
import sys
import time
sys.path.append(os.path.join(os.path.dirname(__file__), ".."));
from src.managers.sensor_manager import SensorManager
import RPi.GPIO as GPIO

def main():
    
    sensor_manager = SensorManager()
    
    while 1:
        try:
            print sensor_manager.getGimblePotAngles("PITCH")
            time.sleep(1)
        except KeyboardInterrupt:
            break
    GPIO.cleanup()

if __name__ == '__main__':
    main()