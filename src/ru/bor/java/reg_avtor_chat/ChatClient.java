package ru.bor.java.reg_avtor_chat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ru.bor.java.messages.DispatcherOfMessages;
import ru.bor.java.messages.MessageSuper;
import ru.bor.java.messages.MessagesForLan;
import ru.bor.java.network.NetWithServer;
import ru.bor.java.reg_avtor_chat.ChatClient.ButtonSend;
import ru.bor.java.reg_avtor_chat.ChatClient.ButtonUsers;
import ru.bor.java.window.WindowClient;

public class ChatClient {
	private WindowClient windowClient;
	private NetWithServer nWS;
	private String login;
	private MessagesForLan outputMessage = null;
	private MessagesForLan inputMessage = null;
	private boolean serviceMessage = false;
	
	
	public class ButtonUsers implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

		}

	}

	public class ButtonSend implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			outputMessage = new MessagesForLan("@003TextMessage", getLogin() , windowClient.getMessageText());

			windowClient.getMessageField().setText("");
			//windowClient.frame.
			//outputMessage.printMessage();
		}

	}
	
	public ChatClient(NetWithServer nWS, String login ) {
		this.nWS = nWS;
		this.login = login;
		outputMessage = new MessagesForLan("@001Login/PassAvtor", getLogin(), "User is loaded");
	}
	
	public class SendToServer implements Runnable {
		@Override
		public void run() {
			while(!isServiceMessage()) {
				while(getOutputMessage() == null) {
				}
				//outputMessage.printMessage();
				nWS.sendToServer(getOutputMessage());
				outputMessage = null;
			}
		}
		
	}

	public synchronized MessagesForLan getOutputMessage() {
		return outputMessage;
	}
	
	public synchronized MessagesForLan getInputMessage() {
		return inputMessage;
	}
	
	public class ListenFromServer implements Runnable {
		@Override
		public void run() {
			while(!isServiceMessage()) {
			//inputMessage = nWS.listenFromServer();
			MessagesForLan message = nWS.listenFromServer();
			message.printMessage();
			DispatcherOfMessages dispatcher = new DispatcherOfMessages(message);
			MessageSuper messageSuper = dispatcher.initMessage();
			messageSuper.playMessage(windowClient);
			//getInputMessage().printMessage();
			//inputMessage.printMessage();
			}
		}
	}

	public class ForWindow implements Runnable {
		public void run() {
			windowClient = new WindowClient();
			windowClient.createWindow();
			windowClient.getButtonSend().addActionListener(new ButtonSend());
			windowClient.getButtonUsers().addActionListener(new ButtonUsers());
			Thread listenFromServer = new Thread(new ListenFromServer());
			Thread sendToServer = new Thread(new SendToServer());
			sendToServer.start();
			listenFromServer.start();
		}

	}
	
	public void clientStart() {
		Thread forWindow = new Thread(new ForWindow());
		forWindow.start();
	}

	public synchronized boolean isServiceMessage() {
		return serviceMessage;
	}

	public String getLogin() {
		return login;
	}
}
