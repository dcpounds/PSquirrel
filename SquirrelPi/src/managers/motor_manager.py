from src.motors.motor import Motor

class MotorManager():
    """
    Class for driving motors for the robot gait based off given drive commands
    """
    
    def __init__(self, sensorManager):
        """
        Initialize the motorManager.
        
        sensorManager - manager for looking at sensor data
        """
        self.cameraMotor = Motor(0)
        self.clawMotorTop = Motor(0)
        self.clawMotorBottom = Motor(0)
        self.ballMotorForward = Motor(0)
        self.ballMotorSide = Motor(0)
        self.screwMotor = Motor(0)
    
    def driveMotors(self, driveCommand, cameraCommand):
        """
        Run correct gaits based on the given camera and drive commands.
        """
        cameraFunctions = {"UP": self.driveCameraUp,
                           "DOWN": self.driveCameraDown,
                           "STOP": self.stopCamera}
        driveFunctions = {"UP": self.driveUp,
                          "DOWN": self.driveDown,
                          "LEFT": self.driveLeft,
                          "RIGHT": self.driveRight,
                          "STOP": self.stop}
        
        cameraFunctions[cameraCommand]()
        driveFunctions[driveCommand]()
        
    def driveCameraUp(self):
        """
        Rotate the camera upwards.
        """
        self.cameraMotor.write(0, 127)
        
    def driveCameraDown(self):
        """
        Rotate the camera downwards.
        """
        self.cameraMotor.write(1, 127)
        
    def stopCamera(self):
        """
        Stop the Camera
        """
        self.cameraMotor.write(0, 0)
        
    def driveUp(self):
        """
        Determine and send the correct motor signals for the up gait.
        """
        return
    
    def driveDown(self):
        """
        Determine and send the correct motor signals for the down gait.
        """
        return
    
    def driveLeft(self):
        """
        Determine and send the correct motor signals for the left gait.
        """
        return
    
    def driveRight(self):
        """
        Determine and send the correct motor signals for the right gait.
        """
        return
    
    def stop(self):
        """
        Stop the robot.
        """
        self.clawMotorTop.write(0, 0)
        self.clawMotorBottom.write(0, 0)
        self.ballMotorForward.write(0, 0)
        self.ballMotorSide.write(0, 0)
        self.screwMotor.write(0, 0)