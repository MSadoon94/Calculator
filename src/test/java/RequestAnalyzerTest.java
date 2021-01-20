import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.calculator.processing.ProcessorControl;
import org.calculator.common.Request;
import org.calculator.request.RequestAnalyzer;
import org.calculator.request.RequestBuilder;

import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@ExtendWith(MockitoExtension.class)
public class RequestAnalyzerTest {
	@Mock private ProcessorControl processor;
	private RequestAnalyzer analyzer;
	private RequestBuilder builder;
	private double[] values = {2.0,2.0,3.0,4.0};

	@BeforeEach
	void setUp(){
		builder = new RequestBuilder();
		analyzer = new RequestAnalyzer(processor, builder);
	}
	@Test
	void whenStringIsSingleValue_ThenWillReturnAnalyzedRequestWithExtractedSingleValue(){
		String singleValue = ("2.0");
		analyzer.analysis(singleValue);
		Request request = builder.getBuiltRequest();
		assertThat(request.toString(), is(equalTo(singleValue)));
	}
	@Test
	void whenStringHasAddition_ThenWillReturnAnalyzedRequestWithExtractedAdditionValues(){
		String addValue = ("2+2+3+4");
		analyzer.analysis(addValue);
		Request request = builder.getBuiltRequest();
		assertThat(Arrays.toString(request.getAdditions()), is(equalTo(Arrays.toString(values))));
	}
	@Test
	void whenStringHasSubtraction_ThenWillReturnAnalyzedRequestWithExtractedSubtractionValues(){
		String minusValue = ("2-2-3-4");
		analyzer.analysis(minusValue);
		Request request = builder.getBuiltRequest();
		assertThat(Arrays.toString(request.getSubtractions()), is(equalTo(Arrays.toString(values))));
	}
}
