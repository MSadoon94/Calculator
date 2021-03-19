package org.calculator.user;

import javax.swing.*;
import java.awt.*;

public abstract class Panel2 extends JPanel {
	protected JTextArea textArea = new JTextArea();
	protected JPanel panel = new JPanel(new GridLayout(3, 3));

	public JPanel panel(){return panel;}

	protected void setPanel(){
		panel.add(textArea);
		panel.add(this);
	}
}
