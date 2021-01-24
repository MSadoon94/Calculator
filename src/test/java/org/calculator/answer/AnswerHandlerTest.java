package org.calculator.answer;

import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class AnswerHandlerTest {

	@Test
	void whenAnswerIsPassed_ThenShouldSendToAnswerCache(){
		String answer = "2";
		AnswerServices handler = new AnswerHandler();
		handler.addAnswer(answer);

		assertThat(handler.getAnswer().toString(), is(equalTo(answer)));
	}
}
