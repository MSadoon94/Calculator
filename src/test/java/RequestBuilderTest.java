import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class RequestBuilderTest {

	@Test
	void whenPassedString_ThenShouldBuildAnalyzedRequestThatContainsSameString(){
		RequestBuilder builder = new RequestBuilder();
		String input = "2.0";
		builder.addOriginalInput(input);
		Request builtRequest = builder.getBuiltRequest();
		assertThat(builtRequest.getClass(), is(Request.class));
		assertThat(builtRequest.toString(), is(equalTo(input)));
	}
}
