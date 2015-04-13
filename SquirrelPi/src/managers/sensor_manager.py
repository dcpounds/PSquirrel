from src.sensors.battery import Battery
from src.sensors.potentiometer import Potentiometer
from src.sensors.strain_gauge import StrainGauge
from src.sensors.gyro import Gyro
from src.sensors.accelerometer import Accelerometer
from src.sensors.limit_switch import LimitSwitch
from src.sensors.ultrasonic import Ultrasonic

import math
import spidev

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
        self.spi.open(0, 0)
        
        self.topClawMotorPot = Potentiometer(1, 0, self.spi)
        self.botClawMotorPot = Potentiometer(2, 0, self.spi)
        self.yawMotorPot1 = Potentiometer(3, 0, self.spi)
        self.yawMotorPot2 = Potentiometer(4, 0, self.spi)
        self.pitchMotorPot1 = Potentiometer(5, 0, self.spi)
        self.pitchMotorPot2 = Potentiometer(6, 0, self.spi)
        self.gyro = Gyro(1, 0)
        self.accel = Accelerometer(1, 0)
        self.topTLClaw = StrainGauge(1, 0, self.spi)
        self.topTRClaw = StrainGauge(2, 0, self.spi)
        self.topBLClaw = StrainGauge(3, 0, self.spi)
        self.topBRClaw = StrainGauge(4, 0, self.spi)
        self.botTLClaw = StrainGauge(5, 0, self.spi)
        self.botTRClaw = StrainGauge(6, 0, self.spi)
        self.botBLClaw = StrainGauge(7, 0, self.spi)
        self.botBRClaw = StrainGauge(8, 0, self.spi)
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
        
    def isGimblePastEdge(self, angle):
        """
        Test if ball yaw is within limits
        """
        
        
    def getGimblePotAngles(self, angle):
        if(angle == "YAW"):
            potValues = (self.yawMotorPot1.readValue(), self.yawMotorPot2.readValue())
        else:
            potValues = (self.pitchMotorPot1.readValue(), self.pitchMotorPot2.readValue())
    
        angle1 = potValues[0]*2*math.pi/1024
        angle2 = potValues[1]*2*math.pi/1024
    
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
    
