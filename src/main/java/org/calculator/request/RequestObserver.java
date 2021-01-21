package org.calculator.request;

class RequestObserver implements Observer {
	private Analyzer analyzer;

	public RequestObserver(Analyzer analyzer){
		this.analyzer = analyzer;
	}

	public void update(String update) {
		analyzer.analysis(update);
	}
}
