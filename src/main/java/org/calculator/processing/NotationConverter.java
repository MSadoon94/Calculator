package org.calculator.processing;

import org.calculator.common.Request;

class NotationConverter {
	public Request scientific(Request request) {
		String secondDigit = request.input().substring(1);

		int digits = secondDigit.length();

		return new Request(String.valueOf(digits));
	}
}
