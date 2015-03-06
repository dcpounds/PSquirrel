import RPi.GPIO as GPIO
import time
import spidev


motor_en = 6 
motor_plus = 13 
motor_minus = 19

#!/usr/bin/python
# -*- coding: utf-8 -*-
# mcp3008_lm35.py - read an LM35 on CH0 of an MCP3008 on a Raspberry Pi
# mostly nicked from
#  http://jeremyblythe.blogspot.ca/2012/09/raspberry-pi-hardware-spi-analog-inputs.html
def readadc(adcnum):
# read SPI data from MCP3008 chip, 8 possible adc's (0 thru 7)
    if adcnum > 7 or adcnum < 0:
        return -1
    r = spi.xfer2([1, 8 + adcnum << 4, 0])
    adcout = ((r[1] & 3) << 8) + r[2]
    return adcout

def run_claws():
    frequency = .001
    GPIO.output(motor_plus, 1)
    GPIO.output(motor_minus, 0)
    GPIO.output(motor_en, 1)
    #p = GPIO.PWM(motor_en, frequency)
    #p.start(100)
    #p.stop()

def stop_claws():
    frequency = .001
    GPIO.output(motor_plus, 0)
    GPIO.output(motor_minus, 0)
    GPIO.output(motor_en, 0)
    #p = GPIO.PWM(motor_en, frequency)
    #p.start(100)
    #p.stop()   

if __name__ == '__main__':
    spi = spidev.SpiDev()
    spi.open(0, 0)
    
    GPIO.setmode(GPIO.BCM)
    GPIO.setup(motor_en, GPIO.OUT)
    GPIO.setup(motor_plus, GPIO.OUT)
    GPIO.setup(motor_minus, GPIO.OUT)
    
    run_claws()
    print "lifting"
    pot = readadc(6)
    while pot < 900:
        pot = readadc(6)
        time.sleep(.001)
    print "stopping"
    stop_claws()
    time.sleep(7)
    print "releasing"
    run_claws()
    while pot > 900:
        pot = readadc(6)
        time.sleep(.001)
    stop_claws()
     
    GPIO.cleanup()