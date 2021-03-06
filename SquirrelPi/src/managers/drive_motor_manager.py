from src.motors.gimbal_motor_driver import GimbalMotorDriver
from src.motors.claw_motor_driver import ClawMotorDriver
from src.motors.stepper_motor_driver import StepperMotorDriver
from src.managers.sensor_manager import SensorManager
from src.gaits.drive_gait import DriveGait
from src.gaits.turn_gait import TurnGait

import math
import time

class DriveMotorManager():
    """
    Class for driving motors for the robot gait based off given drive commands
    """
    CW = "CW"
    CCW = "CCW"
    
    
    CLAW_SPEED = 0
    SCREW_SPEED = 0
    
    def __init__(self, sensorManager):
        """
        Initialize the motorManager.
        
        sensorManager - manager for looking at sensor data
        """
        self.currentPitch = 0
        self.currentYaw = 0
        self.steps = 0
        self.sensorManager = sensorManager
        self.gpio_expander = sensorManager.gpio_expander
        self.clawMotorTop = ClawMotorDriver(12, 26, 16, self.gpio_expander)
        self.clawMotorBottom = ClawMotorDriver(13, 21, 20, self.gpio_expander)
        self.gimbleMotorYaw1 = GimbalMotorDriver(8, 17, 27, self.gpio_expander)
        self.gimbleMotorYaw2 = GimbalMotorDriver(9, 5, 12, self.gpio_expander)
        self.gimbleMotorPitch1 = GimbalMotorDriver(15, 6, 24, self.gpio_expander)
        self.gimbleMotorPitch2 = GimbalMotorDriver(11, 13, 19, self.gpio_expander)
        self.screwMotor = StepperMotorDriver(14, 4, 18, 15, 25, self.gpio_expander)
        self.upGait = DriveGait("UP", sensorManager, self)
        self.downGait = DriveGait("DOWN", sensorManager, self)
        self.leftGait = TurnGait("LEFT", sensorManager, self)
        self.rightGait = TurnGait("RIGHT", sensorManager, self)
    
    def executeDriveCommand(self, driveCommand):
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
            clawMotor = self.clawMotorTop
        else:
            clawMotor = self.clawMotorBottom
        
        
        while(not self.sensorManager.areClawsAttached(robotHalf)):
            clawMotor.write()
            
        clawMotor.stop()
        
    def detachClaws(self, robotHalf):
        """
        Attach the claws of the given half
        
        distance - specified half (TOP or BOTTOM)
        """
        if(robotHalf == "TOP"):
            clawMotor = self.clawMotorTop
        else:
            clawMotor = self.clawMotorBottom
            
        while(not self.sensorManager.areClawsInFirePosition(robotHalf)):
            clawMotor.write()
            
        clawMotor.stop()
    
    def driveLeadScrew(self, direction, steps=5):
        """
        drive lead screw in specified direction
        
        direction - direction to drive in (RETRACT or EXTEND)
        steps - number of steps to drive in
        """
        if(direction == "RETRACT"):
            self.steps -= steps
        else:
            self.steps += steps
            
        self.screwMotor.write(direction, steps)
        self.sensorManager.setSteps(self.steps)
        
        
    def driveToAngleValue(self, angle, value):
        """
        turn robot gimble one degree in specified direction
        
        angle - gimble angle to drive on (YAW or PITCH)
        value - value in degrees to drive to (-25 to 25)
        """
        if(angle == "PITCH"):
            motor1 = self.gimbleMotorPitch1
            motor2 = self.gimbleMotorPitch2
            self.currentPitch = value
            self.sensorManager.setPitch(self.currentPitch)
        else:
            motor1 = self.gimbleMotorYaw1
            motor2 = self.gimbleMotorYaw2
            self.currentYaw = value
            self.sensorManager.setYaw(self.currentYaw)
            
        
        value = value*math.pi/180.0
            
        height = 1.15
        length = 3.6
        pulleyRadius = 0.375
        tolerance = 0.1
        speed = 75
        
        desiredAngle1 = (height*(math.cos(value/2) - 1) + length*(math.sin(value/2)))/pulleyRadius
        desiredAngle2 = -(height*(math.cos(value/2) - 1) - length*(math.sin(value/2)))/pulleyRadius
        
        currentAngle1, currentAngle2 = self.sensorManager.getGimblePotAngles(angle)
        
        print "Desired angles {}, {}".format(desiredAngle1, desiredAngle2)
        print "Current angles {}, {}".format(currentAngle1, currentAngle2)
        
        
        while(abs(currentAngle1 - desiredAngle1) > tolerance 
                 and abs(currentAngle2 - desiredAngle2) > tolerance):
            if(abs(currentAngle1 - desiredAngle1) > 0.01):
                if(currentAngle1 < desiredAngle1):
                    motor1.write("CCW", speed)
                else:
                    motor1.write("CW", speed)
            else:
                motor1.stop()
            if(abs(currentAngle2 - desiredAngle2) > 0.01):
                if(currentAngle2 < desiredAngle2):
                    motor2.write("CW", speed)
                else:
                    motor2.write("CCW", speed)
            else:
                motor2.stop
            currentAngle1, currentAngle2 = self.sensorManager.getGimblePotAngles(angle)
            
        motor1.stop()
        motor2.stop()
        
    
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