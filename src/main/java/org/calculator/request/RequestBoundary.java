package org.calculator.request;


import org.calculator.processing.ProcessorActions;

public interface RequestBoundary {
	Analyzer requestAnalyzer(ProcessorActions processorActions, Builder builder);
	Observer requestObserver(Analyzer analyzer);
	RequestServices requestServices();
	Builder requestBuilder();
	Formatter requestFormatter();
	RequestServices requestHandler();

}
