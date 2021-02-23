package org.calculator.processing;

import org.calculator.common.Request;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NegativeFormatter {
	public Request tagNegatives(Request request) {
		Pattern negativePattern = Pattern.compile("(\\D-)");
		Matcher negativeMatcher = negativePattern.matcher(request.value());
		Request tagged = request;
		while (negativeMatcher.find()){
			tagged = (negativeMatcher.group().equals("--"))
					? doubleNegative(tagged, negativeMatcher.group())
					: tag(tagged, negativeMatcher.group());
		}
		return tagged;
	}
	private Request tag(Request request, String section){
		String modifiedSection = section.substring(0, 1);
		return new Request(request.value().replace(section, modifiedSection + "NEG"));
	}

	private Request doubleNegative(Request request, String section){
		return new Request(request.value().replace(section, "+"));
	}
}
