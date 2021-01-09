import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class ProcessorTest {

	@Test
	public void whenAdditionRequestIsReceived_ThenAdditionAnswerIsCalculated(){
		String in = "2+2";
		RequestServices handler = new RequestHandler();
		Processor processor = new Processor(handler);
		handler.sendRequest(in);
		String correctAnswer = "4.0";
		Answer answer = processor.calculate();
		assertThat(answer.toString(), is(correctAnswer));
	}
}
