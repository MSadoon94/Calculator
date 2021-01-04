import java.util.ArrayList;


public class RequestCache implements CacheServices {

	private ArrayList<CalculationRequest> requests = new ArrayList<>();

	public void addRequest(CalculationRequest in) {
		requests.add(in);
	}
	public CalculationRequest getNextRequest() {
		return requests.get(0);
	}
}
