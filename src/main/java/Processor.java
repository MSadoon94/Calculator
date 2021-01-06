import java.util.HashMap;

public class Processor {
	private HashMap<String, Request> savedInputs = new HashMap<>();
	public Request getSavedInput(String request) {
		return savedInputs.get(request);
	}

	public void addInput(String request, Request calculationRequest) {
		savedInputs.put(request, calculationRequest);
	}

	public Answer getAnswer() { return new Answer("2");
	}
}
