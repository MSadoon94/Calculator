import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

@ExtendWith(MockitoExtension.class)
public class ProcessorTest {

	@Mock private UiBoundary ui;
	private AnswerServices ansHandler;
	private Processor processor;

	private String in = "2+2";
	private String correctAnswer = "4.0";

	@BeforeEach
	void setUp(){
		ansHandler = new AnswerHandler();
		processor = new Processor(ui,  ansHandler);
	}

	@Test
	public void whenAdditionRequestIsReceived_ThenAdditionAnswerIsCalculated(){
		Answer answer = processor.calculate(in);

		assertThat(answer.toString(), is(correctAnswer));
	}

	@Test
	public void whenAnswerIsCreated_ThenAnswerWillBeSentToCache(){
		processor.calculate(in);

		assertThat(ansHandler.getAnswer().toString(), is(correctAnswer));
	}
}
