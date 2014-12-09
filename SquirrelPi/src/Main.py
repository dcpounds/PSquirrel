import sys
import os
sys.path.append(os.path.dirname(__file__), "..");
import multiprocessing as mp
from controllers.CameraController import CameraController
from controllers.RobotController import RobotController
from managers.CameraManager import CameraManager
from managers.MotorManager import MotorManager
from managers.NetworkManager import NetworkManager
from managers.SensorManager import SensorManager


def main():
    networkManager = NetworkManager()
    sensorManager = SensorManager()
    motorManager = MotorManager(sensorManager)
    cameraManager = CameraManager()
    cameraController = CameraController(networkManager, cameraManager)
    robotController = RobotController(networkManager, sensorManager, motorManager)
    
    mp.Process(target=cameraController.run)
    robotController.run()
    
if __name__ == "__main__":
    main()