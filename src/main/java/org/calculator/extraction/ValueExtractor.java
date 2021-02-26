package org.calculator.extraction;

import org.calculator.common.Operations;
import org.calculator.common.Request;

import java.util.Arrays;
import java.util.regex.Pattern;

class ValueExtractor implements Extractor{
	public Request extraction(Request request) {
		String value = request.input();
		Operations operation = request.getOperation();
		String[] split = value.split(Pattern.quote(operation.symbol()));
		Request extracted = new Request(request.input());
		extracted.setValues(voidRemoved(split));
		return extracted;
	}
	private String[] voidRemoved(String[] split){
		return Arrays.stream(split)
				.filter(value -> (value != null && value.length() > 0))
				.toArray(String[]::new);
	}

}
