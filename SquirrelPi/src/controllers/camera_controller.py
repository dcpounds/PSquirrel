import base64
import io
import time
import picamera
import picamera.array



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
        with picamera.PiCamera() as camera:
            stream = io.BytesIO()
            camera.resolution = (512, 384)
            camera.start_preview()
            while 1:
                stream.seek(0)
                camera.capture(stream, format='jpeg', use_video_port=True)
                stream.seek(0)
                img = stream.read()
                self.networkManager.client.send("{:0>10}".format(str(len(img))))
                print "{:0>10}".format(str(len(img)))
                self.networkManager.client.send(img)
                