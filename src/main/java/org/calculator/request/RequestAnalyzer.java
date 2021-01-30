package org.calculator.request;

import org.calculator.common.Operations;
import org.calculator.common.Request;
import org.calculator.processing.ProcessorActions;

import java.math.BigDecimal;
import java.util.Arrays;

class RequestAnalyzer implements Analyzer {

	private ProcessorActions processorActions;
	private Builder builder;
	private RequestFormatter formatter;
	public RequestAnalyzer(ProcessorActions processorActions, Builder builder){
		this.processorActions = processorActions;
		this.builder = builder;
		formatter = new RequestFormatter();
	}

	public void analysis(String input){
		builder.addOriginalInput(input);
		buildRequest(input);
		sendToProcessor();
	}

	private void sendToProcessor(){
		Request request = builder.getBuiltRequest();
		processorActions.processRequest(request);
	}

	private BigDecimal[] formatStringsToBigDecimal(String[] strings){
		return formatter.format(strings);
	}
	private void buildRequest(String input){
		System.out.println(input);
		String filteredInput = filterSingleValues(input);
		Arrays.stream(Operations.values()).forEach(op -> {
			if (filteredInput.contains(op.symbol())) {
				String[] splitInput = filteredInput.split("[" + op.symbol() + "]");
				builder.buildSection(op, formatStringsToBigDecimal(splitInput));
			}
		});
	}
	private String filterSingleValues(String input){
		String filteredInput = input;
		if (Arrays.stream(Operations.values()).noneMatch(op -> input.contains(op.symbol()))) {
			filteredInput += Operations.SINGLE_VALUE.symbol();
		}
		return  filteredInput;
	}


}
