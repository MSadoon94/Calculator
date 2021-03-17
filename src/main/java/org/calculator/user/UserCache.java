package org.calculator.user;

import org.calculator.common.Request;

public interface UserCache {
	void addRequest(Request request);
	boolean hasNext();
	boolean hasPrevious();
	Request next();
	Request previous();
}
