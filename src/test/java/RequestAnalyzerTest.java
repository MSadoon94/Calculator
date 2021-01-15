import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class RequestAnalyzerTest {
	private RequestAnalyzer analyzer;

	@BeforeEach
	void setUp(){
		analyzer = new RequestAnalyzer();
	}
	@Test
	void whenStringIsSingleValue_ThenWillReturnAnalyzedRequestWithExtractedSingleValue(){
		String singleValue = ("2.0");
		AnalyzedRequest analyzed = analyzer.analysis(singleValue);
		assertThat(analyzed.toString(), is(equalTo(singleValue)));
	}
	@Test
	void whenStringHasAddition_ThenWillReturnAnalyzedRequestWithExtractedAdditionValues(){
		String input = ("2+2+3+4");
		String[] values = {"2","2","3","4"};
		ArrayList<String> additionValues = new ArrayList<>(Arrays.asList(values));
		AnalyzedRequest analyzed = analyzer.analysis(input);
		assertThat(analyzed.getAdditions().toString(), is(equalTo(additionValues.toString())));
	}
}
