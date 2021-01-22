package org.calculator.processortests;

import org.calculator.answer.AnswerHandler;
import org.calculator.answer.AnswerServices;
import org.calculator.processing.ProcessorActions;
import org.calculator.processing.ProcessorBoundary;
import org.calculator.processing.ProcessorController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.calculator.common.Request;
import org.calculator.user.UiActions;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

@ExtendWith(MockitoExtension.class)
public class ProcessorTest {
	@Mock private UiActions ui;
	private AnswerServices ansHandler;
	private ProcessorActions processor;
	private ProcessorTestHelper helper;

	@BeforeEach
	void setUp(){
		ProcessorBoundary processorController = new ProcessorController();
		ansHandler = new AnswerHandler();
		processor = processorController.processorActions(ui,  ansHandler);
		helper = new ProcessorTestHelper();
	}

	@ParameterizedTest(name = "{index} ==> {0}")
	@CsvSource({
			"SingleValue",
			"Addition",
			"Subtraction",
			"Multiplication"
	})
	void whenRequestIsReceived_ThenCorrectAnswerIsCalculated(String input){
		helper.setTestTypeFields(input);
		Request request = helper.getRequest();
		processor.processRequest(request);

		assertThat(ansHandler.getAnswer().toString(), is(helper.getAnswer(input)));
	}
}
