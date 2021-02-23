package org.calculator.common;

public class Request {

	private final String value;
	private Operations operation;

	public Request(String request) {
		this.value = request;
	}

	public String value(){
		return value;
	}

	public void setOperation(Operations operation){
		this.operation = operation;
	}

	public Operations getOperation(){
		return operation;
	}
}
