package org.calculator.processing;

import org.calculator.common.Operations;
import org.calculator.common.Request;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Arrays;

public class FunctionStrategy implements OperationStrategy {
	private Request request;

	public BigDecimal execute(Request request) {
		this.request = request;
		BigDecimal[] calculations = {percentage(), singleValue()};
		return Arrays.stream(calculations)
				.reduce(BigDecimal.ZERO, BigDecimal::add);
	}

	private BigDecimal percentage(){
		BigDecimal[] value = request.getSection(Operations.PERCENTAGE);
		return value[0].multiply(BigDecimal.valueOf(100), MathContext.DECIMAL32);
	}

	private BigDecimal singleValue(){
		BigDecimal[] value = request.getSection(Operations.SINGLE_VALUE);
		return value[0];
	}

}
