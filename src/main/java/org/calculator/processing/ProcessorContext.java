package org.calculator.processing;

import org.calculator.common.Request;

class ProcessorContext {
	private OperationStrategy strategy;
	public void setStrategy(OperationStrategy strategy){
		this.strategy = strategy;
	}
	public double executeStrategy(Request request){
		return strategy.execute(request);
	}
}
