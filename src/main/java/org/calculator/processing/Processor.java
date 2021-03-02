package org.calculator.processing;

import org.calculator.common.Operations;
import org.calculator.common.Request;
import org.calculator.extraction.ExtractionController;
import org.calculator.extraction.ExtractorUtilities;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;

class Processor implements ProcessorActions {
	private Request request;
	private int decimalPosition;
	private ExtractorUtilities extractor;
	private ProcessorContext context;

	public Processor(Request request, ExtractorUtilities extractor){
		this.request = request;
		decimalPosition = request.decimalPosition();
		this.extractor = extractor;
		this.context = new ProcessorContext();
	}

	public BigDecimal processedAnswer(){
		Request processed = processMultipleOperationSections(requestAfterProcessingGroups(request));
		return answer(processed).setScale(decimalPosition, RoundingMode.HALF_UP);
	}
	private Request requestAfterProcessingGroups(Request aRequest){
		while(extractor.amountOfGroups(aRequest) > 0){
			aRequest = processGroupedSections(aRequest);
		}
		return aRequest;
	}

	private Request processGroupedSections(Request aRequest){
		GroupProcessor groupProcessor = new GroupProcessor(extractor);
		Request extractedGroup, result;
		extractedGroup = extractor.extraction(aRequest);
		System.out.println("ExtractedGroup: " + extractedGroup.input());
		if (extractor.amountOfGroups(aRequest) > 0){
			System.out.println("AmountOfGroups: " + extractor.amountOfGroups(aRequest));
			String processedGroup = groupProcessor.answer(extractedGroup.getInnerGroup());
			String modifiedSection = extractedGroup.input().replace(extractedGroup.getInnerGroup(), processedGroup);
			result = new Request(modifiedSection);
			System.out.println("ProcessedGroupResult: " + result.input());
		} else {
			result = extractedGroup;
		}
		System.out.println(result.input());
		return result;
	}



	private Request processMultipleOperationSections(Request aRequest){
		OperationSequencer sequencer = new OperationSequencer(new ExtractionController().multiOperatorExtractor());
		while (aRequest.operatorAmount() > 1){
			aRequest = new Request(sequencer.answer(aRequest.input()));
		}
		return aRequest;
	}

	private BigDecimal answer(Request aRequest){
		BigDecimal answer;
		aRequest.setOperation(operatorForRequest(aRequest).get(0));
		if(isUnaryOperation(aRequest)){
			context.setStrategy(new UnaryValueStrategy(aRequest.getOperation()));
		} else {
			context.setStrategy(new MultipleValueStrategy(aRequest.getOperation()));
		}
		answer = context.executeStrategy(aRequest);
		return answer;
	}

	private boolean isUnaryOperation(Request aRequest){
		return Arrays.asList(Operations.unaryOps())
				.contains(aRequest.getOperation());
	}

	private List<Operations> operatorForRequest(Request aRequest){
		List<Operations> operators = aRequest.operators();
		if (operators.size() == 0){
			createSingleValueOperation(operators);
		}
		return operators;
	}

	private void createSingleValueOperation(List<Operations> operators){
		request = new Request(request.input() + Operations.SINGLE_VALUE.symbol());
		operators.add(0, Operations.SINGLE_VALUE);
		request.setOperation(Operations.SINGLE_VALUE);
	}

}
