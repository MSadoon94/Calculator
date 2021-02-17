package org.calculator.request;


import org.calculator.common.Request;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GroupExtractor {
	private Pattern groupPattern = Pattern.compile("\\((.+?)\\)",Pattern.DOTALL);

	public int amountOfGroups(Request request){
		Matcher groupMatcher = groupPattern.matcher(request.value());
		int groupAmount = 0;
		while (groupMatcher.find()){
			groupAmount++;
		}
		return groupAmount;
	}

	public String[] extraction(Request request){
		Matcher groupMatcher = groupPattern.matcher(request.value());
		String[] extracted;
		if (groupMatcher.find()) {
			extracted = extractedGroups(request, groupMatcher);
		} else {
			extracted = new String[]{request.value(), request.value()};
		}
		return extracted;
	}
	private String[] extractedGroups(Request request, Matcher matcher){
		String group = matcher.group().replaceAll("[()]", "");
		String appendedRequest = request.value().replaceAll("\\Q" + matcher.group() + "\\E", group);
		return new String[]{group, appendedRequest};
	}

}
