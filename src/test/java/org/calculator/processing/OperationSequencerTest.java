package org.calculator.processing;

import org.calculator.extraction.ExtractionController;
import org.calculator.extraction.Extractor;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class OperationSequencerTest {

	@Test
	void shouldProcessArithmeticOperationsInARequestBasedOnPriority(){
		Extractor extractor = new ExtractionController().multiOperatorExtractor();
		String input = "2-1+1*2" ;
		OperationSequencer sequencer = new OperationSequencer(extractor);

		assertThat(sequencer.answer(input), is("3"));
	}
}
