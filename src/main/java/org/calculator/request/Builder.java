package org.calculator.request;

import org.calculator.common.Request;

public interface Builder {
	void addOriginalInput(String in);

	Request getBuiltRequest();

	void buildAdditionSection(double[] addList);

	void buildSubtractionSection(double[] minusList);
}
