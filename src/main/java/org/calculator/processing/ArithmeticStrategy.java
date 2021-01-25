package org.calculator.processing;

import org.calculator.common.Operations;
import org.calculator.common.Request;

class ArithmeticStrategy implements OperationStrategy {
	private Request request;
	public double execute(Request request) {
		this.request = request;
		return division() + multiplication() + subtract() + addition() + singleValue();
	}

	private double division(){
		double[] division = request.getSection(Operations.DIVISION);
		if (division == null){
			return 0;
		}
		double value = division[0];
		for (int i = 1; i < division.length; i++){
			value /= division[i];
		}
		return value;
	}

	private double multiplication(){
		double[] multiplication = request.getSection(Operations.MULTIPLICATION);
		if( multiplication == null){
			return 0;
		}
		double value = multiplication[0];
		for (int i = 1; i < multiplication.length; i++){
			value *= multiplication[i];
		}
		return value;
	}

	private double subtract(){
		double[] subtractions = request.getSection(Operations.SUBTRACTION);
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
		double[] additions = request.getSection(Operations.ADDITION);
		if(additions == null){
			return 0;
		}
		double value = additions[0];
		for (int i = 1; i < additions.length; i++){
			value += additions[i];
		}
		return value;
	}

	private double singleValue(){
		double[] value = request.getSection(Operations.SINGLE_VALUE);
		if (value == null){
			return 0;
		}
		return value[0];
	}

}
