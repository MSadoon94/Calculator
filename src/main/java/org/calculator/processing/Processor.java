package org.calculator.processing;

import org.calculator.answer.AnswerBuilder;
import org.calculator.common.Answer;
import org.calculator.answer.AnswerServices;
import org.calculator.common.Operations;
import org.calculator.common.Request;
import org.calculator.user.UiActions;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.DecimalFormat;

class Processor implements ProcessorActions {
	private AnswerServices ansServices;
	private UiActions ui;
	private DecimalFormat df = new DecimalFormat("#.00");
	private ProcessorContext context = new ProcessorContext();
	private AnswerBuilder builder = new AnswerBuilder();

	public Processor(UiActions ui, AnswerServices ansServices){
		this.ui = ui;
		this.ansServices = ansServices;
	}

	public void processRequest(Request request){
		Answer answer = builder.answer(symbol(request), calculate(request));
		sendAnswer(answer);
	}

	private String symbol(Request request){
		String symbol = "";
		if (request.toString().contains(Operations.PERCENTAGE.symbol())){
			symbol = Operations.PERCENTAGE.symbol();
		}
		return symbol;
	}

	private BigDecimal calculate(Request request){
		OperationStrategy[] strategies = {new FunctionStrategy(), new ArithmeticStrategy()};
		BigDecimal answer = BigDecimal.ZERO;
		for (OperationStrategy strategy : strategies) {
			answer =  answer.add(calculation(strategy, request), MathContext.DECIMAL32);
		}
		return answer.setScale(2, RoundingMode.HALF_UP);
	}

	private BigDecimal calculation(OperationStrategy strategy, Request request){
		context.setStrategy(strategy);
		return context.executeStrategy(request);
	}
	private void sendAnswer(Answer answer){
		ansServices.addAnswer(answer.toString());
		ui.setResponse(answer.toString());
	}

}
