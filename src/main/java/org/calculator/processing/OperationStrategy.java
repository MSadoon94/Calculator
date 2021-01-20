package org.calculator.processing;

import org.calculator.common.Request;

interface OperationStrategy {
	double execute(Request request);
}
