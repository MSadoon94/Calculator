import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class RequestAnalyzerTest {
	private RequestAnalyzer analyzer;

	@BeforeEach
	void setUp(){
		analyzer = new RequestAnalyzer();
	}
	@Test
	void whenRequestIsSingleValue_ThenWillReturnRequest(){
		Request single = new Request("2");
		AnalyzedRequest analyzed = (AnalyzedRequest) analyzer.analysis(single);
		assertThat(analyzed.toString(), is(equalTo(single.toString())));
	}
	@Test
	void whenRequestHasAddition_ThenWillCreateAnalyzedRequestWithExtractedAddition(){
		AnalyzedRequest analyzed = (AnalyzedRequest) analyzer.analysis(new Request("2+2-2"));
		assertThat(analyzed.getAdditions(), is(equalTo("2+2")));
	}
}
