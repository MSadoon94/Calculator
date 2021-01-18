public class RequestBuilder {
	private Request request;
	public void addOriginalInput(String in){
		request = new Request(in);
	}
	public Request getBuiltRequest() {
		return request;
	}

	public void buildAdditionSection(String[] addList) {
		request.setAdditions(addList);
	}

	public void buildSubtractionSection(String[] minusList) {
		request.setSubtractions(minusList);
	}
}
