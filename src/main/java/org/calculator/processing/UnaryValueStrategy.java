package org.calculator.processing;

import org.calculator.common.Operations;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.HashMap;
import java.util.function.Function;

public class UnaryValueStrategy implements OperationStrategy {
	private HashMap<Operations, Function<BigDecimal, BigDecimal> > bigDecimalOperations = new HashMap<>();
	private Operations operation;

	public UnaryValueStrategy(Operations operation) {
		this.operation = operation;
		setBigDecimalOperations();
	}

	public BigDecimal execute(BigDecimal[] value) {
		return bigDecimalOperations.get(operation).apply(value[0]);
	}

	private void setBigDecimalOperations(){
		bigDecimalOperations.put(Operations.SINGLE_VALUE, this::singleValue);
		bigDecimalOperations.put(Operations.PERCENTAGE, this::percentage);
	}

	private BigDecimal singleValue(BigDecimal value){
		return value;
	}
	private BigDecimal percentage(BigDecimal fraction){
		return fraction.multiply(BigDecimal.valueOf(100));
	}

}
