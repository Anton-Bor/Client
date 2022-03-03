package ru.bor.java.reg_avtor_chat;

import javax.swing.*;

import ru.bor.java.messages.MessagesForLan;
import ru.bor.java.network.NetWithServer;
import ru.bor.java.window.WindowClientAvtor;
import ru.bor.java.window.WindowClientReg;

import java.awt.event.*;

public class Authorization{
	private WindowClientAvtor windowClientAvtor;
	private WindowClientReg windowClientReg;
	private NetWithServer nWS = new NetWithServer();
	private MessagesForLan outputMessage = null;
	private MessagesForLan inputMessage = null;
	private boolean serviceMessage = false;
	private Thread forWindowReg;
	private Thread forWindowAvtor;
	
	public synchronized MessagesForLan getOutputMessage() {
		return outputMessage;
	}
	
	public synchronized MessagesForLan getInputMessage() {
		return inputMessage;
	}
	public Authorization() {
		Thread sendToServer = new Thread(new SendToServer());
		sendToServer.start();
		Thread listenFromServer = new Thread(new ListenFromServer());
		listenFromServer.start();
	}
	
	public class SendToServer implements Runnable {
		public void run() {
			while(!isServiceMessage()) {
				while(getOutputMessage() == null) {
				}
				nWS.sendToServer(getOutputMessage());
				outputMessage = null;
			}
		}
	}

	public class ListenFromServer implements Runnable {
		@Override
		public void run() {
			do{
			inputMessage = nWS.listenFromServer();
			}while(!inputMessage.getTextMessage().equals("Authorization_is_good" ));
		}

	}

	

	public void doAvtorisation(){
		forWindowAvtor = new Thread (new ForWindowAvtor());
		forWindowAvtor.start();
		
	}
	
	public class ForWindowAvtor implements Runnable {
		@Override
		public void run() {
			windowClientAvtor = new WindowClientAvtor();
			windowClientAvtor.createWindow();
			windowClientAvtor.getButtonOk().addActionListener(new ButtonOkListenerForAvtorisation());
			windowClientAvtor.getButtonReg().addActionListener(new ButtonRegListenerForAvtorisation());
		}
	}

	class ButtonRegListenerForAvtorisation implements ActionListener{
		public void actionPerformed( ActionEvent e){
			doRegistration();
		}
	}
	
	void doRegistration(){
		forWindowReg = new Thread (new ForWindowReg());
		forWindowReg.start();	
	}

	
	public class ForWindowReg implements Runnable {
		@Override
		public void run() {
			windowClientReg = new WindowClientReg();
			windowClientReg.createWindow();
			windowClientReg.getButtonOk().addActionListener(new ButtonOkListenerForRegistration());
		}
	}
	

	class ButtonOkListenerForAvtorisation implements ActionListener{
		public void actionPerformed( ActionEvent e){
			if(windowClientAvtor.getLoginText().equals("")||windowClientAvtor.getPasswordText().equals("")){
				getDialog("All fields must be filled in!");
			}
			else{
				outputMessage = new MessagesForLan("@001Login/PassAvtor", windowClientAvtor.getLoginText(), windowClientAvtor.getLoginText() + "///" + windowClientAvtor.getPasswordText());

				while(getInputMessage() == null) {}
				
				if((getInputMessage().getTextMessage()).equals("Authorization_is_good")){
					inputMessage=null;
					serviceMessage = true;
					ChatClient chatClient = new ChatClient(nWS, windowClientAvtor.getLoginText() );
					windowClientAvtor.frame.dispose();
					chatClient.clientStart();
					forWindowAvtor.interrupt();
				}
				else {
					getDialog("Invalid login or password!");
					inputMessage=null;
				}
			}
		}

	}
	
	void getDialog(String a ){
		JFrame frameMessage = new JFrame();
		JOptionPane.showMessageDialog(frameMessage, a);
	}
	

	

	public synchronized boolean  isServiceMessage() {
		return serviceMessage;
	}

	class ButtonOkListenerForRegistration implements ActionListener{
		public void actionPerformed( ActionEvent e ){
			if(windowClientReg.getLoginText().equals("") || windowClientReg.getPasswordText().equals("")){
				getDialog("All fields must be filled in!");
			}
			else{
				if(!(windowClientReg.getPasswordText().equals(windowClientReg.getRepPasswordText()))){
					getDialog("Passwords don't match!");
				}
				else{
					outputMessage = new MessagesForLan("@002Login/PassReg", windowClientReg.getLoginText(), windowClientReg.getLoginText() + "///" + windowClientReg.getPasswordText());

					while(getInputMessage() == null) {}
					
					if((getInputMessage().getTextMessage()).equals("Registration_is_good")){
						getDialog("The user " + windowClientReg.getLoginText() + " has been successfully registered");
						inputMessage=null;
						windowClientReg.frame.dispose();
					}
					else {
						getDialog("User " + windowClientReg.getLoginText() + " is registrated");
						inputMessage=null;
					}
				}
			}	
		}
	}
}