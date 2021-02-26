package org.calculator.processing;

import org.calculator.common.Operations;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.function.BiFunction;

public class MultipleValueStrategy implements OperationStrategy {
	private HashMap<Operations, BiFunction<BigDecimal, BigDecimal, BigDecimal>> bigDecimalOperations = new HashMap<>();
	private Operations operation;

	public MultipleValueStrategy(Operations operation) {
		this.operation = operation;
		setBigDecimalOperations();
	}

	public BigDecimal execute(BigDecimal[] values){
		BigDecimal answer = values[0];
		for (int i = 1; i < values.length; i++) {
			answer = bigDecimalOperations.get(operation).apply(answer, values[i]);
		}
		return answer;
	}

	private void setBigDecimalOperations(){
		bigDecimalOperations.put(Operations.ADDITION, BigDecimal::add);
		bigDecimalOperations.put(Operations.SUBTRACTION, BigDecimal::subtract);
		bigDecimalOperations.put(Operations.MULTIPLICATION, BigDecimal::multiply);
		bigDecimalOperations.put(Operations.DIVISION, BigDecimal::divide);
		bigDecimalOperations.put(Operations.EXPONENT, this::exponentiation);
	}
	private BigDecimal exponentiation(BigDecimal base, BigDecimal exponent){
		return base.pow(exponent.intValue());
	}
}
