from src.motors.gimbal_motor_driver import Motor_Driver

class CameraMotorManager():
    """
    Class for driving motors for the robot gait based off given drive commands
    """
    
    CAMERA_SPEED = 127
    
    def __init__(self, sensorManager):
        """
        Initialize the motorManager.
        
        sensorManager - manager for looking at sensor data
        """
        self.sensorManager = sensorManager
        self.cameraMotor = Motor_Driver(0)
        
    def executeDriveCommand(self, cameraCommand):
        """
        Run correct gait based on the given camera command.
        """
        cameraFunctions = {"UP": self.driveCameraUp,
                           "DOWN": self.driveCameraDown,
                           "STOP": self.stopCamera}
        cameraFunctions[cameraCommand]()
        
    def driveCameraUp(self):
        """
        Rotate the camera upwards.
        """
        if(self.isCameraAtEdge):
            self.cameraMotor.stop()
        else:
            self.cameraMotor.write(Motor_Driver.CW, self.CAMERA_SPEED)
        
    def driveCameraDown(self):
        """
        Rotate the camera downwards.
        """
        if(self.sensorManager.isCameraAtEdge()):
            self.cameraMotor.stop()
        else:
            self.cameraMotor.write(Motor_Driver.CW, self.CAMERA_SPEED)
        
    def stopCamera(self):
        """
        Stop the Camera
        """
        self.cameraMotor.stop(0, 0)
        