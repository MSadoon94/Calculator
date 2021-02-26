package org.calculator.extraction;

import org.calculator.common.Operations;
import org.calculator.common.Request;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class MultiOperationExtractor implements Extractor{

	public Request extraction(Request input) {
		Operations targetOperation = input.getOperation(); //ToDo make sure classes are setting operation before using this extractor
		Matcher matcher = extractor(input, targetOperation);
		String extracted;
		if (matcher.find()){
			extracted = matcher.group();
		} else {
			extracted = input.input();
		}
		return new Request(extracted);
	}

	private Matcher extractor(Request input, Operations targetOperation){
		String operationValues =  "(\\d+\\.+\\d+|\\d+)";
		String regex =  operationValues + "[" + targetOperation.symbol() + "]" + operationValues;
		Pattern pattern = Pattern.compile(regex);
		return pattern.matcher(input.input());
	}
}
