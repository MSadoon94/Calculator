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
	public String answer(String input) {
		String targetSection = input;
		Request results = new Request(targetSection);

		OperationFinder finder = new OperationFinder();
		for (int i = 0; i < amountOfOperators(input); i++) {
			Request inputRequest = new Request(targetSection);
			inputRequest.setOperation(finder.targetOperation(targetSection));
			Request partialResults =multiOpExtractor.extraction(inputRequest);
			String partialAnswer =
					new SubProcessor(partialResults.input()).subSection(inputRequest.getOperation());
			targetSection = targetSection.replace(partialResults.input(), partialAnswer);
			results = new Request(targetSection);
		}
		return results.input();
	}
	private long amountOfOperators (String input){
		return Arrays.stream(Operations.values())
				.filter(operation -> input.contains(operation.symbol()))
				.count();
	}
}
