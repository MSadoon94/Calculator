package org.calculator.user;

import org.calculator.common.Request;

public interface Observer {
	void update(Request request, Request answer);
}
