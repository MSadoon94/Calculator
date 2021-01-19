public class RequestBuilder {
	private Request request;
	public void addOriginalInput(String in){
		request = new Request(in);
	}
	public Request getBuiltRequest() {
		return request;
	}

	public void buildAdditionSection(double[] addList) {
		request.setAdditions(addList);
	}

	public void buildSubtractionSection(double[] minusList) {
		request.setSubtractions(minusList);
	}
}
