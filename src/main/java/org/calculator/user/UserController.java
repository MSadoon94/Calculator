package org.calculator.user;

import javax.swing.*;

public class UserController implements UserBoundary {

	public HistoryPanel historyPanel(JLabel label, UserCache cache) {
		return new HistoryPanel(label, cache);
	}
}
