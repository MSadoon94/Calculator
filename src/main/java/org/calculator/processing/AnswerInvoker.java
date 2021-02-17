package org.calculator.processing;

import org.calculator.common.Request;

class AnswerInvoker implements Invoker {
	public String answer(Request request) {
		ProcessorActions processor = new Processor(request);
		return processor.processedAnswer().toString();
	}
}
