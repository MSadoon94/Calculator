import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.stream.DoubleStream;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

@ExtendWith(MockitoExtension.class)
public class ProcessorTest {
	@Mock private UiBoundary ui;
	private static HashMap<String, String> answers = new HashMap<>();
	private static HashMap<String, String> values = new HashMap<>();
	private AnswerServices ansHandler;
	private Processor processor;


	private String in = "2+2";
	private String correctAnswer = "4.0";

	@BeforeEach
	void setUp(){
		ansHandler = new AnswerHandler();
		processor = new Processor(ui,  ansHandler);
		setValues();
		setAnswers();
	}

	private void setValues() {
		values.put("SingleValue", "2.0");
		values.put("Addition", "2+2");
	}

	@ParameterizedTest(name = "{index} ==> {0}")
	@CsvSource({
			"SingleValue",
			"Addition"
	})
	void whenRequestIsReceived_ThenCorrectAnswerIsCalculated(String input){
		String value = values.get(input);
		Answer answer = processor.calculate(value);

		assertThat(answer.toString(), is(answers.get(value)));
	}

	@Test
	void whenAnswerIsCreated_ThenAnswerWillBeSentToCache(){
		processor.calculate(in);

		assertThat(ansHandler.getAnswer().toString(), is(correctAnswer));
	}
	private void setAnswers(){
		answers.put("2.0", correctAnswer(DoubleStream.of(2.0)));
		answers.put("2+2", correctAnswer(DoubleStream.of(2, 2)));
	}
	private String correctAnswer(DoubleStream inputs){
		double answer = inputs.sum();
		return String.valueOf(answer);
	}
}
