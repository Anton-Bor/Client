package ru.bor.java.window;

import javax.swing.*;
import java.awt.*;

public class  WindowClientAvtor extends WindowSuper{
		
	private JButton buttonOk = new JButton("Ok");
	private JButton buttonReg = new JButton("Registration");
	private JLabel login = new JLabel("Login: ");
	private JLabel password = new JLabel("Password: ");
	private JTextField loginText = new JTextField(20);
	private JPasswordField passwordText = new JPasswordField(20);

	public void createPanel( Container con ){
		
		con.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		con.setLayout( new GridBagLayout() );
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.NONE;

		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.insets = new Insets(10,0,0,0);
		constraints.anchor = GridBagConstraints.LINE_END;
		con.add( login, constraints );

		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.insets = new Insets(10,10,0,0);
		constraints.anchor = GridBagConstraints.LINE_END;
		con.add( password, constraints );
	
		constraints.gridx = 1;
		constraints.gridy = 0;
		constraints.gridwidth = 2;
		constraints.insets = new Insets(10,0,0,10);
		con.add( loginText, constraints );

		constraints.gridx = 1;
		constraints.gridy = 1;
		constraints.gridwidth = 2;
		constraints.insets = new Insets(10,0,0,10);
		con.add( passwordText, constraints );

		constraints.gridx = 1;
		constraints.gridy = 2;
		constraints.insets = new Insets(0,0,0,0);
		constraints.anchor = GridBagConstraints.LINE_START;
		con.add( buttonReg, constraints );
		
		constraints.gridx = 2;
		constraints.gridy = 2;
		constraints.insets = new Insets(10,100,10,0);
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.ipadx = 50;
		con.add( buttonOk, constraints );
	}

	public void createWindow(){
		createPanel(getFrame().getContentPane());
		setNameWindow("Authorization");
		super.createWindow();
	}

	public JButton getButtonOk(){
		return buttonOk;
	}

	public JButton getButtonReg(){
		return buttonReg;
	}

	public String getLoginText(){
		return loginText.getText();
	}
	
	public String getPasswordText(){
		String a =  new String(passwordText.getPassword());
		return a;
	}	
}