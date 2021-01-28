package org.calculator.common;

import java.math.BigDecimal;

public enum TestHelper {
	SINGLE_VALUE("2.0", new BigDecimal[]{BigDecimal.valueOf(2.0)}, "2.00"),
	ADDITION("2+2", new BigDecimal[]{BigDecimal.valueOf(2), BigDecimal.valueOf(2)}, "4.00"),
	SUBTRACTION("4-2", new BigDecimal[]{BigDecimal.valueOf(4), BigDecimal.valueOf(2)}, "2.00"),
	MULTIPLICATION("2*2", new BigDecimal[]{BigDecimal.valueOf(2), BigDecimal.valueOf(2)}, "4.00"),
	DIVISION("4/2", new BigDecimal[]{BigDecimal.valueOf(4), BigDecimal.valueOf(2)}, "2.00"),
	PERCENTAGE("0.5%", new BigDecimal[]{BigDecimal.valueOf(0.5)}, "50.00%");

	private final String input;
	private final BigDecimal[] extracted;
	private final String answer;

	TestHelper(String input, BigDecimal[] extracted, String answer) {
		this.input = input;
		this.extracted = extracted;
		this.answer = answer;
	}

	public String input(){
		return input;
	}
	public String answer(){
		return answer;
	}
	public BigDecimal[] extracted(){return extracted;}
}
