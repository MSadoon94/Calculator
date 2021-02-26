package org.calculator.common;

public class Request {

	private final String input;
	private Operations operation;
	private String[] values;
	private String innerGroup;

	public Request(String request) {
		this.input = request;
	}

	public String input(){
		return input;
	}

	public void setOperation(Operations operation){
		this.operation = operation;
	}

	public Operations getOperation(){
		return operation;
	}

	public void setValues(String[] values){
		this.values = values;
	}

	public String[] getValues(){
		return values;
	}

	public void setInnerGroup(String innerGroup){
		this.innerGroup = innerGroup;
	}

	public String getInnerGroup(){
		return innerGroup;
	}
}
