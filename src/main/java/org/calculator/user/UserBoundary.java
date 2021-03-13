package org.calculator.user;

import org.calculator.request.Observer;

import javax.swing.*;

public interface UserBoundary {
	HistoryPanel historyPanel(JLabel label, UserCache cache);
	TextAppendingPanel textAppendingPanel(JTextArea textArea);
	TextFunctionPanel textFunctionPanel(Gui gui, Observer observer);
}
