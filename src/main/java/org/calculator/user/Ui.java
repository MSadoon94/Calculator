package org.calculator.user;

import javax.swing.*;

public interface Ui {
	void setPanels(Panel panel);
	void addPanelsToMainPanel();
	void inputErrorMessage(String invalidInput);
	void decimalPositionError();
}
