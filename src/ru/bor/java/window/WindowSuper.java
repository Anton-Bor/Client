package ru.bor.java.window;

import javax.swing.*;

public abstract class WindowSuper{

	private String nameWindow;	
	public JFrame frame = new JFrame();

	void setNameWindow( String name ){
		nameWindow = name;
	}	
	
	String getNameWindow(){
		return nameWindow;
	}
	
	void createWindow(){
		frame.setTitle( getNameWindow() );
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);	
		frame.setResizable(false);
		frame.pack();
		frame.setVisible( true );
		frame.setLocationRelativeTo(null);
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
		
}