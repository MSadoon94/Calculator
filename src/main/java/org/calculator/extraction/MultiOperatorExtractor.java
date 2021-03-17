package org.calculator.extraction;

import org.calculator.common.Operations;
import org.calculator.common.Request;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class MultiOperatorExtractor implements Extractor{

	public Request extraction(Request aRequest) {
		Operations targetOperation = aRequest.getOperation();
		Matcher matcher = extractor(aRequest, targetOperation);
		String extracted;
		if (matcher.find()){
			extracted = matcher.group();
		} else {
			extracted = aRequest.input();
		}
		return new Request(extracted);
	}

	private Matcher extractor(Request aRequest, Operations targetOperation){
		String operationValues =  "(\\d+\\.+\\d+|\\d+)";
		String regex =  operationValues + "[" + targetOperation.symbol() + "]" + operationValues;
		Pattern pattern = Pattern.compile(regex);
		return pattern.matcher(aRequest.input());
	}
}
