package org.calculator.request;

import org.calculator.common.Request;

public interface Observer {
	String update(Request update);
}
