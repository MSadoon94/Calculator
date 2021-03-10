package org.calculator.user;

import javax.swing.*;

public interface Panel {
	JPanel getPanel();
	JTextField textField();
	JButton button(String name);
}
