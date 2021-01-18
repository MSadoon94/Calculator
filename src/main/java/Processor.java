import java.text.DecimalFormat;

public class Processor implements ProcessorControl {
	private AnswerServices ansServices;
	private UiBoundary ui;
	private DecimalFormat df = new DecimalFormat("#.00");

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
		}
		double calculation = 0;
		calculation +=(subtract(request));
		calculation +=(addition(request));
		return new Answer(String.valueOf(calculation));
	}

	private double subtract(Request request) {
		if(request.getSubtractions() == null){
			return 0;
		}
		String[] subtractions = request.getSubtractions();
		double value = Double.parseDouble(subtractions[0]);
		for (int i = 1; i < subtractions.length; i++){
			value -= Double.parseDouble(subtractions[i]);
		}
		return value;
	}

	private boolean isSingleValue(Request request){
		return request.getSubtractions() == null && request.getAdditions() == null;
	}

	private double addition(Request request){
		if(request.getAdditions() == null){
			return 0;
		}
		String[] additions = request.getAdditions();
		double value = Double.parseDouble(additions[0]);
		for (int i = 1; i < additions.length; i++){
			value += Double.parseDouble(additions[i]);
		}
		return value;
	}

	private void sendAnswer(Answer answer){
		ansServices.addAnswer(answer.toString());
		ui.setResponse(answer.toString());
	}

}
