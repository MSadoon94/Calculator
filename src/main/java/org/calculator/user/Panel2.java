package org.calculator.user;

import javax.swing.*;

public abstract class Panel2 extends JPanel {
	protected JTextArea textArea;

	public void attachTextArea(JTextArea aTextArea){
		textArea = aTextArea;
	}

	protected abstract JPanel panel();
}
