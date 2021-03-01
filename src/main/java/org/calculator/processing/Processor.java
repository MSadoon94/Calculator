package org.calculator.processing;

import org.calculator.common.Operations;
import org.calculator.common.Request;
import org.calculator.extraction.ExtractionController;
import org.calculator.extraction.ExtractorUtilities;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;

class Processor implements ProcessorActions {
	private Request request;
	private ExtractorUtilities extractor;
	private ProcessorContext context;

	public Processor(Request request, ExtractorUtilities extractor){
		this.request = request;
		this.extractor = extractor;
		this.context = new ProcessorContext();
	}

	public BigDecimal processedAnswer(){
		processGroupedSections();
		processMultipleOperationSections();
		return answer().setScale(2, RoundingMode.HALF_UP);
	}
	private void processGroupedSections(){
		GroupProcessor groupProcessor = new GroupProcessor(extractor);
		String processedGroup, modifiedSection;
		Request extractedGroup;
		extractedGroup = extractor.extraction(request);
		if (extractor.amountOfGroups(request) > 0){
			processedGroup = groupProcessor.answer(extractedGroup.getInnerGroup());
			modifiedSection = extractedGroup.input().replace( extractedGroup.getInnerGroup(), processedGroup);
			this.request = new Request(modifiedSection);
			processGroupedSections();
		} else {
			this.request = extractedGroup;
		}

	}

	private void processMultipleOperationSections(){
		OperationSequencer sequencer = new OperationSequencer(new ExtractionController().multiOperatorExtractor());
		while (this.request.operatorAmount() > 1){
			this.request = new Request(sequencer.answer(request.input()));
		}
	}

	private BigDecimal answer(){
		BigDecimal answer;
		request.setOperation(operatorForRequest().get(0));
		if(isUnaryOperation()){
			context.setStrategy(new UnaryValueStrategy(request.getOperation()));
		} else {
			context.setStrategy(new MultipleValueStrategy(request.getOperation()));
		}
		answer = context.executeStrategy(request);
		return answer;
	}

	private boolean isUnaryOperation(){
		return Arrays.asList(Operations.unaryOps())
				.contains(request.getOperation());
	}

	private ArrayList<Operations> operatorForRequest(){
		ArrayList<Operations> operators = new ArrayList<>();
		for (int i = 0; i < Operations.values().length; i++) {
			Operations operation = Operations.values()[i];
			if (request.input().contains(operation.symbol())){
				operators.add(operation);
			}
		}
		if (operators.size() == 0){
			createSingleValueOperation(operators);
		}
		return operators;
	}

	private void createSingleValueOperation(ArrayList<Operations> operators){
		request = new Request(request.input() + Operations.SINGLE_VALUE.symbol());
		operators.add(0, Operations.SINGLE_VALUE);
		request.setOperation(Operations.SINGLE_VALUE);
	}

}
