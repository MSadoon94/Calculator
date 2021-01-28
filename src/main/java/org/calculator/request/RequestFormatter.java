package org.calculator.request;

import java.math.BigDecimal;

class RequestFormatter implements RequestFormat {

	public BigDecimal[] format(String[] input) {
		BigDecimal[] values = new BigDecimal[input.length];
		for (int i = 0; i < input.length; i++){
			values[i] = new BigDecimal(input[i]);
		}
		return values;
	}
}
