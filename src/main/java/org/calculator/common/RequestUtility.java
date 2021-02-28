package org.calculator.common;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.regex.Pattern;

class RequestUtility extends Request {
	private Request request;
	public RequestUtility(Request request) {
		super(request.input);
		this.request = request;
	}

	public BigDecimal[] bigDecimalValues() {
		return convertedToBigDecimals(new BigDecimal[splitInput().length], splitInput());
	}
	private String[] splitInput(){
		return Arrays.stream(request.input.split(Pattern.quote(request.operation.symbol())))
				.filter(value -> (value != null && value.length() > 0))
				.toArray(String[]::new);
	}
	private BigDecimal[] convertedToBigDecimals(BigDecimal[] bigDecimals, String[] split) {
		for (int i = 0; i < split.length; i++) {
			if (split[i].equals(Operations.SUBTRACTION.symbol())) {
				bigDecimals[i] = new BigDecimal(split[i++]).negate();
			} else {
				bigDecimals[i] = new BigDecimal(split[i]);
			}
		}
		return bigDecimals;
	}
}
