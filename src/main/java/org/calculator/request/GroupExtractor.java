package org.calculator.request;


import org.calculator.common.Request;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GroupExtractor {
	private Pattern groupPattern = Pattern.compile("\\((.+?)\\)",Pattern.DOTALL);

	public int amountOfGroups(Request request){
		Matcher groupMatcher = groupPattern.matcher(request.input());
		int groupAmount = 0;
		while (groupMatcher.find()){
			groupAmount++;
		}
		return groupAmount;
	}

	public String[] extraction(Request request){
		Matcher groupMatcher = groupPattern.matcher(request.input());
		String[] extracted;
		if (groupMatcher.find()) {
			extracted = extractedGroups(request, groupMatcher);
		} else {
			extracted = new String[]{request.input(), request.input()};
		}
		return extracted;
	}
	private String[] extractedGroups(Request request, Matcher matcher){
		String group = matcher.group().replaceAll("[()]", "");
		String appendedRequest = request.input().replaceAll("\\Q" + matcher.group() + "\\E", group);
		return new String[]{group, appendedRequest};
	}

}
