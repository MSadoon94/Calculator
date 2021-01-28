package org.calculator.request;

import java.math.BigDecimal;

public interface RequestFormat {
	BigDecimal[] format(String[] input);
}
