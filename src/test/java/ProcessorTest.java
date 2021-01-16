import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.DoubleStream;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

@ExtendWith(MockitoExtension.class)
public class ProcessorTest {
	@Mock private UiBoundary ui;
	private static HashMap<String, String> answers = new HashMap<>();
	private static HashMap<String, Request> values = new HashMap<>();
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


	@ParameterizedTest(name = "{index} ==> {0}")
	@CsvSource({
			"SingleValue",
			"Addition"
	})
	void whenRequestIsReceived_ThenCorrectAnswerIsCalculated(String input){
		Request request = values.get(input);
		processor.processRequest(request);

		assertThat(ansHandler.getAnswer().toString(), is(answers.get(input)));
	}

	private void setValues() {
		Request singleValue = new Request("2.0");
		Request addition = new Request("2+2");
		String[] addArray = {"2", "2"};
		ArrayList<String> addList = new ArrayList<>(Arrays.asList(addArray));
		addition.setAdditions(addList);
		values.put("SingleValue", singleValue);
		values.put("Addition", addition);
	}

	private void setAnswers(){
		answers.put("SingleValue", correctAnswer(DoubleStream.of(2.0)));
		answers.put("Addition", correctAnswer(DoubleStream.of(2, 2)));
	}
	private String correctAnswer(DoubleStream inputs){
		double answer = inputs.sum();
		return String.valueOf(answer);
	}
}
