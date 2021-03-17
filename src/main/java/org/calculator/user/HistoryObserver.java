package org.calculator.user;

import org.calculator.common.Request;

class HistoryObserver implements Observer {
	private AccessoryPanel inputHistory, answerHistory;

	public HistoryObserver(AccessoryPanel inputHistory, AccessoryPanel answerHistory){
		this.inputHistory = inputHistory;
		this.answerHistory = answerHistory;
	}

	public void update(Request request, Request answer) {
		inputHistory.addRequest(request);
		answerHistory.addRequest(answer);
	}
}
