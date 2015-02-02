import sys
import os
sys.path.append(os.path.join(os.path.dirname(__file__), ".."));
import multiprocessing as mp
from src.controllers import camera_controller
from src.controllers import robot_controller
from src.managers import camera_stream_manager
from src.managers import drive_motor_manager
from src.managers import network_manager
from src.managers import sensor_manager


def main():
    """
    Creates all the managers.
    Creates a subprocess for each controller and runs them separately
    """
    
    networkManager = network_manager()
    sensorManager = sensor_manager()
    motorManager = drive_motor_manager(sensorManager)
    cameraManager = camera_stream_manager()
    cameraController = camera_controller(networkManager, cameraManager)
    robotController = robot_controller(networkManager, sensorManager, motorManager)
    
    mp.Process(target=cameraController.run)
    robotController.run()
    
if __name__ == "__main__":
    main()