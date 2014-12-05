package org.projectsquirrel.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.DatagramSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import org.projectsquirrel.models.CommandPacket;
import org.projectsquirrel.models.SensorPacket;

public class NetworkManager {
	private static NetworkManager instance = new NetworkManager();
	private static Socket mainSocket;
	private static DatagramSocket cameraSocket;
	private static PrintWriter mainOut;
	private static BufferedReader mainIn;
	private static String ip;
	private static int mainPort;
	private static boolean isInitialized;
	
	private NetworkManager(){
	}
	
	//TODO add errors for uninitialized network
	public static void initialize(String ip, int mainPort) throws UnknownHostException, IOException{
		NetworkManager.ip = ip;
		NetworkManager.mainPort = mainPort;
		//NetworkManager.cameraPort = cameraPort;
		isInitialized = true;
        
		mainSocket = new Socket(ip, mainPort);
		//cameraSocket = new DatagramSocket(ip, cameraPort);
		mainOut = new PrintWriter(mainSocket.getOutputStream(), true);
		mainIn = new BufferedReader(new InputStreamReader(mainSocket.getInputStream()));
	}
	
	public static void close() throws IOException{
		mainSocket.close();
	}
	
	public static void sendCommandPacket(CommandPacket commandPacket) throws NetworkUninitializedException{
		if(!isInitialized){
			throw new NetworkUninitializedException();
		}
		mainOut.println(commandPacket.toJson());
	}
	
	public static SensorPacket receiveSensorPacket() throws IOException, NetworkUninitializedException{
		if(!isInitialized){
			throw new NetworkUninitializedException();
		}
		//while(!in.ready());
		return SensorPacket.fromJson(mainIn.readLine());
	}
	
	public static NetworkManager getInstance(){
		return instance;
	}
	
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) throws IOException {
		close();
		this.ip = ip;
		initialize(ip, mainPort);
	}

	public int getPort() {
		return mainPort;
	}

	public void setPort(int port) throws IOException {
		close();
		this.mainPort = port;;
		initialize(ip, port);
	}
	

}
