class CameraManager():
    """Class for receiving frames from the camera"""
    
    def __init__(self):
        """
        Initializes the camera manager
        """
        self.camera = None
        
    def getFrame(self):
        """
        Get an encoded camera frame
        """
        self.camera.getFrame()