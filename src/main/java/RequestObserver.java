public class RequestObserver implements Observer {
	private RequestAnalyzer analyzer;

	public RequestObserver(RequestAnalyzer analyzer){
		this.analyzer = analyzer;
	}

	public void update(String update) {
		analyzer.analysis(update);
	}
}
