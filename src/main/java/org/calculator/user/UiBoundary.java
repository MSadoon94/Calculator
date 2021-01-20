package org.calculator.user;

import org.calculator.common.Request;
import org.calculator.request.Observer;

public interface UiBoundary {
	void attachObserver(Observer observer);
	Request getRequest();
	void setResponse(String response);
}
