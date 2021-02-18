package org.calculator.request;

import org.calculator.common.Operations;
import org.calculator.common.Request;

import java.util.regex.Pattern;

public class ValueExtractor {
	public String[] extraction(Request request) {
		String value = request.value();
		Operations operation = request.getOperation();
		return value.split(Pattern.quote(operation.symbol()));
	}

}
