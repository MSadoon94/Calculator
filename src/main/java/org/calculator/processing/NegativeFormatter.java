package org.calculator.processing;

import org.calculator.common.Request;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NegativeFormatter {
	public Request findDoubleNegatives(Request request) {
		Pattern negativePattern = Pattern.compile("(--)");
		Matcher negativeMatcher = negativePattern.matcher(request.input());
		Request tagged = request;
		while (negativeMatcher.find()){
			tagged = doubleNegative(tagged, negativeMatcher.group());
		}
		return tagged;
	}
	private Request doubleNegative(Request request, String section){
		return new Request(request.input().replace(section, "+"));
	}
}
