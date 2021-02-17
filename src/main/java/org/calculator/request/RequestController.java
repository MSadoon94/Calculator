package org.calculator.request;

import org.calculator.processing.Invoker;

public class RequestController implements RequestBoundary{
	public Observer requestObserver(Invoker invoker) {
		return new RequestObserver(invoker);
	}
}
