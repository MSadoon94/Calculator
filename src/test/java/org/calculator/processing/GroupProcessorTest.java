package org.calculator.processing;

import org.calculator.common.Request;
import org.calculator.extraction.ExtractionController;
import org.calculator.extraction.ExtractorUtilities;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class GroupProcessorTest {

	@Test
	void shouldReturnArithmeticAnswerOfTargetGroup(){
		ExtractorUtilities extractor = new ExtractionController().groupExtractor();
		GroupProcessor processor = new GroupProcessor(extractor);

		assertThat(processor.answer(new Request("2-1+1*2")).input(), is("3"));
	}

}
