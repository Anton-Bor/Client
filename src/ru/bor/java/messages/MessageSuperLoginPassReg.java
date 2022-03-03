package ru.bor.java.messages;


public class MessageSuperLoginPassReg extends MessageSuper{

	public MessageSuperLoginPassReg(String idMessage, String nikUser, String textMessage) {
		super(idMessage, nikUser, textMessage);
		// TODO Auto-generated constructor stub
	}

	public void playMessage() {
		if(getTextMessage().equals("Registration_is_good")) {
			
		}		
	}

}
