package org.calculator.common;


import java.math.BigDecimal;
import java.util.HashMap;

public class Request extends Calculation {
	private HashMap<Operations, BigDecimal[]> sectionType = new HashMap<>();

	public Request(String request) {
		super(request);
	}
	public void setSection(Operations type, BigDecimal[] values){
		sectionType.put(type,values);
	}

	public BigDecimal[] getSection(Operations type){
		BigDecimal[] section = sectionType.get(type);
		if (section == null){
			section = new BigDecimal[]{BigDecimal.ZERO};
		}
		return section;
	}

}
