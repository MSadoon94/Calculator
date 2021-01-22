package org.calculator.common;

public enum Operations {
	ADDITION("+"),
	SUBTRACTION("-"),
	MULTIPLICATION("*");
	private final String operation;
	Operations(String operation) {
		this.operation = operation;
	}
	public String symbol(){
		return operation;
	}
}
