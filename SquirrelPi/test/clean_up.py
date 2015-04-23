import RPi.GPIO as GPIO
import time
 
GPIO.setmode(GPIO.BCM)
if __name__ == "__main__":
    GPIO.setmode(GPIO.BCM)
    reserved_pins = [2, 3, 7, 8, 9, 10, 11]
    for x in range(2, 28):
        if x not in reserved_pins:
            GPIO.setup(x, GPIO.OUT)
    GPIO.cleanup()

    