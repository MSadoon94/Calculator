import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@ExtendWith(MockitoExtension.class)
public class RequestAnalyzerTest {
	@Mock private ProcessorControl processor;
	private RequestAnalyzer analyzer;
	private RequestBuilder builder;
	private String[] values = {"2","2","3","4"};

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
