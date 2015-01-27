

class CameraController():
    """
    Class for controlling the logic behind reading
    a camera packet and sending it through the network.
    """
    
    def __init__(self, networkManager, cameraManager):
        """
        Initialize the camera module.
        
        networkManager - the manager that sends camera packets
        cameraManager - the manager that reads a camera frame
        """
        self.networkManager = networkManager
        self.cameraManager = cameraManager
        
    def run(self):
        """
        Runs the core loop of the controller.
        Each iteration of the loop reads a camera frame and then sends a camera frame.
        """
        while 1:
            cameraFrame = self.cameraManager.getFrame()
            self.networkManager.sendFrame(cameraFrame)
                