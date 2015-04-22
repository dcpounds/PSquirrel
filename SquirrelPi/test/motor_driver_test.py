import os
import sys
sys.path.append(os.path.join(os.path.dirname(__file__), ".."));
from src.motors.gimbal_motor_driver import GimbalMotorDriver
from src.adafruit.Adafruit_MCP230xx import Adafruit_MCP230XX
import RPi.GPIO as GPIO

import time

def main():
    
    GPIO.setmode(GPIO.BCM)
    gpio_expander = Adafruit_MCP230XX(address = 0x20, num_gpios = 16) 
    motor1 = GimbalMotorDriver(11, 13 , 19, gpio_expander)
    motor2 = GimbalMotorDriver(15, 6, 24, gpio_expander)
    motor1.write("CCW")
    motor2.write("CCW")
    time.sleep(60)
    motor1.stop()
    motor2.stop()
    GPIO.cleanup()


if __name__ == '__main__':
    main()