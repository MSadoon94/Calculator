package org.calculator.user;

import org.calculator.request.Observer;

import javax.swing.*;

public class UserController implements UserBoundary {

	public HistoryPanel historyPanel(JLabel label, UserCache cache) {
		return new HistoryPanel(label, cache);
	}

	public TextAppendingPanel textAppendingPanel(JTextArea textArea){
		return new TextAppendingPanel(textArea);
	}

	public TextFunctionPanel textFunctionPanel(Gui gui, Observer observer) {
		return new TextFunctionPanel(gui, observer);
	}
}
