package org.calculator.request;

import org.calculator.common.Operations;
import org.calculator.common.Request;

public interface Builder {
	void addOriginalInput(String in);

	Request getBuiltRequest();

	void buildSection(Operations section, double[] values);

}
