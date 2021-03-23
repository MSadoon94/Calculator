package org.calculator.processing;

import org.calculator.common.Request;
import org.calculator.extraction.ExtractionController;
import org.calculator.extraction.ExtractorUtilities;

public class GroupProcessor {
	private ExtractorUtilities extractor;

	public GroupProcessor(ExtractorUtilities extractorUtilities){
		this.extractor = extractorUtilities;
	}

	public Request answer(Request aRequest){
		Request targetRequest = extractor.extraction(aRequest);
		if (extractor.amountOfGroups(aRequest) > 0){
			aRequest = processInnerGroup(targetRequest);
		}
		OperationSequencer sequencer = new OperationSequencer(new ExtractionController().multiOperatorExtractor());
		return sequencer.answer(aRequest);
	}

	private Request processInnerGroup(Request aRequest) {
		GroupProcessor secondaryProcessor = new GroupProcessor(extractor);
		Request subRequest = secondaryProcessor.answer(new Request(aRequest.getInnerGroup()));
		return new Request(aRequest.input().replace(aRequest.getInnerGroup(), subRequest.input()));
	}
}
