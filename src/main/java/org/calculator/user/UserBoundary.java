package org.calculator.user;

import org.calculator.processing.Invoker;

import javax.swing.*;

public interface UserBoundary {
	Ui gui(JFrame jframe);
	UserCache userCache();
	HistoryPanel historyPanel(JLabel label, UserCache cache);
	Observer historyObserver(AccessoryPanel inputHistory, AccessoryPanel answerHistory);
	AppenderPanel textAppendingPanel(Ui gui);
	FunctionsPanel textFunctionPanel(Ui gui, Invoker answerInvoker, Observer historyObserver);
	Panel2 entryPanel(Ui gui, Invoker answerInvoker, Observer historyObserver);
}
