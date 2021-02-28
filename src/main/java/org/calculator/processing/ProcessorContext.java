package org.calculator.processing;

import org.calculator.common.Operations;
import org.calculator.common.Request;
import org.calculator.extraction.Extractor;

import java.math.BigDecimal;

class ProcessorContext {
	private OperationStrategy strategy;
	private Extractor extractor;
	public void setStrategy(OperationStrategy strategy){
		this.strategy = strategy;
	}
	public ProcessorContext(Extractor valueExtractor){
		this.extractor = valueExtractor;
	}
	public BigDecimal executeStrategy(Request request){
		return strategy.execute(request.bigDecimals());
	}
}
