import RPi.GPIO as GPIO
import time
 
GPIO.setmode(GPIO.BCM)
if __name__ == "__main__":
    GPIO.setmode(GPIO.BCM)
    GPIO.setup(25, GPIO.OUT)
    GPIO.output(25, 1)
    time.sleep(10)
    GPIO.cleanup()

    