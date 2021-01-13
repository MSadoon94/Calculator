public class RequestAnalyzer {

	public Request analysis(Request request) {
		return new AnalyzedRequest(request.toString());
	}
}
