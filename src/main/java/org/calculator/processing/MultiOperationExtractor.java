package org.calculator.processing;

import org.calculator.common.Operations;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MultiOperationExtractor {

	public String extraction(String input, Operations targetOperation) {
		Matcher matcher = extractor(input, targetOperation);
		String extracted;
		if (matcher.find()){
			extracted = matcher.group();
		} else {
			extracted = input;
		}
		return extracted;
	}

	private Matcher extractor(String input, Operations targetOperation){
		String operationValues =  "(\\d+\\.+\\d+|\\d+)";
		String regex =  operationValues + "[" + targetOperation.symbol() + "]" + operationValues;
		Pattern pattern = Pattern.compile(regex);
		return pattern.matcher(input);
	}
}
