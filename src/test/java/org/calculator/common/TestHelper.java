package org.calculator.common;

public enum TestHelper {
	SINGLE_VALUE("2.0", new double[]{2.0}, "2.0"),
	ADDITION("2+2", new double[]{2.0, 2.0}, "4.0"),
	SUBTRACTION("4-2", new double[]{4.0, 2.0}, "2.0"),
	MULTIPLICATION("2*2", new double[]{2.0, 2.0}, "4.0");

	private final String input;
	private final double[] doubles;
	private final String answer;

	TestHelper(String input, double[] doubles, String answer) {
		this.input = input;
		this.doubles = doubles;
		this.answer = answer;
	}

	public String input(){
		return input;
	}
	public String answer(){
		return answer;
	}
	public double[] doubles(){return doubles;}
}
