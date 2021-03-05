package org.calculator.processing;

import org.calculator.common.Request;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NegativeFormatter {
	public Request findDoubleNegatives(Request request) {
		Pattern negativePattern = Pattern.compile("(--)");
		Matcher negativeMatcher = negativePattern.matcher(request.input());
		while (negativeMatcher.find()){
			request = doubleNegative(request, negativeMatcher.group());
		}
		return request;
	}
	private Request doubleNegative(Request request, String section){
		return new Request(request.input().replace(section, "+"));
	}
}
