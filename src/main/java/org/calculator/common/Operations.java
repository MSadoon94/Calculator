package org.calculator.common;

public enum Operations {
	SINGLE_VALUE("SINGLE_VALUE"),
	ADDITION("+"),
	SUBTRACTION("-"),
	MULTIPLICATION("*"),
	DIVISION("/"),
	PERCENTAGE("PERCENTAGE");

	private final String operation;
	Operations(String operation) {
		this.operation = operation;
	}
	public String symbol(){
		return operation;
	}
	public static Operations[] arithmeticOps(){
		return new Operations[]{ADDITION, SUBTRACTION, MULTIPLICATION, DIVISION};
	}
	public static Operations[] functionOps(){
		return new Operations[]{SINGLE_VALUE, PERCENTAGE};
	}
}
