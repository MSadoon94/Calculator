package org.calculator.processing;

import org.calculator.common.Request;
import org.calculator.extraction.ExtractorUtilities;

class AnswerInvoker implements Invoker {
	public String answer(Request request, ExtractorUtilities extractorUtilities) {
		Request formatted = new NegativeFormatter().findDoubleNegatives(request);
		ProcessorActions processor = new Processor(formatted, extractorUtilities);
		return processor.processedAnswer().toString();
	}
}
