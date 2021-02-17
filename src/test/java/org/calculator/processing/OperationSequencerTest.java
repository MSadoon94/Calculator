package org.calculator.processing;
import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class OperationSequencerTest {

	@Test
	void shouldProcessArithmeticOperationsInARequestBasedOnPriority(){
		OperationSequencer sequencer = new OperationSequencer();
		String input = "2-1+1*2";

		assertThat(sequencer.answer(input), is("3"));
	}
}
