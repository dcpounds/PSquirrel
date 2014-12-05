

class RobotController():
    
    def init(self, networkManager, sensorManager, motorManager):
        self.networkManager = networkManager
        self.sensorManager = sensorManager
        self.motorManager = motorManager
        self.driveCommand = "STOP"
        self.cameraCommand = "STOP"
        self.sensorData = self.sensorManager.readSensorData()
        self.heartbeatCounter = 100
        
    def run(self):
        while 1:
            if self.heartbeatCounter <= 0:
                self.networkManager.sendHeartbeat()
            if self.networkManager.checkForCommand():
                command = self.networkManager.receiveCommand()
                if command['commandType'] == 'DRIVE':
                    self.driveCommand = command['command']
                    self.networkManager.sendSensorData(self.sensorData)
                if command['commandType'] == 'CAMERA':
                    self.cameraCommand = command['command']
            self.sensorData = self.sensorManager.updateSensorData()
            self.motorManager.driveMotors(self.driveCommand, self.cameraCommand)
                