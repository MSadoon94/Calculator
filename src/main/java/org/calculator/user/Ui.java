package org.calculator.user;

import javax.swing.*;

public interface Ui {
	void addPanels(Panel inputHistory, Panel answerHistory, CompositePanel entryPanel);
	void inputErrorMessage(String invalidInput);
	void decimalPositionError();
}
