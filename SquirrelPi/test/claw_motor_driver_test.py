import os
import sys
sys.path.append(os.path.join(os.path.dirname(__file__), ".."));
from src.motors.claw_motor_driver import ClawMotorDriver
from src.adafruit.Adafruit_MCP230xx import Adafruit_MCP230XX
import RPi.GPIO as GPIO

import time

def main():
    
    GPIO.setmode(GPIO.BCM)
    gpio_expander = Adafruit_MCP230XX(address = 0x20, num_gpios = 16) 
    motor1 = ClawMotorDriver(12, 26, 16, gpio_expander)
    motor2 = ClawMotorDriver(13, 21, 20, gpio_expander)
    motor1.write()
    motor2.write()
    time.sleep(60)
    motor1.stop()
    motor2.stop()
    GPIO.cleanup()


if __name__ == '__main__':
    main()