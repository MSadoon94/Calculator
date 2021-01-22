package org.calculator.processing;

import org.calculator.common.Operations;
import org.calculator.common.Request;

class ArithmeticStrategy implements OperationStrategy {
	private Request request;
	public double execute(Request request) {
		this.request = request;
		return multiplication() + subtract() + addition();
	}
	private double subtract(){
		double[] subtractions = request.getSection(Operations.SUBTRACTION.symbol());
		if(subtractions == null){
			return 0;
		}

		double value = subtractions[0];
		for (int i = 1; i < subtractions.length; i++){
			value -= subtractions[i];
		}
		return value;
	}

	private double addition(){
		double[] additions = request.getSection(Operations.ADDITION.symbol());
		if(additions == null){
			return 0;
		}
		double value = additions[0];
		for (int i = 1; i < additions.length; i++){
			value += additions[i];
		}
		return value;
	}

	private double multiplication(){
		double[] multiplication = request.getSection(Operations.MULTIPLICATION.symbol());
		if( multiplication == null){
			return 0;
		}
		double value = multiplication[0];
		for (int i = 1; i < multiplication.length; i++){
			value *= multiplication[i];
		}
		return value;
	}
}
