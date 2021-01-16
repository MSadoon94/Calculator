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
		String[] values = {"2","2","3","4"};
		ArrayList<String> additionValues = new ArrayList<>(Arrays.asList(values));
		analyzer.analysis(addValue);
		Request request = builder.getBuiltRequest();
		assertThat(request.getAdditions().toString(), is(equalTo(additionValues.toString())));
	}
}
