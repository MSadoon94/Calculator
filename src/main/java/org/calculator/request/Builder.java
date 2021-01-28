package org.calculator.request;

import org.calculator.common.Operations;
import org.calculator.common.Request;

import java.math.BigDecimal;

public interface Builder {
	void addOriginalInput(String in);

	Request getBuiltRequest();

	void buildSection(Operations section, BigDecimal[] values);

}
