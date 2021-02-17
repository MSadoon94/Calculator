package org.calculator.processing;

import org.calculator.common.Operations;

import java.math.BigDecimal;
import java.util.HashMap;

public class FunctionStrategy implements OperationStrategy {
	private HashMap<Operations, BigDecimal> functionConstants = new HashMap<>();
	private Operations operation;

	public FunctionStrategy(Operations operation) {
		this.operation = operation;
		setFunctionConstants();
	}
	public BigDecimal execute(BigDecimal[] values){
		return values[0].multiply(functionConstants.get(operation));
	}

	private void setFunctionConstants() {
		BigDecimal singleValueMultiplier = BigDecimal.ONE;
		BigDecimal percentageMultiplier = new BigDecimal("100");
		functionConstants.put(Operations.SINGLE_VALUE, singleValueMultiplier);
		functionConstants.put(Operations.PERCENTAGE, percentageMultiplier);
	}
}

