package org.calculator.processing;

import org.calculator.common.Request;
import org.calculator.request.GroupExtractor;

public class GroupProcessor {
	private GroupExtractor extractor = new GroupExtractor();
	private String[] innerGroups;
	private String answer = "";
	private String targetGroup;

	public String answer(String targetGroup) {
		this.targetGroup = targetGroup;
		Request targetRequest = new Request(targetGroup);
		innerGroups = extractor.extraction(targetRequest);
		if (extractor.amountOfGroups(targetRequest) > 0) {
			processInnerGroups();
		}
		OperationSequencer sequencer = new OperationSequencer();
		answer = sequencer.answer(targetGroup);
		return answer;
	}

	private void processInnerGroups() {
		GroupProcessor secondaryProcessor = new GroupProcessor();
		for (String innerGroup : innerGroups) {
			String secondaryAnswer = secondaryProcessor.answer(innerGroup);
			targetGroup = targetGroup.replace(innerGroup, secondaryAnswer);
		}
	}
}
