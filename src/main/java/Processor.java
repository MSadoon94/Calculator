import java.text.DecimalFormat;

public class Processor implements ProcessorControl {
	private AnswerServices ansServices;
	private UiBoundary ui;
	private DecimalFormat df = new DecimalFormat("#.00");
	private ProcessorContext context = new ProcessorContext();

	public Processor(UiBoundary ui, AnswerServices ansServices){
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
		return request.getSubtractions() == null && request.getAdditions() == null;
	}

	private boolean hasArithmetic(Request request){
		String input = request.toString();
		return (input.contains("+") | input.contains("-"));
	}

	private void sendAnswer(Answer answer){
		ansServices.addAnswer(answer.toString());
		ui.setResponse(answer.toString());
	}

}
