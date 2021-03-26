package org.calculator.processing;

import org.calculator.common.Operations;
import org.calculator.common.Request;
import org.calculator.extraction.Extractor;

import java.util.Arrays;

public class OperationSequencer {
	private Extractor multiOpExtractor;
	public OperationSequencer(Extractor multiOpExtractor){
		this.multiOpExtractor = multiOpExtractor;
	}

	public Request answer(Request aRequest){
		for (long i = aRequest.operatorAmount(); i > 0; i--){
			if(aRequest.operatorAmount() != 0) {
				Request subRequest = subRequest(aRequest);
				Request answer = new SubProcessor().subSection(subRequest);
				aRequest = new Request(aRequest.input().replace(subRequest.input(), answer.input()));
			}
		}
		System.out.println(aRequest.input());
		return aRequest;
	}

	private Request subRequest(Request aRequest){
		aRequest.setOperation(new OperationFinder().targetOperation(aRequest));
		Request subRequest = multiOpExtractor.extraction(aRequest);
		subRequest.setOperation(aRequest.getOperation());
		return subRequest;
	}


}
