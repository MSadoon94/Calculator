package org.calculator.user;

import org.calculator.processing.Invoker;

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

	public AppenderPanel textAppendingPanel(Ui gui){
		return new AppenderPanel(gui);
	}

	public FunctionsPanel textFunctionPanel(Ui gui, Invoker answerInvoker, Observer observer) {
		return new FunctionsPanel(gui, answerInvoker, observer);
	}

	public Panel2 entryPanel(Ui gui, Invoker answerInvoker, Observer historyObserver) {
		return new EntryPanel(gui, answerInvoker, historyObserver);
	}
}
