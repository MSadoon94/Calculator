package org.calculator.processing;

import org.calculator.extraction.ExtractorUtilities;

public class ProcessorController implements ProcessorBoundary{
	public AnswerInvoker answerInvoker(ExtractorUtilities extractorUtilities) {
		return new AnswerInvoker(extractorUtilities);
	}
}
