package org.calculator.request;

import org.calculator.common.Request;
import org.calculator.processing.ProcessorActions;

class RequestAnalyzer implements Analyzer {

	private ProcessorActions processorActions;
	private Builder builder;
	private RequestFormatter formatter;
	private String[] operations = {"+", "-", "*"};
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
		for (String operation : operations) {
			if (input.contains(operation)) {
				double[] formattedInput =
						formatStringsToDoubles(input.split("[" + operation + "]"));
				builder.buildSection(operation, formattedInput);
			}
		}
	}


}
