package org.calculator.processing;
import org.calculator.common.Request;
import org.calculator.common.TestHelper;
import org.calculator.request.GroupExtractor;
import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class GroupProcessorTest {

	@Test
	void shouldReturnArithmeticAnswerOfTargetGroup(){
		Request request = new Request(TestHelper.MIXED.input());
		GroupExtractor extractor = new GroupExtractor();
		String[] groups = extractor.extraction(request);
		GroupProcessor processor = new GroupProcessor();

		assertThat(processor.answer(groups[0]), is("3"));
	}
}
