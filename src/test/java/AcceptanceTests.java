
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class AcceptanceTests {


	@Test
	public void When_NumberIsEntered_Then_ThatSameNumberWillBeReturned(){
		userRequestSentFromUIToProcessor();
		inputProcessedIntoAnswer();
		answerSentFromProcessorToUI();
		answerDisplayedToUser();

	}

	private void userRequestSentFromUIToProcessor(){
		UI ui = new UI();
		RequestCache cache = new RequestCache();
		String in = "2";
		ui.sendRequest(in,cache);
		assertThat(cache.getNextRequest().request, is(in));
	}

	private void inputProcessedIntoAnswer() {}

	private void answerSentFromProcessorToUI() {}

	private void answerDisplayedToUser() {}






}
