package org.calculator.user;

import javax.swing.*;


public abstract class Panel extends JPanel {
	protected  JTextArea textArea;

	protected void setTextArea(JTextArea aTextArea){
		textArea = aTextArea;
	}
}
