import sys
import os
sys.path.append(os.path.join(os.path.dirname(__file__), ".."));
import multiprocessing as mp
from src.controllers import camera_controller
from src.controllers import robot_controller
from src.managers import network_manager
from src.managers import sensor_manager


def main():
    """
    Creates all the managers.
    Creates a subprocess for each controller and runs them separately
    """
    
    host = '10.5.5.1'
    mainPort = 9003
    cameraPort = 9004
    bufSize = 8192
    
    mainNetworkManager = network_manager.NetworkManager(host, mainPort, bufSize)
    cameraNetworkManager = network_manager.NetworkManager(host, cameraPort, bufSize)
    
    
    #sensorManager = sensor_manager()
    #motorManager = drive_motor_manager(sensorManager)
    #robotController = robot_controller(networkManager, sensorManager, motorManager)
    cameraController = camera_controller.CameraController(cameraNetworkManager)
    
    #mp.Process(target=cameraController.run)
    #robotController.run()
    cameraController.run()
    
if __name__ == "__main__":
    main()