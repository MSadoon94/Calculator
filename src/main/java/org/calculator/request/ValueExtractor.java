package org.calculator.request;

import org.calculator.common.Operations;
import org.calculator.common.Request;

import java.util.Arrays;
import java.util.regex.Pattern;

public class ValueExtractor {
	public String[] extraction(Request request) {
		String value = request.value();
		Operations operation = request.getOperation();
		String[] split = value.split(Pattern.quote(operation.symbol()));
		return voidRemoved(split);
	}
	private String[] voidRemoved(String[] split){
		return Arrays.stream(split)
				.filter(value -> (value != null && value.length() > 0))
				.toArray(String[]::new);
	}

}
