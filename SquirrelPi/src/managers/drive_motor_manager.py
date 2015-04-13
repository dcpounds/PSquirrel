from src.motors.motor import Motor
from src.motors.stepper_motor import StepperMotor
from src.managers.sensor_manager import SensorManager
from src.gaits.drive_gait import DriveGait
from src.gaits.turn_gait import TurnGait

import math

class DriveMotorManager():
    """
    Class for driving motors for the robot gait based off given drive commands
    """
    CW = 0
    CCW = 1
    
    GIMBLE_SPEED = 0
    CLAW_SPEED = 0
    SCREW_SPEED = 0
    
    def __init__(self, sensorManager):
        """
        Initialize the motorManager.
        
        sensorManager - manager for looking at sensor data
        """
        self.sensorManager = sensorManager
        self.clawMotorTop = Motor(0)
        self.clawMotorBottom = Motor(0)
        self.gimbleMotorYaw1 = Motor(0)
        self.gimbleMotorYaw2 = Motor(0)
        self.gimbleMotorPitch1 = Motor(0)
        self.gimbleMotorPitch2 = Motor(0)
        self.screwMotor = StepperMotor(0)
        self.upGait = DriveGait("UP", sensorManager, self)
        self.downGait = DriveGait("DOWN", sensorManager, self)
        self.leftGait = TurnGait("LEFT", sensorManager, self)
        self.rightGait = TurnGait("RIGHT", sensorManager, self)
    
    def driveMotor(self, driveCommand, cameraCommand):
        """
        Run correct gait based on the given drive command.
        """
        driveFunctions = {"UP": self.upGait.execute,
                          "DOWN": self.downGait.execute,
                          "LEFT": self.leftGait.execute,
                          "RIGHT": self.rightGait.execute,
                          "STOP": self.stop}
        driveFunctions[driveCommand]()
        return
    
    def maintainDistance(self, robotHalf):
        """
        Make sure the corresponding robot half is the correct distance from the tree
        
        distance - specified half (TOP or BOTTOM)
        """
    
    def attachClaws(self, robotHalf):
        """
        Attach the claws of the given half
        
        distance - specified half (TOP or BOTTOM)
        """
        if(robotHalf == "TOP"):
            clawMotor = self.clawMotorTop()
        else:
            clawMotor = self.clawMotorBot()
        
        while(not self.sensorManager.areClawsAttached(robotHalf)):
            clawMotor.write(self.CW, self.CLAW_SPEED)
            
        clawMotor.stop()
        
    def detachClaws(self, robotHalf):
        """
        Attach the claws of the given half
        
        distance - specified half (TOP or BOTTOM)
        """
        if(robotHalf == "TOP"):
            clawMotor = self.clawMotorTop()
        else:
            clawMotor = self.clawMotorBot()
            
        while(not self.sensorManager.areClawsInFirePostion(robotHalf)):
            clawMotor.write(self.CW, self.CLAW_SPEED)
            
        clawMotor.stop()
    
    def driveLeadScrewStep(self, direction):
        """
        drive lead screw in specified direction
        
        direction - direction to drive in (RETRACT or EXTEND)
        """
        if(direction == "RETRACT"):
            self.screwMotor.write(self.CW, self.SCREW_SPEED)
        else:
            self.screwMotor.write(self.CCW, self.SCREW_SPEED)
        
    def turnStep(self, angle, direction):
        """
        turn robot in specified direction
        
        direction - direction to drive in (LEFT or RIGHT)
        """
        if(angle == "PITCH"):
            motor1 = self.gimbleMotorPitch1.stop()
            motor2 = self.gimbleMotorPitch2.stop()
        else:
            motor1 = self.gimbleMotorYaw1.stop()
            motor2 = self.gimbleMotorYaw2.stop()
        angles = self.sensorManager.getGimblePotAngles(angle)
    
        speed1 = self.GIMBLE_SPEED
        """do calculation for speed of other motor here"""
        e = 1
        f = 1
        s0 = 1
        r = 1
        
        s = s0 + r*angles[0]
        l = s0 + r*angles[1]
        S = math.acos((-(s**2) + e**2 + f**2)/(2*e*f))
        L = math.acos((-(l**2) + e**2 + f**2)/(2*e*f))
         
        speed2 = -(l*math.sin(S))/(s*math.sin(L)) 
        
        if(direction == "LEFT"):
            motor1.write(self.CW, speed1)
            motor2.write(self.CW, speed2)
        else:
            motor1.write(self.CCW, speed2)
            motor2.write(self.CCW, speed1)
    
    def stop(self):
        """
        Stop the robot.
        """
        self.gimbleMotorPitch1.stop()
        self.gimbleMotorPitch2.stop()
        self.gimbleMotorYaw1.stop()
        self.gimbleMotorYaw2.stop()
        self.clawMotorTop.stop()
        self.clawMotorBottom.stop()
        self.screwMotor.stop()