package ru.bor.java.messages;

import ru.bor.java.window.WindowClient;

public class MessageSuperTextMessage extends MessageSuper {
	public MessageSuperTextMessage(String idMessage, String nikUser, String textMessage) {
		super(idMessage, nikUser, textMessage);
		// TODO Auto-generated constructor stub
	}
	
	public void playMessage( WindowClient windowClient ) {
		MessagesForLan messages = new MessagesForLan(getIdMessage(), getNikUser(), getTextMessage());
		windowClient.getMessagesArea().append( messages.getTimeMessage() + " " + messages.getNikUser() + " " + messages.getTextMessage() + "\n");
	}
}
