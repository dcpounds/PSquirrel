import RPi.GPIO as GPIO
import time
 
GPIO.setmode(GPIO.BCM)
 
enable_pin = 24
coil_A_1_pin = 23
coil_A_2_pin = 18
coil_B_1_pin = 15
coil_B_2_pin = 14
 


def forward(delay, steps):  
    for i in range(0, steps):
        setStep(1, 0, 1, 0)
        time.sleep(delay)
        setStep(0, 1, 1, 0)
        time.sleep(delay)
        setStep(0, 1, 0, 1)
        time.sleep(delay)
        setStep(1, 0, 0, 1)
        time.sleep(delay)
 
def backwards(delay, steps):  
    for i in range(0, steps):
        setStep(1, 0, 0, 1)
        time.sleep(delay)
        setStep(0, 1, 0, 1)
        time.sleep(delay)
        setStep(0, 1, 1, 0)
        time.sleep(delay)
        setStep(1, 0, 1, 0)
        time.sleep(delay)
 
  
def setStep(w1, w2, w3, w4):
    
    GPIO.output(coil_A_1_pin, w1)
    GPIO.output(coil_A_2_pin, w2)
    GPIO.output(coil_B_1_pin, w3)
    GPIO.output(coil_B_2_pin, w4)
    print("A1:{}, A2:{}, B1:{}, B2:{}".format(w1,w2,w3,w4))
 
if __name__ == "__main__":
    """
    A1 Pink - Red -23
    A2 Orange - Red White 18
    B1 Blue - Green White 15
    B2 Yellow - Green 14
    Mode- 24
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

    