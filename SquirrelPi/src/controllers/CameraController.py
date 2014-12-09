

class CameraController():
    
    def __init__(self, networkManager, cameraManager):
        self.networkManager = networkManager
        self.cameraManager = cameraManager
        
    def run(self):
        cameraFrame = self.cameraManager.getFrame()
        self.networkManager.sendFrame(cameraFrame)
                