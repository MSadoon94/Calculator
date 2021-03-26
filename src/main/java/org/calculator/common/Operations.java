package org.calculator.common;

public enum Operations {
	SINGLE_VALUE("SINGLE_VALUE"),
	ADDITION("+"),
	SUBTRACTION("-"),
	MULTIPLICATION("*"),
	DIVISION("/"),
	PERCENTAGE("%"),
	EXPONENT("^"),
	ROOT("√");

	private final String symbol;
	Operations(String symbol) {
		this.symbol = symbol;
	}
	public String symbol(){
		return symbol;
	}
	public static Operations[] unaryOps(){
		return new Operations[]{SINGLE_VALUE, PERCENTAGE};
	}
}
