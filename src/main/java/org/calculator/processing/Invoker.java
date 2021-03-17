package org.calculator.processing;

import org.calculator.common.Request;

public interface Invoker{
	Request answer(Request request);
}
