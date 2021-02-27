package org.calculator.processing;

import org.calculator.common.Operations;
import org.calculator.common.Request;
import org.calculator.extraction.ExtractionController;

import java.math.BigDecimal;

class SubProcessor{
	private String input;
	private Request request;
	private Operations targetOperation;
	private ProcessorContext context = new ProcessorContext(new ExtractionController().valueExtractor());

	public SubProcessor(String input) {
		this.input = input;
	}

	public String subSection(Operations targetOperation){
		this.targetOperation = targetOperation;
		this.request = new Request(input);
		request.setOperation(targetOperation);
		return answer().toString();
	}

	private BigDecimal answer(){
		context.setStrategy(new MultipleValueStrategy(targetOperation));
		return context.executeStrategy(request);
	}

}
