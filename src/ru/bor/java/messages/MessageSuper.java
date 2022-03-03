package ru.bor.java.messages;

import java.io.ObjectOutputStream;
import java.io.Serializable;

import ru.bor.java.window.WindowClient;


public abstract class MessageSuper {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String idMessage;
	private String textMessage;
	private String nikUser;

	public MessageSuper( String idMessage, String nikUser, String textMessage) {
		setIdMessage(idMessage);
		setNikUser(nikUser);
		setTextMessage(textMessage);
	}
	
	public void playMessage( WindowClient windowClient){}

	public String getIdMessage() {
		return idMessage;
	}

	public void setIdMessage(String idMessage) {
		this.idMessage = idMessage;
	}

	public String getTextMessage() {
		return textMessage;
	}

	public void setTextMessage(String textMessage) {
		this.textMessage = textMessage;
	}

	public String getNikUser() {
		return nikUser;
	}

	public void setNikUser(String nikUser) {
		this.nikUser = nikUser;
	}
}
