package org.calculator.user;

import javax.swing.*;

public interface Ui {
	JTextArea textArea();
	void setPanels(Panel panel);
	void addPanelsToMainPanel();
	void inputErrorMessage(String invalidInput);
	void decimalPositionError();
}
