package org.calculator.processing;

import org.calculator.common.Operations;
import org.calculator.common.Request;

import java.util.Arrays;
import java.util.List;

public class OperationFinder {

	public Operations targetOperation(Request aRequest){
		List<Operations[]> operationTiers = Arrays.asList
				(
						new Operations[]{Operations.ROOT, Operations.EXPONENT},
						new Operations[]{Operations.DIVISION, Operations.MULTIPLICATION},
						new Operations[]{Operations.ADDITION, Operations.SUBTRACTION}
				);
		Operations target = null;
		int count = 0;
		while (target == null){
			target = findOperation(aRequest, operationTiers.get(count));
			count++;
		}
		return target;
	}

	private Operations findOperation(Request aRequest, Operations...operation){
		Operations[] operators =
				aRequest.operators().stream()
						.filter(op -> (op.equals(operation[0])) || (op.equals(operation[1])))
						.toArray(Operations[]::new);
		return (operators.length == 0) ? null : closestToStartOfSection(aRequest.input(), operators);
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
}
