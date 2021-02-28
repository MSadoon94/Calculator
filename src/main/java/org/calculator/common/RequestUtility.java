package org.calculator.common;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.regex.Pattern;

class RequestUtility {
	protected BigDecimal[] bigDecimalValues(Request request) {
		return convertedToBigDecimals(new BigDecimal[splitInput(request).length], splitInput(request));
	}
	private String[] splitInput(Request request){
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
