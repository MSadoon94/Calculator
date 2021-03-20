package org.calculator.user;

import org.calculator.processing.Invoker;
import org.calculator.verification.Verifier;

import javax.swing.*;

public interface UserBoundary {
	Ui gui(JFrame jframe);
	UserCache userCache();
	HistoryPanel historyPanel(JLabel label, UserCache cache);
	Observer historyObserver(AccessoryPanel inputHistory, AccessoryPanel answerHistory);
	Panel functionsPanel(Verifier aErrorMessenger, Invoker answerInvoker, Observer historyObserver);
	CompositePanel entryPanel(Verifier aErrorMessenger, Invoker answerInvoker, Observer historyObserver);
}
