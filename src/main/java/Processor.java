import java.text.DecimalFormat;
import java.util.ArrayList;

public class Processor implements ProcessorControl {
	private AnswerServices ansServices;
	private UiBoundary ui;
	private DecimalFormat df = new DecimalFormat("#.00");

	public Processor(UiBoundary ui, AnswerServices ansServices){
		this.ui = ui;
		this.ansServices = ansServices;
	}
	public void processRequest(AnalyzedRequest request){
		Answer answer = calculate(request);
		sendAnswer(answer);
	}

	private Answer calculate(AnalyzedRequest request){
		if (isSingleValue(request)){
			return new Answer(request.toString());
		}
		double calculation = 0;
		calculation +=(sumAdditionSection(request));
		return new Answer(String.valueOf(calculation));
	}

	private boolean isSingleValue(AnalyzedRequest request){
		return request.getAdditions() == null;
	}

	private double sumAdditionSection(AnalyzedRequest request){
		ArrayList<String> additions = request.getAdditions();
		return additions.stream()
				.mapToDouble(Double::parseDouble)
				.sum();
	}

	private void sendAnswer(Answer answer){
		ansServices.addAnswer(answer.toString());
		ui.setResponse(answer.toString());
	}

}
