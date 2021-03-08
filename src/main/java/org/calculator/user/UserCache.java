package org.calculator.user;

import org.calculator.common.Request;

public interface UserCache {
	void addRequest(Request request);
	Request getRequest(int hashCode);
}
