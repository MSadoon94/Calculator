package org.calculator.processing;

import org.calculator.common.Request;
import org.calculator.extraction.ExtractorUtilities;

class AnswerInvoker implements Invoker {
	private ExtractorUtilities extractor;
	public AnswerInvoker(ExtractorUtilities extractorUtilities){
		extractor = extractorUtilities;
	}
	public String answer(Request request) {
		Request formatted = new NegativeFormatter().findDoubleNegatives(request);
		ProcessorActions processor = new Processor(formatted, extractor);
		return processor.processedAnswer().input();
	}
}
