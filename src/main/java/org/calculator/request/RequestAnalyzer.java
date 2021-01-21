package org.calculator.request;

import org.calculator.common.Request;
import org.calculator.processing.ProcessorActions;
import org.calculator.processing.ProcessorBoundary;


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
	private void buildRequest(String input){
		String addition = "+";
		String subtraction = "-";
		if (input.contains(addition)){
			double[] formattedInput =
					formatStringsToDoubles(input.split("[" + addition + "]"));
			builder.buildAdditionSection(formattedInput);
		}
		if(input.contains(subtraction)){
			double[] formattedInput =
					formatStringsToDoubles(input.split("[" + subtraction + "]"));
			builder.buildSubtractionSection(formattedInput);
		}
	}
	private double[] formatStringsToDoubles(String[] strings){
		return formatter.format(strings);
	}


}
