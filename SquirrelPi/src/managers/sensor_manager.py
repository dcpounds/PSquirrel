from src.sensors.battery import Battery
from src.sensors.spi_sensor import SPISensor
from src.sensors.imu import IMUSensor
from src.sensors.limit_switch import LimitSwitch
from src.sensors.ultrasonic import Ultrasonic
from src.adafruit.Adafruit_MCP230xx import Adafruit_MCP230XX

import math
import spidev
import RPi.GPIO as GPIO

class SensorManager():
    """
    Class for managing reading sensor data
    """
    POT_FIRE_POSITION = None
    
    BALL_YAW_MIN = None
    BALL_YAW_MAX = None
    
    BALL_PITCH_MIN = None
    BALL_PITCH_MAX = None
   
    CLAW_THRESHOLD = -1

    
    def __init__(self):
        """
        Initializes the sensor manager with the various list of sensors.
        """
        
        self.spi = spidev.SpiDev()
        self.gpio_expander = Adafruit_MCP230XX(address = 0x20, num_gpios = 16) 
        GPIO.setmode(GPIO.BCM)
        
        self.topClawMotorPot = SPISensor(1, 0, 0, self.spi)
        self.topClawMotorPotStartValue = self.topClawMotorPot.readValue()
        self.botClawMotorPot = SPISensor(2, 1, 0, self.spi)
        self.botClawMotorPotStartValue = self.topClawMotorPot.readValue()
        self.yawMotorPot1 = SPISensor(3, 4, 0, self.spi)
        self.yawMotorPot1StartValue = self.yawMotorPot1.readValue()
        self.yawMotorPot2 = SPISensor(4, 5, 0, self.spi)
        self.yawMotorPot2StartValue = self.yawMotorPot2.readValue()
        self.pitchMotorPot1 = SPISensor(5, 6, 0, self.spi)
        self.pitchMotorPot1StartValue = self.pitchMotorPot1.readValue()
        self.pitchMotorPot2 = SPISensor(6, 7, 0, self.spi)
        self.pitchMotorPot2StartValue = self.pitchMotorPot2.readValue()
        self.IMU = IMUSensor(1, 0)
        self.topTLClaw = SPISensor(1, 0, 1, self.spi)
        self.topTRClaw = SPISensor(2, 0, 1, self.spi)
        self.topBLClaw = SPISensor(3, 0, 1, self.spi)
        self.topBRClaw = SPISensor(4, 0, 1, self.spi)
        self.botTLClaw = SPISensor(5, 0, 1, self.spi)
        self.botTRClaw = SPISensor(6, 0, 1, self.spi)
        self.botBLClaw = SPISensor(7, 0, 1, self.spi)
        self.botBRClaw = SPISensor(8, 0, 1, self.spi)
        self.leadScrewTopLim = LimitSwitch(1, 0)
        self.leadScrewBotLim = LimitSwitch(2, 0)
        self.topBranchUltrasonic = Ultrasonic(1, 0)
        self.topTreeUltrasonic = Ultrasonic(2, 0)
        self.botBranchUltrasonic = Ultrasonic(1, 0)
        self.botTreeUltrasonic = Ultrasonic(2, 0)
        self.battery = Battery(1, 0)
        
    def readRobotPositionData(self):
        """
        reads all the data about the robot's position, claws attached, and battery
        """
        accelValues = self.accel.readValue()
        gyroValues = self.gyro.readValue()
        alpha = -1
        yaw = -1
        gamma = -1
        pitch = -1
        
        claws = [self.topTLClaw, self.topTRClaw, self.topBLClaw, self.topBRClaw,
                 self.botTLClaw, self.botTRClaw, self.botBLClaw, self.botBRClaw]
        attachedClaws = [claw for claw in claws if claw.readValue() > self.CLAW_THRESHOLD]
        
        topClearance = self.topTreeUltrasonic.readValue()
        botClearance = self.botTreeUltrasonic.readValue()
        battery = self.battery.readValue()
        
        return {"Alpha": alpha,
                "Yaw": yaw,
                "Gamma": gamma, 
                "Pitch": pitch,
                "Extend": -1,
                "AttachedClaws": attachedClaws,
                "TopClearance": topClearance,
                "BotClearance": botClearance,
                "Battery": battery}
        
    def areClawsAttached(self, robotHalf, num_claws=3):
        """
        Determines whether the claws for the given robotHalf are attached
        
        robotHalf - half of the robot to read claws from
        num_claws - the number of claws required to be attached (defaults to 3)
        """
        if(robotHalf == "TOP"):
            claws = [self.topTLClaw, self.topTRClaw, self.topBLClaw, self.topBRClaw]
        else:
            claws = [self.botTLClaw, self.botTRClaw, self.botBLClaw, self.botBRClaw]
        
        return sum([claw > self.CLAW_THRESHOLD for claw in claws]) > num_claws
    
    def areClawsInFirePosition(self, robotHalf):
        """
        Determines if claws are raised enough to be in fire position
        """
        if(robotHalf == "TOP"):
            potValue = self.topClawMotorPot.readValue()
        else:
            potValue = self.botClawMotorPot.readValue()
            
        return potValue > self.POT_FIRE_POSITION
        
        
    def getGimblePotAngles(self, angle):
        if(angle == "YAW"):
            potValues = (self.yawMotorPot1.readValue(), self.yawMotorPot2.readValue())
            start_values = (self.yawMotorPot1StartValue, self.yawMotorPot2StartValue)
        else:
            potValues = (self.pitchMotorPot1.readValue(), self.pitchMotorPot2.readValue())
            start_values = (self.pitchMotorPot1StartValue, self.pitchMotorPot2StartValue)
    
        angle1 = (potValues[0] - start_values[0]) * math.pi/230
        angle2 = (potValues[1] - start_values[1]) * math.pi/230 
    
        return (angle1, angle2)
    
    def isLeadScrewPastEdge(self):
        """
        Test if lead screw is past edge
        """
        
        return self.isLeadScrewExtended() or self.isLeadScrewRetracted()
        
    def isLeadScrewExtended(self):
        """
        Test if lead screw is extended
        """
        return self.leadScrewTopLim.readValue() == 1
    
    def isLeadScrewRetracted(self):
        """
        Test if lead screw is retracted
        """
        return self.leadScrewBotLim.readValue() == 1
    
