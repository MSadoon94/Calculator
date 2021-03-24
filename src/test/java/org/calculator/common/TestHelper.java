package org.calculator.common;

import java.math.BigDecimal;

public enum TestHelper {
	SINGLE_VALUE("2.0", bigDecimals(2.0), "2.00"),
	ADDITION("2+2", bigDecimals(2, 2), "4.00"),
	SUBTRACTION("4-2", bigDecimals(4, 2), "2.00"),
	MULTIPLICATION("2*2", bigDecimals(2, 2), "4.00"),
	DIVISION("4/2", bigDecimals(4, 2), "2.00"),
	PERCENTAGE("0.5%", bigDecimals(0.5), "50.00"),
	MIXED("(2-1+1*2)/(10/5)", bigDecimals(2, 1, 1, 2, 10, 5),"1.50"),
	EXPONENT("2^3", bigDecimals(2, 3), "8.00"),
	SQUARE_ROOT("√4", bigDecimals(4), "2.00"),
	NEGATIVE("4+-2", bigDecimals(4, -2), "2.00"),
	DECIMAL("4.5234*5.4325", bigDecimals(4.5234, 5.4325), "4.523"),
	NOTATION("6543.21", bigDecimals(6543.21), "6.54321*10^3");

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
	public static BigDecimal[] bigDecimals(double... values){
		BigDecimal[] decimals = new BigDecimal[values.length];
		for (int i = 0; i < values.length; i++){
			decimals[i] = BigDecimal.valueOf(values[i]);
		}
		return decimals;
	}
}
