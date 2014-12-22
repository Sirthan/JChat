package jchat.networking;

import java.io.IOException;
import java.net.Socket;

import jchat.GUI.MainGui;

public class MessageTransmitter extends Thread{
	String message, hostname;
	int port;
	MainGui gui;
	
	public MessageTransmitter(String message, String hostname, int port, MainGui gui){
		this.message = message;
		this.hostname = hostname;
		this.port = port;
		this.gui = gui;
	}
	
	@Override 
	public void run(){
		try {
			gui.write("You - " + message);
			Socket s = new Socket(hostname, port);
			s.getOutputStream().write(("Them - " + message).getBytes());
			s.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
