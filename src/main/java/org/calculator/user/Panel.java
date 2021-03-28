package org.calculator.user;

import javax.swing.*;


public abstract class Panel extends JPanel {
	protected JTextField text;

	protected void setText(JTextField textField){
		text = textField;
	}
}
