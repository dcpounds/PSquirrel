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
    #drive_manager.stop()
    
    
    while 1:
        try:
            
            print "moving down"
            
            drive_manager.detachClaws("BOTTOM");
            #drive_manager.driveToAngleValue("PITCH", -5);
            drive_manager.driveLeadScrew("RETRACT", 3000);
            #drive_manager.driveToAngleValue("PITCH", 5);
            drive_manager.attachClaws("BOTTOM");
            
            print "moving up"
            drive_manager.detachClaws("TOP");
            #drive_manager.driveToAngleValue("PITCH", -5);
            drive_manager.driveLeadScrew("EXTEND", 3000);
            #drive_manager.driveToAngleValue("PITCH", 5);
            drive_manager.attachClaws("TOP");
            
        except KeyboardInterrupt:
            GPIO.cleanup()
            break    
    GPIO.cleanup()

if __name__ == '__main__':
    main()