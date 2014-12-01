package org.projectsquirrel.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import org.projectsquirrel.models.CommandPacket;
import org.projectsquirrel.models.SensorPacket;

public class NetworkManager {
	private static NetworkManager instance = new NetworkManager();
	private static Socket socket;
	private static PrintWriter out;
	private static BufferedReader in;
	private static String ip;
	private static int port;
	private static boolean isInitialized;
	
	private NetworkManager(){
	}
	
	//TODO add errors for unitialized network
	public static void initialize(String ip, int port) throws UnknownHostException, IOException{
		NetworkManager.ip = ip;
		NetworkManager.port = port;
		isInitialized = true;
        
		socket = new Socket(ip, port);
		out = new PrintWriter(socket.getOutputStream(), true);
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	}
	
	public static void close() throws IOException{
		socket.close();
	}
	
	public static void sendCommandPacket(CommandPacket commandPacket) throws NetworkUninitializedException{
		if(!isInitialized){
			throw new NetworkUninitializedException();
		}
		System.out.print(commandPacket.toJson());
	}
	
	public static SensorPacket receiveSensorPacket(SensorPacket sensorPacket) throws IOException, NetworkUninitializedException{
		if(!isInitialized){
			throw new NetworkUninitializedException();
		}
		return SensorPacket.fromJson(in.readLine());
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
		initialize(ip, port);
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) throws IOException {
		close();
		this.port = port;;
		initialize(ip, port);
	}
	

}
