package org.calculator.processing;

import org.calculator.answer.AnswerHandler;
import org.calculator.answer.AnswerServices;
import org.calculator.common.Operations;
import org.calculator.common.TestHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
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
	private Processor processor;

	@BeforeEach
	void setUp(){
		ansHandler = new AnswerHandler();
		processor = new Processor(ui,  ansHandler);

	}

	@ParameterizedTest(name = "{index} ==> {0}")
	@EnumSource
	void whenRequestIsReceived_ThenCorrectAnswerIsCalculated(TestHelper helper){
		Request request = new Request(helper.input());
		request.setSection(Operations.valueOf(helper.name()), helper.doubles());
		processor.processRequest(request);

		assertThat(ansHandler.getAnswer().toString(), is(helper.answer()));
	}
}
