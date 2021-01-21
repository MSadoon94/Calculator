package org.calculator.processing;

import org.calculator.answer.AnswerServices;
import org.calculator.user.UiActions;

public interface ProcessorBoundary {
	ProcessorActions processorActions(UiActions ui, AnswerServices services);
}
