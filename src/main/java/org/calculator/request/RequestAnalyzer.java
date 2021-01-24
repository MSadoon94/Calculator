package org.calculator.request;

import org.calculator.common.Operations;
import org.calculator.common.Request;
import org.calculator.processing.ProcessorActions;

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

	private double[] formatStringsToDoubles(String[] strings){
		return formatter.format(strings);
	}
	private void buildRequest(String input){
		for (Operations operation : Operations.values()) {
			if (input.contains(operation.symbol())) {
				double[] formattedInput =
						formatStringsToDoubles(input.split("[" + operation.symbol() + "]"));
				builder.buildSection(operation, formattedInput);
			}
		}
	}


}
