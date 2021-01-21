package org.calculator.processing;

import org.calculator.answer.AnswerServices;
import org.calculator.user.UiActions;

public class ProcessorController implements ProcessorBoundary{
	public ProcessorActions processorActions(UiActions ui, AnswerServices services) {
		return new Processor(ui, services);
	}
}
