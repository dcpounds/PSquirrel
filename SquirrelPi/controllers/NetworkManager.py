import socket
import json
import sys
import time

class NetworkManager:
    def __init__(self):
        self.host = 'localhost'
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

        except socket.error, (value,message):
            if self.server:
                self.server.close()
            print "Could not open socket: " + message
            sys.exit(1)

    def close(self):
        client.close()
        server.close()

    def sendSensorData(self, sensorData):
        data = json.dumps(sensorData)
        self.client.send(data + "\n")

    def receiveCommand(self):
        data = self.client.recv(self.size)
        return data
    
if __name__ == "__main__":
    sensorData = {"sensorData":[{"type":"ENCODER", 
                                 "number":0, 
                                 "value":100},
                                {"type":"POTENTIOMETER", 
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
