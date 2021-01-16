import java.util.ArrayList;

public class RequestBuilder {
	private AnalyzedRequest request;
	public void addOriginalInput(String in){
		request = new AnalyzedRequest(in);
	}
	public AnalyzedRequest getBuiltRequest() {
		return request;
	}

	public void buildAdditionSection(ArrayList<String> addList) {
		request.setAdditions(addList);
	}
}
