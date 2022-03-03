package ru.bor.java.network;

import java.net.*;

import ru.bor.java.messages.MessagesForLan;

import java.io.*;

public class NetWithServer{
	private Socket socketChat;
	private ObjectOutputStream writer;
	private ObjectInputStream reader;
	private String localHost = "127.0.0.1";
	private int port = 4240;
	
	public NetWithServer(){
		setUpNetworkServer();
	}

	private void setUpNetworkServer(){

		try{
			socketChat = new Socket( localHost, port );
			writer = new ObjectOutputStream( socketChat.getOutputStream());
			reader = new ObjectInputStream(socketChat.getInputStream());
		}
		
		catch(Exception ex){
			ex.printStackTrace();
		}	
	}

	public void sendToServer( MessagesForLan message ){
		try{
			writer.writeObject(message);
			writer.reset();
		}
		catch( Exception ex ){
			ex.printStackTrace();
		}
	}

	public MessagesForLan listenFromServer(){
		MessagesForLan message = null;
		try{
			message = (MessagesForLan)reader.readObject();
		}
		
		catch( Exception ex ){
			ex.printStackTrace();
		}
		return message;
	}

	
}
