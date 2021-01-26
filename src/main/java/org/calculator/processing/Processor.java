package org.calculator.processing;

import org.calculator.common.Answer;
import org.calculator.answer.AnswerServices;
import org.calculator.common.Operations;
import org.calculator.common.Request;
import org.calculator.user.UiActions;

import java.text.DecimalFormat;

class Processor implements ProcessorActions {
	private AnswerServices ansServices;
	private UiActions ui;
	private DecimalFormat df = new DecimalFormat("#.00");
	private ProcessorContext context = new ProcessorContext();

	public Processor(UiActions ui, AnswerServices ansServices){
		this.ui = ui;
		this.ansServices = ansServices;
	}

	public void processRequest(Request request){
		Answer answer = calculate(request);
		sendAnswer(answer);
	}

	private Answer calculate(Request request){
		if (isSingleValue(request)){
			return new Answer(request.toString());
		} else if (hasArithmetic(request)){
			context.setStrategy(new ArithmeticStrategy());
		}
		double calculation = context.executeStrategy(request);
		return new Answer(String.valueOf(calculation));
	}

	private boolean isSingleValue(Request request){
		return request.getSection(Operations.SINGLE_VALUE) != null;
	}

	private boolean hasArithmetic(Request request){
		boolean hasArithmetic = false;
		String input = request.toString();
		String[] symbols = Operations.arithmeticSymbols();
		for (String symbol : symbols) {
			if (input.contains(symbol)) {
				hasArithmetic = true;
			}
		}
		return hasArithmetic;
	}

	private void sendAnswer(Answer answer){
		ansServices.addAnswer(answer.toString());
		ui.setResponse(answer.toString());
	}

}
