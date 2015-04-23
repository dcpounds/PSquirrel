

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
        driveMotorManager - manager for driving the motors based on given drive commands
        """
        self.networkManager = networkManager
        self.sensorManager = sensorManager
        self.driveMotorManager = motorManager
        self.driveCommand = "STOP"
        self.cameraCommand = "STOP"
        
    def run(self):
        """
        Runs the core loop of the controller.
        In each iteration the heart beat is checked,
        drive commands are checked for and responded to with sensor data,
        the sensor data is updated,
        the motors are driven.
        """
        while 1:
            if self.networkManager.checkForCommand():
                command = self.networkManager.receiveCommand()
                if command['commandType'] == 'DRIVE':
                    self.driveCommand = command['command']
                if command['commandType'] == 'CAMERA':
                    self.cameraCommand = command['command']
            #self.stateData = self.sensorManager.getRobotStateData()
            #self.networkManager.sendRobotStateData(self.stateData)
            self.driveMotorManager.executeDriveCommand(self.driveCommand)
            #self.cameraMotorManager.executeDriveCommand(self.cameraCommand)
                