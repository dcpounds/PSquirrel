import socket
import json
import sys
import time

class NetworkManager:
    """
    Class for sending sensor data, heart beat signals, and camera frames to network
    and receiving commands from network.
    """
    
    def __init__(self):
        """
        Initializes the network manager.
        TODO: parameterize the host and port number
        """
        self.host = '10.5.5.1'
        self.port = 10004
        self.backlog = 5
        self.size = 2048
        self.server = None
        self.client = None
        self.clientAddress = None

        try:
            self.server = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
            self.server.bind((self.host,self.port))
            self.server.listen(self.backlog)
            self.client, self.clientAddress = self.server.accept() 
            print "Connection Successful!"

        except socket.error, (value, message):
            if self.server:
                self.server.close()
            print "Could not open socket: " + message
            sys.exit(1)
        
        

    def close(self):
        """
        Close down the sockets.
        """
        self.client.close()
        self.server.close()

    def sendSensorData(self, sensorData):
        """
        Sends the JSON encoded sensor data packet to the network.
        
        sensorData - sensor data in dictionary of form:
        {"sensorData":[{"sensorType": <type>, 
                        "number": <number>, 
                        "value": <value>},
                       {"sensorType": <type>, 
                        "number": <number>,
                        "value": <value>} ...]}
        """
        data = json.dumps(sensorData)
        self.client.send(data + "\n")
        
    def sendFrame(self, cameraFrame):
        """
        Sends a JSON encoded camera frame packet to the network.
        
        cameraFrame - camera frame data of form:
        {"cameFrame": <encodedFrame>}
        """
        self.client.send(cameraFrame + "\n")
        
    def sendHeartbeatSignal(self, signal):
        """
        Sends a JSON encoded heart beat signal to the network.
        
        signal - signal to be sent of form:
        {"heartbeat": <signal>}
        """

    def receiveCommand(self):
        """
        Receives the JSON encoded command from the network.
        
        returns dictionary of the form:
        {"commandType": <commndType>,
         "command": <command>}
        """
        data = json.loads(self.client.recv(self.size))
        return data
    
    def checkForCommand(self):
        """
        Check to see if there is a drive command ready to be received
        """
        return False
    
    def run(self):
        sensorData = {"sensorData":[{"sensorType":"ENCODER", 
                                     "number":0, 
                                     "value":100},
                                    {"sensorType":"POTENTIOMETER", 
                                     "number":0,
                                     "value":100}]}
        while 1:
            data = self.receiveCommand()
            time.sleep(0.01)
            if data:
                print data
                self.sendSensorData(sensorData)
        
if __name__ == "__main__":
    """main loop that just echoes sensor data back for received commands"""
    sensorData = {"sensorData":[{"sensorType":"ENCODER", 
                                 "number":0, 
                                 "value":100},
                                {"sensorType":"POTENTIOMETER", 
                                 "number":0,
                                 "value":100}]}
    s = NetworkManager()
    while 1:
        data = s.receiveCommand()
        time.sleep(0.01)
        if data:
            print data
            s.sendSensorData(sensorData)

#{"sensorData":[{"type":"ENCODER","number":0,"value":0},{"type":"ENCODER","number":0,"value":0},{"type":"POTENTIOMETER","number":0,"value":0}]}
