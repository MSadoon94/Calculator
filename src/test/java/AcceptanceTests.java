import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.netbeans.jemmy.operators.JButtonOperator;
import org.netbeans.jemmy.operators.JFrameOperator;
import org.netbeans.jemmy.operators.JTextAreaOperator;

import javax.swing.*;
import java.util.HashMap;
import java.util.stream.DoubleStream;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class AcceptanceTests {

	private static HashMap<String, DoubleStream> answers = new HashMap<>();

	private RequestServices reqHandler;
	private Processor processor;

	private String input = "0";

	private JFrameOperator frameOperator;
	private JTextAreaOperator textAreaOperator;
	private JButtonOperator buttonOperator;

	@DisplayName("TestResults")
	@ParameterizedTest(name = "{index} ==> {0}")
	@CsvSource({
			"WhenNumberIsEntered_ThenThatSameNumberWillBeReturned, 2.0",
			"WhenUserRequestsAddition_ThenInputtedValuesAreAdded, 2+2"
	})
	void testingFixture(String test, String aInput){
		//Although unused, test variable is needed so test name isn't assigned to input.
		input = aInput;
		setUp();
		startInputtingRequest();
		showRequestCacheHasInput();
		showRequestProcessedIntoAnswer();
		hasDisplayedAnswer();
	}
	private void setUp(){
		reqHandler = new RequestHandler();
		processor = new Processor(reqHandler);
		createGui();
		setOperators();
		setAnswers();
	}
	private Gui createGui(){
		JFrame frame = new JFrame(input);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
		return new Gui(frame, reqHandler);
	}
	private void setOperators(){
		frameOperator = new JFrameOperator(input);
		textAreaOperator = new JTextAreaOperator(frameOperator, 0);
		buttonOperator = new JButtonOperator(frameOperator, 0);
	}
	private void setAnswers(){
		answers.put("2.0", DoubleStream.of((2.0)));
		answers.put("2+2", DoubleStream.of(2, 2));
	}
	private void startInputtingRequest(){
		textAreaOperator.enterText(input);
		buttonOperator.push();
	}
	private void showRequestCacheHasInput() {
		assertThat(reqHandler.getRequest().toString(), is(equalTo(input)));
	}

	private void showRequestProcessedIntoAnswer() {
		assertThat(processor.calculate().toString(), is(equalTo(correctAnswer(answers.get(input)))));
	}
	private String correctAnswer(DoubleStream inputs){
		double answer = inputs.sum();
		return String.valueOf(answer);
	}
	private void hasDisplayedAnswer() {
		assertThat(textAreaOperator.getText().trim(), is(equalTo(input)));
	}
}
