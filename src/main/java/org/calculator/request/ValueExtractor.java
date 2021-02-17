package org.calculator.request;

import org.calculator.common.Operations;
import org.calculator.common.Request;

public class ValueExtractor {
	public String[] extraction(Request request) {
		String value = request.value();
		Operations operation = request.getOperation();
		return value.split("[" + operation.symbol() + "]");
	}

}
