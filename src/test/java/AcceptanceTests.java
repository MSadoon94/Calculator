import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.netbeans.jemmy.operators.*;

import javax.swing.*;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class AcceptanceTests {

	private RequestCache cache;
	private RequestHandler requestHandler;
	private Processor processor;

	private String input = "2";

	private JFrameOperator frameOperator;
	private JTextAreaOperator textAreaOperator;
	private JButtonOperator buttonOperator;


	@BeforeEach
	public void setUp(){
		cache = new RequestCache();
		requestHandler = new RequestHandler(cache);
		processor = new Processor();
		Gui gui = createGui();
		frameOperator = new JFrameOperator("Gui");
		textAreaOperator = new JTextAreaOperator(frameOperator, 0);
		buttonOperator = new JButtonOperator(frameOperator, 0);
	}
	private Gui createGui(){
		JFrame frame = new JFrame("Gui");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
		return new Gui(frame, requestHandler);
	}

	@Test
	public void When_NumberIsEntered_Then_ThatSameNumberWillBeReturned(){
		startInputtingRequest();
		showRequestCacheHasInput();
		showRequestProcessedIntoAnswer();
		hasDisplayedAnswer();
	}
	private void startInputtingRequest(){
		textAreaOperator.enterText(input);
		buttonOperator.push();
	}
	private void showRequestCacheHasInput() {
		assertThat(cache.getNextRequest().toString(), is(equalTo(input)));
	}

	private void showRequestProcessedIntoAnswer() {
		assertThat(processor.getAnswer().toString(), is(equalTo(input)));
	}

	private void hasDisplayedAnswer() {
		assertThat(textAreaOperator.getText().trim(), is(equalTo(input)));
	}















}
