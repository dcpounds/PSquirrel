from src.motors.motor import Motor
from src.managers.sensor_manager import SensorManager
from src.gaits.drive_gait import DriveGait
from src.gaits.turn_gait import TurnGait

class DriveMotorManager():
    """
    Class for driving motors for the robot gait based off given drive commands
    """
    
    BALL_PITCH_SPEED = 0
    BALL_YAW_SPEED = 0
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
        self.ballMotorYaw = Motor(0)
        self.ballMotorPitch = Motor(0)
        self.screwMotor = Motor(0)
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
        #PID stuff
        pass
    
    def attachClaws(self, robotHalf):
        """
        Attach the claws of the given half
        
        distance - specified half (TOP or BOTTOM)
        """
        #attach claws
        pass
        
    def detachClaws(self, robotHalf):
        """
        Attach the claws of the given half
        
        distance - specified half (TOP or BOTTOM)
        """
        #detach claws
        pass
    
    def driveLeadScrew(self, direction):
        """
        drive lead screw in specified direction
        
        direction - direction to drive in (RETRACT or EXTEND)
        """
        if(self.sensor_manager.isLeadScrewPastEdge()):
            if(direction == "RETRACT"):
                self.screwMotor.write(Motor.CCW, self.SCREW_SPEED)
            elif(direction == "EXTEND"):
                self.screwMotor.write(Motor.CW, self.SCREW_SPEED)
        elif(direction == "RETRACT"):
            self.screwMotor.write(Motor.CW, self.SCREW_SPEED)
        elif(direction == "EXTEND"):
            self.screwMotor.write(Motor.CCW, self.SCREW_SPEED)
        else:
            self.screwMotor.stop()
            raise ValueError("Invalid Direction")
        
    def turn(self, direction):
        """
        turn robot in specified direction
        
        direction - direction to drive in (LEFT or RIGHT)
        """
        if(self.sensor_manager.isBallPitchPastEdge()):
            if(direction == "LEFT"):
                self.ballMotorPitch.write(Motor.CCW, self.SCREW_SPEED)
            elif(direction == "RIGHT"):
                self.ballMotorPitch.write(Motor.CW, self.SCREW_SPEED)
        elif(direction == "LEFT"):
            self.ballMotorPitch.write(Motor.CW, self.BALL_PITCH_SPEED)
        elif(direction == "RIGHT"):
            self.ballMotorPitch.write(Motor.CCW, self.BALL_PITCH_SPEED)
        else:
            self.ballMotorPitch.stop()
            raise ValueError("Invalid Direction")
    
    def stop(self):
        """
        Stop the robot.
        """
        self.clawMotorTop.stop()
        self.clawMotorBottom.stop()
        self.ballMotorYaw.stop()
        self.ballMotorPitch.stop()
        self.screwMotor.stop()