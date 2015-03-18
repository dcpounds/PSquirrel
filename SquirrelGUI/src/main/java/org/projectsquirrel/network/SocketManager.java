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

public class SocketManager {
	private static SocketManager instance = new SocketManager();
	private static Socket socket;
	private static Socket cameraSocket;
	private static PrintWriter out;
	private static BufferedReader in;
	private static InputStream cameraStream;
	private static BufferedReader cameraIn;
	private static String ip;
	private static int mainPort;
	private static int port;
	private static boolean isInitialized;

	private SocketManager() {
	}

	// TODO add errors for uninitialized network
	public static void initialize(String ip, int port)
			throws UnknownHostException, IOException {
		SocketManager.ip = ip;
		SocketManager.port = port;
		isInitialized = true;

		socket = new Socket(ip, port);	
		out = new PrintWriter(socket.getOutputStream(), true);
		in = new BufferedReader(new InputStreamReader(
				socket.getInputStream()));
	}

	public static void close() throws IOException {
		socket.close();
	}

	public static void sendCommandPacket(CommandPacket commandPacket)
			throws NetworkUninitializedException {
		if (!isInitialized) {
			throw new NetworkUninitializedException();
		}
		out.println(commandPacket.toJson());
	}

	public static SensorPacket receiveSensorPacket() throws IOException,
			NetworkUninitializedException {
		if (!isInitialized) {
			throw new NetworkUninitializedException();
		}
		// while(!in.ready());
		return SensorPacket.fromJson(in.readLine());
	}

	public static SocketManager getInstance() {
		return instance;
	}

	public String getIp() {
		return ip;
	}

	public int getMainPort() {
		return mainPort;
	}
	
	public int getCameraPort() {
		return port;
	}


}


