package org.calculator.processing;

public class ProcessorController implements ProcessorBoundary{
	public AnswerInvoker answerInvoker() {
		return new AnswerInvoker();
	}
}
