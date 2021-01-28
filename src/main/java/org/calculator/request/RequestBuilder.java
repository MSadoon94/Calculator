package org.calculator.request;

import org.calculator.common.Operations;
import org.calculator.common.Request;

import java.math.BigDecimal;

class RequestBuilder implements Builder{
	private Request request;

	public void addOriginalInput(String in){
		request = new Request(in);
	}

	public Request getBuiltRequest() {
		return request;
	}

	public void buildSection(Operations section, BigDecimal[] values) {
		request.setSection(section, values);
	}
}
