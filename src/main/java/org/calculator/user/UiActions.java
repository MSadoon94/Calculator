package org.calculator.user;

import org.calculator.common.Request;
import org.calculator.request.Observer;

public interface UiActions {
	void attachObserver(Observer observer);
	Request getRequest();
	void setResponse(String response);
}
