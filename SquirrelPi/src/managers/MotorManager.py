from src.motors.motor import Motor

class MotorManager():
    
    def __init__(self, sensorManager):
        self.cameraMotor = Motor(0)
        self.clawMotorTop = Motor(0)
        self.clawMotorBottom = Motor(0)
        self.ballMotorForward = Motor(0)
        self.ballMotorSide = Motor(0)
        self.screwMotor = Motor(0)
    
    def driveMotors(self, driveCommand, cameraCommand):
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
        self.cameraMotor.write(0, 127)
        
    def driveCameraDown(self):
        self.cameraMotor.write(1, 127)
        
    def stopCamera(self):
        self.cameraMotor.write(0, 0)
        
    def driveUp(self):
        return
    
    def driveDown(self):
        return
    
    def driveLeft(self):
        return
    
    def driveRight(self):
        return
    
    def stop(self):
        self.clawMotorTop.write(0, 0)
        self.clawMotorBottom.write(0, 0)
        self.ballMotorForward.write(0, 0)
        self.ballMotorSide.write(0, 0)
        self.screwMotor.write(0, 0)