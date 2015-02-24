import base64
import time

class CameraController():
    """
    Class for controlling the logic behind reading
    a camera packet and sending it through the network.
    """
    
    def __init__(self, networkManager):
        """
        Initialize the camera module.
        
        networkManager - the manager that sends camera packets
        cameraStreamManager - the manager that reads a camera frame
        """
        self.networkManager = networkManager
        
    def run(self):
        """
        Runs the core loop of the controller.
        Each iteration of the loop reads a camera frame and then sends a camera frame.
        """
        
        while 1:
            with open("../resources/test1.jpeg", 'r') as image1:
                with open("../resources/test2.jpeg", 'r') as image2:
                    img1 = image1.read()
                    self.networkManager.client.send("{:0>10}".format(str(len(img1))))
                    self.networkManager.client.send(img1)
                    print "sending 1"
                    print base64.b64encode(img1)
                    time.sleep(0.5);
                    
                    print "sending 2"
                    img2 = image2.read()
                    self.networkManager.client.send("{:0>10}".format(str(len(img2))))
                    self.networkManager.client.send(img2)
                    print base64.b64encode(img2)
                    time.sleep(0.5);
                