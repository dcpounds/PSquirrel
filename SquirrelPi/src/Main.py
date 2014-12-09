import multiprocessing as mp
from src.controllers.CameraController import CameraController
from src.controllers.RobotController import RobotController
from src.managers.CameraManager import CameraManager
from src.managers.MotorManager import MotorManager
from src.managers.NetworkManager import NetworkManager
from src.managers.SensorManager import SensorManager


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