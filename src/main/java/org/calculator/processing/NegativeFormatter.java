package org.calculator.processing;

import org.calculator.common.Request;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NegativeFormatter {
	public Request tagNegatives(Request request) {
		Pattern negativePattern = Pattern.compile("(\\D-)");
		Matcher negativeMatcher = negativePattern.matcher(request.value());
		boolean hasANegativeNumber = negativeMatcher.find();
		Request formatted;
		formatted = (hasANegativeNumber) ? tag(request, negativeMatcher.group()) : request;
		return formatted;
	}
	private Request tag(Request request, String section){
		String modifiedSection = section.substring(0, 1);
		return new Request(request.value().replace(section, modifiedSection + "NEG"));
	}
}
