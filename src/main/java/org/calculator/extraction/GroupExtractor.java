package org.calculator.extraction;


import org.calculator.common.Request;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class GroupExtractor implements Extractor, Utilities{
	private Pattern groupPattern = Pattern.compile("\\((.+?)\\)",Pattern.DOTALL);

	public int amountOfGroups(Request request){
		Matcher groupMatcher = groupPattern.matcher(request.input());
		int groupAmount = 0;
		while (groupMatcher.find()){
			groupAmount++;
		}
		return groupAmount;
	}

	public Request extraction(Request request){
		Matcher groupMatcher = groupPattern.matcher(request.input());
		Request extracted;
		if (groupMatcher.find()) {
			extracted = extractedGroup(request, groupMatcher);
		} else {
			extracted = new Request(request.input());
			extracted.setInnerGroup(request.input());
		}

		return extracted;
	}
	private Request extractedGroup(Request request, Matcher matcher){
		String group = matcher.group().replaceAll("[()]", "");
		String appendedRequest = request.input().replaceAll("\\Q" + matcher.group() + "\\E", group);
		Request extracted = new Request(appendedRequest);
		extracted.setInnerGroup(group);
		return extracted;
	}

}
