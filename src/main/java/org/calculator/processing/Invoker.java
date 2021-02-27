package org.calculator.processing;

import org.calculator.common.Request;
import org.calculator.extraction.ExtractorUtilities;

public interface Invoker {
	String answer(Request request, ExtractorUtilities extractorUtilities);
}
