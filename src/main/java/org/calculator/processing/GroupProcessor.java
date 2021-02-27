package org.calculator.processing;

import org.calculator.common.Request;
import org.calculator.extraction.ExtractionController;
import org.calculator.extraction.ExtractorUtilities;

public class GroupProcessor {
	private ExtractorUtilities extractor;
	private String innerGroup;
	private String answer = "";
	private String targetGroup;

	public GroupProcessor(ExtractorUtilities extractorUtilities){
		this.extractor = extractorUtilities;
	}
	public String answer(String targetGroup) {
		this.targetGroup = targetGroup;
		Request targetRequest = extractor.extraction(new Request(targetGroup));
		innerGroup = targetRequest.getInnerGroup();
		if (extractor.amountOfGroups(targetRequest) > 0) {
			processInnerGroup();
		}
		OperationSequencer sequencer = new OperationSequencer(new ExtractionController().multiOperatorExtractor());
		answer = sequencer.answer(targetGroup);
		return answer;
	}

	private void processInnerGroup() {
		GroupProcessor secondaryProcessor = new GroupProcessor(extractor);
		String secondaryAnswer = secondaryProcessor.answer(innerGroup);
		targetGroup = targetGroup.replace(innerGroup, secondaryAnswer);
	}
}
