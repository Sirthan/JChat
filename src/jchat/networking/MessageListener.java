package jchat.networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;

public class MessageListener extends Thread{
	
	ServerSocket server;
	int port;
	WriteableGui gui;
	
	public MessageListener(WriteableGui gui, int port){
		this.port = port;
		this.gui = gui;
		try {
			this.server = new ServerSocket(port);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public MessageListener(){
		this.port = 8877;
		try {
			this.server = new ServerSocket(port);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run(){
		Socket clientSocket;
		try {
			while((clientSocket = server.accept()) != null){
				InputStream IS = clientSocket.getInputStream();
				BufferedReader BR = new BufferedReader(new InputStreamReader(IS));
				String line = BR.readLine();
				if(line != null)gui.write(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
