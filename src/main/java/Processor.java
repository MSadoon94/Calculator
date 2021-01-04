import java.util.HashMap;

public class Processor {
	private HashMap<String, CalculationRequest> savedInputs = new HashMap<>();
	public CalculationRequest getSavedInput(String request) {
		return savedInputs.get(request);
	}

	public void addInput(String request, CalculationRequest calculationRequest) {
		savedInputs.put(request, calculationRequest);
	}
}
