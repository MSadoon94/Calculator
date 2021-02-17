package org.calculator.processing;

import org.calculator.common.Operations;

import java.util.Arrays;

public class OperationFinder {
	private Operations operator;
	public Operations targetOperation(String section) {
		Operations target;

		if (amountOfOperators(section) <= 1){
			target = operator;
		} else if (section.contains(Operations.DIVISION.symbol())
				&& section.contains(Operations.MULTIPLICATION.symbol())){
			target = closestToStartOfSection(section, Operations.DIVISION, Operations.MULTIPLICATION);
		} else if (section.contains(Operations.DIVISION.symbol())
				|| section.contains(Operations.MULTIPLICATION.symbol())){
			target = section.contains(Operations.DIVISION.symbol())
					? Operations.DIVISION : Operations.MULTIPLICATION;
		} else {
			target = closestToStartOfSection(section, Operations.ADDITION, Operations.SUBTRACTION);
		}

		return target;
	}

	private Operations closestToStartOfSection(String section, Operations...operations){
		Operations closest = operations[0];
		int closestIndex = section.length();
		for (Operations operation : operations) {
			int operationIndex = section.indexOf(operation.symbol());
			if (operationIndex < closestIndex){
				closestIndex = operationIndex;
				closest = operation;
			}
		}
		return closest;
	}

	private long amountOfOperators (String input){
		return Arrays.stream(Operations.values())
				.filter(operation -> input.contains(operation.symbol()))
				.map(op -> operator = op)
				.count();
	}
}
