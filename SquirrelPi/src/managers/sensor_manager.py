from src.sensors.encoder import Encoder
from src.sensors.potentiometer import Potentiometer
from src.sensors.current import Current
from src.sensors.gyro import Gyro
from src.sensors.ir import IR
from src.sensors.limit_switch import LimitSwitch
from src.sensors.sonar import Sonar

class SensorManager():
    """
    Class for managing reading sensor data
    """
    
    CAMERA = 1
    CAMERA_ANGLE_MIN = 0
    CAMERA_ANGLE_MAX = 1024    
    
    BALL_YAW = 1
    BALL_YAW_MIN = 0
    BALL_YAW_MAX = 1024
    
    BALL_PITCH = 2
    BALL_PITCH_MIN = 0
    BALL_PITCH_MAX = 1024
    
    SCREW_ENCODER = 1
    SCREW_RETRACTED = 0
    SCREW_EXTENDED = 1024
    
    CLAW_TTL = 1
    CLAW_TTR = 2
    CLAW_TBL = 3
    CLAW_TBR = 4
    CLAW_BTL = 5
    CLAW_BTR = 6
    CLAW_BBL = 7
    CLAW_BBR = 8
    IR_TOP = 1
    IR_BOT = 2
    SONAR_TOP = 1 
    SONAR_BOT = 2
    
    def __init__(self):
        """
        Initializes the sensor manager with the various list of sensors.
        """
        self.sensors = []
        self.sensors.append(Encoder(1, 0))
        self.sensors.append(Potentiometer(1, 0))
        self.sensors.append(Potentiometer(2, 0))
        self.sensors.append(Current(1, 0))
        self.sensors.append(Current(2, 0))
        self.sensors.append(Current(3, 0))
        self.sensors.append(Current(4, 0))
        self.sensors.append(Current(5, 0))
        self.sensors.append(Gyro(1, 0))
        self.sensors.append(IR(1, 0))
        self.sensors.append(IR(2, 0))
        self.sensors.append(LimitSwitch(1, 0))
        self.sensors.append(LimitSwitch(2, 0))
        self.sensors.append(LimitSwitch(3, 0))
        self.sensors.append(LimitSwitch(4, 0))
        self.sensors.append(LimitSwitch(5, 0))
        self.sensors.append(LimitSwitch(6, 0))
        self.sensors.append(LimitSwitch(7, 0))
        self.sensors.append(LimitSwitch(8, 0))
        self.sensors.append(Sonar(1, 0))
        self.sensors.append(Sonar(2, 0))
        
    def updateSensorData(self):
        """
        reads all the sensors and updates internal data
        """
        for sensor in self.sensors:
            sensor.updateValue()
        return {"sensorData":self.sensors}
    
    def getSensor(self, sensorID, sensorType):
        """
        returns the sensor with the given ID and type
        
        sensorType - type of the sensor
        sensorID - id number of the sensor
        """
        for sensor in self.sensors:
            if sensor.sensorType == sensorType and sensor.ID == sensorID:
                return sensor
            
    def readSensorValue(self, sensorID, sensorType):
        """
        returns the currently stored data for the given sensor ID and type
        
        sensorType - type of the sensor
        sensorID - id number of the sensor
        """
        for sensor in self.sensors:
            if sensor.sensorType == sensorType and sensor.ID == sensorID:
                return sensor
            
    def areClawsAttached(self, robotHalf, num_claws=3):
        """
        Determines whether the claws for the given robotHalf are attached
        
        robotHalf - half of the robot to read claws from
        num_claws - the number of claws required to be attached (defaults to 3)
        """
        if(robotHalf == "TOP"):
            claw_sensors = [self.CLAW_TTL, self.CLAW_TTR, self.CLAW_TBL, self.CLAW_TBR]
        else:
            claw_sensors = [self.CLAW_BTL, self.CLAW_BTR, self.CLAW_BBL, self.CLAW_BBR]
            
        num_attached_claws = sum([self.readSensorValue(claw_sensor, "LIMIT_SWITCH")
                                    for claw_sensor in claw_sensors])
        
        return num_attached_claws >= 4 
        
    def isCameraPastEdge(self):
        """
        Test if camera is within limits
        """
        cameraAngle = self.readSensorValue(self.CAMERA, "POTENTIOMETER")
        return cameraAngle > self.CAMERA_ANGLE_MAX or cameraAngle < self.CAMERA_ANGLE_MIN
        
    def isBallYawPastEdge(self):
        """
        Test if ball yaw is within limits
        """
        ballYaw = self.get_sensor(self.BALL_YAW, "POTENTIOMETER")
        return ballYaw> self.BALL_YAW_MAX or ballYaw < self.BALL_YAW_MIN
        
        
    def isBallPitchPastEdge(self):
        """
        Test if ball pitch is within limits
        """
        ballPitch = self.get_sensor(self.BALL_PITCH, "POTENTIOMETER")
        return ballPitch > self.BALL_PITCH_MAX or ballPitch < self.BALL_PITCH_MIN
    
    def isLeadScrewPastEdge(self):
        """
        Test if lead screw is past edge
        """
        return self.isLeadScrewExtended or self.isLeadScrewRetracted
        
    def isLeadScrewExtended(self):
        """
        Test if lead screw is extended
        """
        return self.readSensorValue(self.SCREW_ENCODER, "ENCODER") > self.SCREW_EXTENDED
    
    def isLeadScrewRetracted(self):
        """
        Test if lead screw is retracted
        """
        return self.readSensorValue(self.SCREW_ENCODER, "ENCODER") < self.SCREW_RETRACTED