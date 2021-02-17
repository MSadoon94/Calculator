package org.calculator.processing;

import java.math.BigDecimal;

interface OperationStrategy {
	BigDecimal execute(BigDecimal[] values);
}
