import java.util.ArrayList;


public class RequestCache implements CacheServices {

	private ArrayList<Request> requests = new ArrayList<>();

	public void addRequest(Request in) {
		requests.add(in);
	}
	public Request getNextRequest() {
		return requests.get(0);
	}
}
