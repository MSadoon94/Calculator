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
		bigDecimalOperations.put(Operations.SQUARE_ROOT, this::squareRoot);
	}

	private BigDecimal squareRoot(BigDecimal radicand){
		return radicand.sqrt(MathContext.DECIMAL32);
	}

}
