package org.calculator.processing;

import org.calculator.extraction.ExtractorUtilities;

public interface ProcessorBoundary {
	AnswerInvoker answerInvoker(ExtractorUtilities extractorUtilities);
}
