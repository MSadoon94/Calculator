package org.calculator.processing;

import org.calculator.common.Request;

import java.math.BigDecimal;

interface OperationStrategy {
	BigDecimal execute(Request request);
}
