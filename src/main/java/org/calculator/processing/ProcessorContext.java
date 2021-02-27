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
		return strategy.execute(formattedValues(request));
	}
	private BigDecimal[] formattedValues(Request request){
		String[] values = extractor.extraction(request).getValues();
		BigDecimal[] bigDecimals = new BigDecimal[values.length];
		for (int i = 0; i < values.length; i++){
			if(values[i].equals(Operations.SUBTRACTION.symbol())){
				bigDecimals[i] = new BigDecimal(values[i++]).negate();
			} else {
				bigDecimals[i] = new BigDecimal(values[i]);
			}
		}
		return bigDecimals;
	}
}
