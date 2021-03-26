package org.calculator.common;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

class RequestUtility {
	protected BigDecimal[] bigDecimalValues(Request request) {
		return convertedToBigDecimals(new BigDecimal[splitInput(request).length], splitInput(request));
	}

	protected long amountOfOperators(Request request){
		return Arrays.stream(Operations.values())
				.map(Operations::symbol)
				.filter(request.input::contains)
				.count();
	}

	protected List<Operations> operatorList(Request request) {
		return Arrays.stream(Operations.values())
				.filter(op -> request.input.contains(op.symbol()))
				.collect(Collectors.toList());
	}

	protected String scientificNotation(String input){
		String[] split = input.split("\\.");

		int offset = split[1].length();

		String preNotation = split[0] + split[1];
		String firstDigitRemoved = preNotation.substring(1);

		int digits = firstDigitRemoved.length() - offset;

		return preNotation.charAt(0) + "." + zerosRemoved(firstDigitRemoved) + "*10^" + digits;
	}

	private String zerosRemoved(String input){
		String result = input;
		while(result.endsWith("0")){
			result = result.substring(0, result.length() - 1);
		}
		return result;
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
