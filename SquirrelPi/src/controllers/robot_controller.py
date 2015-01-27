

class RobotController():
    """
    Class for receiving drive commands from the network
    and sending a heart beat and sensor data back through the network.
    """
    
    def __init__(self, networkManager, sensorManager, motorManager):
        """
        Initializes the robot controller.
        
        networkManager - manager for sending and receiving packets
        sensorManager - manager for reading the sensor data
        motorManager - manager for driving the motors based on given drive commands
        """
        self.networkManager = networkManager
        self.sensorManager = sensorManager
        self.motorManager = motorManager
        self.driveCommand = "STOP"
        self.cameraCommand = "STOP"
        self.sensorData = self.sensorManager.updateSensorData()
        self.heartbeatCounter = 100
        
    def run(self):
        """
        Runs the core loop of the controller.
        In each iteration the heart beat is checked,
        drive commands are checked for and responded to with sensor data,
        the sensor data is updated,
        the motors are driven.
        """
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
                