package org.calculator.request;

import org.calculator.common.Request;
import org.calculator.extraction.ExtractionController;
import org.calculator.processing.Invoker;

class RequestObserver implements Observer {
	private Invoker invoker;

	public RequestObserver(Invoker invoker){
		this.invoker = invoker;
	}

	public String update(Request request) {
		return invoker.answer(request, new ExtractionController().groupExtractor());
	}
}
