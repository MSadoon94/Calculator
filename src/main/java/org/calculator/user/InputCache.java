package org.calculator.user;

import org.calculator.common.Request;

import java.util.HashMap;

public class InputCache implements UserCache{
	private HashMap<Integer, Request> requests = new HashMap<>();
	public Request getRequest(int hashCode) {
		return requests.get(hashCode);
	}

	public void addRequest(Request request) {
		requests.put(request.hashCode(), request);
	}
}
