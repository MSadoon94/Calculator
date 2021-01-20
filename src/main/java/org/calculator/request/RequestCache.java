package org.calculator.request;

import org.calculator.common.Request;

import java.util.ArrayList;


public class RequestCache{

	private ArrayList<Request> requests = new ArrayList<>();

	public void addRequest(Request in) {
		requests.add(in);
	}

	public Request getNextRequest() {
		return requests.get(0);
	}
}
