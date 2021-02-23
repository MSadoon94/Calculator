package org.calculator.processing;

import org.calculator.common.Request;

class AnswerInvoker implements Invoker {
	public String answer(Request request) {
		Request formatted = new NegativeFormatter().findDoubleNegatives(request);
		ProcessorActions processor = new Processor(formatted);
		return processor.processedAnswer().toString();
	}
}
