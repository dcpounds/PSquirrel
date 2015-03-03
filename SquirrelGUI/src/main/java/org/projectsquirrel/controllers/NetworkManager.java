package org.projectsquirrel.controllers;

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

public class NetworkManager {
	private static NetworkManager instance = new NetworkManager();
	private static Socket mainSocket;
	private static Socket cameraSocket;
	private static PrintWriter mainOut;
	private static BufferedReader mainIn;
	private static InputStream cameraStream;
	private static BufferedReader cameraIn;
	private static String ip;
	private static int mainPort;
	private static int cameraPort;
	private static boolean isInitialized;

	private NetworkManager() {
	}

	// TODO add errors for uninitialized network
	public static void initialize(String ip, int mainPort, int cameraPort)
			throws UnknownHostException, IOException {
		NetworkManager.ip = ip;
		NetworkManager.mainPort = mainPort;
		NetworkManager.cameraPort = cameraPort;
		isInitialized = true;

		mainSocket = new Socket(ip, mainPort);	
		cameraSocket = new Socket(ip, cameraPort);
		mainOut = new PrintWriter(mainSocket.getOutputStream(), true);
		mainIn = new BufferedReader(new InputStreamReader(
				mainSocket.getInputStream()));
		
		cameraStream = new DataInputStream(cameraSocket.getInputStream());
		cameraIn = new BufferedReader(new InputStreamReader(
				cameraStream));
	}

	public static void close() throws IOException {
		mainSocket.close();
		cameraSocket.close();
	}

	public static void sendCommandPacket(CommandPacket commandPacket)
			throws NetworkUninitializedException {
		if (!isInitialized) {
			throw new NetworkUninitializedException();
		}
		mainOut.println(commandPacket.toJson());
	}

	public static SensorPacket receiveSensorPacket() throws IOException,
			NetworkUninitializedException {
		if (!isInitialized) {
			throw new NetworkUninitializedException();
		}
		// while(!in.ready());
		return SensorPacket.fromJson(mainIn.readLine());
	}
	
	public static BufferedImage receiveCameraPacket() throws IOException,
	NetworkUninitializedException {
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


	public static NetworkManager getInstance() {
		return instance;
	}

	public String getIp() {
		return ip;
	}

	public int getMainPort() {
		return mainPort;
	}
	
	public int getCameraPort() {
		return cameraPort;
	}


}


