package org.calculator.processing;

import org.calculator.common.Request;
import org.calculator.request.ValueExtractor;

import java.math.BigDecimal;

class ProcessorContext {
	private OperationStrategy strategy;
	private ValueExtractor extractor;
	public void setStrategy(OperationStrategy strategy){
		this.strategy = strategy;
	}
	public BigDecimal executeStrategy(Request request){
		extractor = new ValueExtractor();
		return strategy.execute(formattedValues(request));
	}
	private BigDecimal[] formattedValues(Request request){
		String[] values = extractor.extraction(request);
		BigDecimal[] bigDecimals = new BigDecimal[values.length];
		for (int i = 0; i < values.length; i++){
			bigDecimals[i] = new BigDecimal(values[i]);
		}
		return bigDecimals;
	}
}
