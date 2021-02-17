package org.calculator.processing;

import org.calculator.common.Operations;

import java.util.Arrays;

public class OperationSequencer {
	public String answer(String input) {
		String targetSection = input;
		String singleOperation;
		MultiOperationExtractor multiOpExtractor = new MultiOperationExtractor();
		OperationFinder finder = new OperationFinder();
		for (int i = 0; i < amountOfOperators(input); i++) {
			Operations targetOperation = finder.targetOperation(targetSection);
			singleOperation =multiOpExtractor.extraction(targetSection, targetOperation);
			String partialAnswer =
					new SubProcessor(singleOperation).subSection(targetOperation);
			targetSection = targetSection.replace(singleOperation, partialAnswer);
		}
		return targetSection;
	}
	private long amountOfOperators (String input){
		return Arrays.stream(Operations.values())
				.filter(operation -> input.contains(operation.symbol()))
				.count();
	}
}
