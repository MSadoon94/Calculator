package org.calculator.processing;

import org.calculator.common.Request;
import java.math.BigDecimal;

class ProcessorContext {
	private OperationStrategy strategy;

	public void setStrategy(OperationStrategy strategy){
		this.strategy = strategy;
	}
	public BigDecimal executeStrategy(Request request){
		return strategy.execute(request.bigDecimals());
	}
}
