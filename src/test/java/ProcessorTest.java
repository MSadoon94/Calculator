import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

@ExtendWith(MockitoExtension.class)
public class ProcessorTest {
	@Mock private UiBoundary ui;
	private AnswerServices ansHandler;
	private Processor processor;
	private ProcessorTestHelper helper;

	@BeforeEach
	void setUp(){
		ansHandler = new AnswerHandler();
		processor = new Processor(ui,  ansHandler);
		helper = new ProcessorTestHelper();
	}

	@ParameterizedTest(name = "{index} ==> {0}")
	@CsvSource({
			"SingleValue",
			"Addition",
			"Subtraction"
	})
	void whenRequestIsReceived_ThenCorrectAnswerIsCalculated(String input){
		helper.setTestTypeFields(input);
		Request request = helper.getRequest();
		processor.processRequest(request);

		assertThat(ansHandler.getAnswer().toString(), is(helper.getAnswer(input)));
	}
}
