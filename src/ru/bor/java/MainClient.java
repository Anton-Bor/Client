package ru.bor.java;

import ru.bor.java.reg_avtor_chat.Authorization;
import ru.bor.java.reg_avtor_chat.ChatClient;
import ru.bor.java.window.*;

public class MainClient {

	public static void main(String[] args) {
		Authorization a = new Authorization();
		a.doAvtorisation();
		
		//WindowClient windowClient = new WindowClient();
		//windowClient.createWindow();;
	}

}
