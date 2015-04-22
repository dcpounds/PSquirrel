from src.sensors.sensor import Sensor

class SPISensor(Sensor):
    """
    Class representing a potentiometer.
    """
    
    def __init__(self, ID, adcNum, spiNum, spi):
        """
        Initialize the sensor

        adcNum - port connected to on the adc
        spiNum - spi device number (which adc [0 or 1])
        spi - spi interface
        """
        super(SPISensor, self).__init__(adcNum)
        self.adcNum = adcNum
        self.spiNum = spiNum
        self.spi = spi
        
    def readValue(self):
        """
        Read the sensor.
        """
        self.spi.open(0, self.spiNum)
        
        r = self.spi.xfer2([1, 8 + self.adcNum << 4, 0])
        result = ((r[1] & 3) << 8) + r[2]
        
        self.spi.close()
        
        return result