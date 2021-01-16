import java.text.DecimalFormat;

public class Processor implements ProcessorControl {

	private AnswerServices ansServices;
	private UiBoundary ui;

	public Processor(UiBoundary ui, AnswerServices ansServices){
		this.ui = ui;
		this.ansServices = ansServices;
	}
	public void processRequest(AnalyzedRequest request){
		Answer answer = calculate(request.toString());
	}

	public Answer calculate(String request){
		Answer answer = request.contains("+") ? addition(request) : singleValue(request);
		ansServices.addAnswer(answer.toString());
		ui.setResponse(answer.toString());
		return answer;
	}

	private Answer addition(String request) {
		DecimalFormat df = new DecimalFormat("#.00");
		double calculated = 0;
		String[] parsed = request.split("\\+",0);
		for (String value : parsed){
			calculated += Double.parseDouble(value);
			df.format(calculated);
		}

		return new Answer(String.valueOf(calculated));
	}

	private Answer singleValue(String request){
		return new Answer(request);
	}

}
