import java.util.ArrayList;

public class RequestBuilder {
	private Request request;
	public void addOriginalInput(String in){
		request = new Request(in);
	}
	public Request getBuiltRequest() {
		return request;
	}

	public void buildAdditionSection(ArrayList<String> addList) {
		request.setAdditions(addList);
	}
}
