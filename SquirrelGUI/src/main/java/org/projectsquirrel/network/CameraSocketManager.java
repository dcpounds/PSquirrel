package org.projectsquirrel.network;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.DatagramSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Base64;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import org.projectsquirrel.models.CommandPacket;
import org.projectsquirrel.models.SensorPacket;

/**
 * @author dave
 *
 * Singleton for maintaining the socket for the camera
 * Only camera images are received; nothing is sent
 *
 */
public class CameraSocketManager {
	private static CameraSocketManager instance = new CameraSocketManager();
	private static Socket cameraSocket;
	private static InputStream cameraStream;
	private static BufferedReader cameraIn;
	private static String ip;
	private static int cameraPort;
	private static boolean isInitialized;

	/**
	 * constructor is private because class is a singleton
	 */
	private CameraSocketManager() {
	}

	// TODO add errors for uninitialized network
	/**
	 * Initialize the socket to the given ip and port
	 * @param ip - ip of the RasPi
	 * @param cameraPort - port of the RasPi to connect to for the camera
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	public static void initialize(String ip, int cameraPort)
			throws UnknownHostException, IOException {
		CameraSocketManager.ip = ip;
		CameraSocketManager.cameraPort = cameraPort;
		isInitialized = true;

		cameraSocket = new Socket(ip, cameraPort);
		
		cameraStream = new DataInputStream(cameraSocket.getInputStream());
		cameraIn = new BufferedReader(new InputStreamReader(
				cameraStream));
	}

	/**
	 * closes the camera socket
	 * @throws IOException
	 */
	public static void close() throws IOException {
		cameraSocket.close();
	}
	
	/**
	 * receives the next image from the raspi. 
	 * Note the size is received first and then the corresponding number of bytes is received
	 * @return - the image in the form of a buffered image
	 * @throws IOException
	 * @throws NetworkUninitializedException
	 */
	public static BufferedImage receiveCameraPacket() throws IOException, NetworkUninitializedException {
		if (!isInitialized) {
			throw new NetworkUninitializedException();
		} 

		// Read the prefixed sizeou
		byte [] sizeBuf = new byte[10];
		cameraStream.read(sizeBuf);
        int size = Integer.parseInt(new String(sizeBuf, Charset.forName("UTF-8")));
        
        byte[] imageBuf = new byte[size];
        int bytesRead = 0;
        while(bytesRead < size){
        	bytesRead += cameraStream.read(imageBuf, bytesRead, size-bytesRead);
        }
		BufferedImage image = ImageIO.read(new ByteArrayInputStream(imageBuf));
		
		return image;
	}

	/**
	 * get the one instance of the controller
	 * @return
	 */
	public static CameraSocketManager getInstance() {
		return instance;
	}

	/**
	 * get the ip address
	 * @return
	 */
	public String getIp() {
		return ip;
	}
	
	/**
	 * get the camera port
	 * @return
	 */
	public int getCameraPort() {
		return cameraPort;
	}


}


