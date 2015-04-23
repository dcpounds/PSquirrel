import math

def main():

    height = 1.25
    length = 3.6
    pulleyRadius = 0.375
    
    value = 5
    value = math.radians(value)

    angle1 = (height*(math.cos(value/2.0) - 1) + length*(math.sin(value/2.0)))/pulleyRadius
    angle2 = -(height*(math.cos(value/2.0) - 1) - length*(math.sin(value/2.0)))/pulleyRadius
    
    print math.degrees(angle1)
    print math.degrees(angle2)
    
if __name__ == '__main__':
    main()