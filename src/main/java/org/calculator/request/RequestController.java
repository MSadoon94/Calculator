package org.calculator.request;

import org.calculator.processing.ProcessorActions;

public class RequestController implements RequestBoundary{

	public RequestAnalyzer requestAnalyzer(ProcessorActions processorActions, Builder builder) {
		return new RequestAnalyzer(processorActions, builder);
	}

	public Observer requestObserver(Analyzer analyzer) {
		return new RequestObserver(analyzer);
	}

	public RequestServices requestServices() {
		return new RequestHandler();
	}

	public Builder requestBuilder() {
		return new RequestBuilder();
	}

	public RequestFormat requestFormatter() {
		return new RequestFormatter();
	}

	public RequestServices requestHandler() {
		return new RequestHandler();
	}
}
