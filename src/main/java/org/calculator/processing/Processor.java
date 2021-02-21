package org.calculator.processing;

import org.calculator.common.Operations;
import org.calculator.common.Request;
import org.calculator.request.GroupExtractor;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;

class Processor implements ProcessorActions {
	private Request request;
	private ProcessorContext context = new ProcessorContext();

	public Processor(Request request){
		this.request = request;
	}

	public BigDecimal processedAnswer(){
		processGroupedSections();
		processMultipleOperationSections();
		return answer().setScale(2, RoundingMode.HALF_UP);
	}
	private void processGroupedSections(){
		int groupAmount = new GroupExtractor().amountOfGroups(request);
		GroupProcessor groupProcessor = new GroupProcessor();
		String processedGroup, modifiedSection;
		String[] extractedGroup;
		extractedGroup = new GroupExtractor().extraction(request);
		if (groupAmount > 0){
			processedGroup = groupProcessor.answer(extractedGroup[0]);
			modifiedSection = extractedGroup[1].replace( extractedGroup[0], processedGroup);
			this.request = new Request(modifiedSection);
			processGroupedSections();
		} else {
			this.request = new Request(extractedGroup[0]);
		}

	}

	private void processMultipleOperationSections(){
		OperationSequencer sequencer = new OperationSequencer();
		while (amountOfOperators() > 1){
			this.request = new Request(sequencer.answer(request.value()));
		}
	}

	private BigDecimal answer(){
		BigDecimal answer;
		request.setOperation(operatorForRequest().get(0));
		if(isFunctionOperation()){
			context.setStrategy(new FunctionStrategy(request.getOperation()));
		} else {
			context.setStrategy(new MultipleValueStrategy(request.getOperation()));
		}
		answer = context.executeStrategy(request);
		return answer;
	}

	private long amountOfOperators(){
		return Arrays.stream(Operations.values())
				.filter(operation -> request.value().contains(operation.symbol()))
				.count();
	}

	private boolean isFunctionOperation(){
		return Arrays.stream(Operations.functionOps())
				.anyMatch(operation -> request.getOperation() == operation);
	}

	private ArrayList<Operations> operatorForRequest(){
		ArrayList<Operations> operators = new ArrayList<>();
		for (int i = 0; i < Operations.values().length; i++) {
			Operations operation = Operations.values()[i];
			if (request.value().contains(operation.symbol())){
				operators.add(operation);
			}
		}
		if (operators.size() == 0){
			createSingleValueOperation(operators);
		}
		return operators;
	}

	private void createSingleValueOperation(ArrayList<Operations> operators){
		request = new Request(request.value() + Operations.SINGLE_VALUE.symbol());
		operators.add(0, Operations.SINGLE_VALUE);
		request.setOperation(Operations.SINGLE_VALUE);
	}

}
