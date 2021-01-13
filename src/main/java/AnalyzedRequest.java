public class AnalyzedRequest extends Request {
	public AnalyzedRequest(String request) {
		super(request);
	}

	public String getAdditions() {
		return "2+2";
	}
}
