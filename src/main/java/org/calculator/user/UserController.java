package org.calculator.user;

import org.calculator.processing.Invoker;
import org.calculator.verification.Verifier;

import javax.swing.*;

public class UserController implements UserBoundary {

	public Ui gui(JFrame frame) {
		return new Gui(frame);
	}

	public UserCache userCache() {
		return new HistoryCache();
	}

	public HistoryPanel historyPanel(JLabel label, UserCache cache) {
		return new HistoryPanel(label, cache);
	}

	public Observer historyObserver(AccessoryPanel inputHistory, AccessoryPanel answerHistory) {
		return new HistoryObserver(inputHistory, answerHistory);
	}

	public Panel functionsPanel(Verifier aErrorMessenger, Invoker answerInvoker, Observer historyObserver) {
		return new FunctionsPanel(aErrorMessenger, answerInvoker, historyObserver);
	}

	public CompositePanel entryPanel(Verifier aErrorMessenger, Invoker answerInvoker, Observer historyObserver) {
		return new EntryPanel( aErrorMessenger,answerInvoker, historyObserver);
	}
}
