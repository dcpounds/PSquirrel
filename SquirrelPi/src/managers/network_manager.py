import socket
import json
import sys
import time

class NetworkManager:
    """
    Class for sending sensor data, heart beat signals, and camera frames to network
    and receiving commands from network.
    """
    
    def __init__(self, host, port, size):
        """
        Initializes the network manager.
        TODO: parameterize the host and port number
        """
        self.host = host
        self.port = port
        self.backlog = 5
        self.size = size
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
        

                #{"sensorData":[{"type":"ENCODER","number":0,"value":0},{"type":"ENCODER","number":0,"value":0},{"type":"POTENTIOMETER","number":0,"value":0}]}
