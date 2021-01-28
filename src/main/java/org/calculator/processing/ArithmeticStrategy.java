package org.calculator.processing;

import org.calculator.common.Operations;
import org.calculator.common.Request;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Arrays;


class ArithmeticStrategy implements OperationStrategy {
	private Request request;
	public BigDecimal execute(Request request) {
		this.request = request;
		BigDecimal[] calculations = {division(), multiplication(), subtract(), addition()};

		return Arrays.stream(calculations)
				.reduce(BigDecimal.ZERO, BigDecimal::add);

	}

	private BigDecimal division(){
		BigDecimal[] division = request.getSection(Operations.DIVISION);
		BigDecimal value = division[0];
		for (int i = 1; i < division.length; i++){
			value = value.divide(division[i], MathContext.DECIMAL32) ;
		}
		return value;
	}

	private BigDecimal multiplication(){
		BigDecimal[] multiplication = request.getSection(Operations.MULTIPLICATION);
		BigDecimal value = multiplication[0];
		for (int i = 1; i < multiplication.length; i++){
			value = value.multiply(multiplication[i], MathContext.DECIMAL32);
		}
		return value;
	}

	private BigDecimal subtract(){
		BigDecimal[] subtractions = request.getSection(Operations.SUBTRACTION);
		BigDecimal value = subtractions[0];
		for (int i = 1; i < subtractions.length; i++){
			value = value.subtract(subtractions[i], MathContext.DECIMAL32);
		}
		return value;
	}

	private BigDecimal addition(){
		BigDecimal[] additions = request.getSection(Operations.ADDITION);
		BigDecimal value = additions[0];
		for (int i = 1; i < additions.length; i++){
			value = value.add(additions[i], MathContext.DECIMAL32);
		}
		return value;
	}



}
