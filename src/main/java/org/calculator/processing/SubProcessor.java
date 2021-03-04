package org.calculator.processing;
import org.calculator.common.Request;

class SubProcessor{

	private ProcessorContext context = new ProcessorContext();

	public Request subSection(Request aRequest){
		context.setStrategy(new MultipleValueStrategy(aRequest.getOperation()));
		return new Request(context.executeStrategy(aRequest).toString());
	}

}
