package org.calculator.common;

public enum Operations {
	SINGLE_VALUE("SINGLE_VALUE"),
	ADDITION("+"),
	SUBTRACTION("-"),
	MULTIPLICATION("*"),
	DIVISION("/"),
	PERCENTAGE("%");

	private final String operation;
	Operations(String operation) {
		this.operation = operation;
	}
	public String symbol(){
		return operation;
	}
	public static String[] arithmeticSymbols(){
		return new String[]{ADDITION.symbol(), SUBTRACTION.symbol(), MULTIPLICATION.symbol(), DIVISION.symbol()};
	}
}
