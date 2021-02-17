package org.calculator.request;

import org.calculator.processing.Invoker;

public interface RequestBoundary {
	Observer requestObserver(Invoker invoker);

}
