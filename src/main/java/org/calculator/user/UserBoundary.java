package org.calculator.user;

import javax.swing.*;

public interface UserBoundary {
	HistoryPanel historyPanel(JLabel label, UserCache cache);
	TextAppendingPanel textAppendingPanel(JTextArea textArea);
}
