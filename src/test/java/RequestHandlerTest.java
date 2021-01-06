import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class RequestHandlerTest {

	private String input = "2";

	@Test
	public void whenRequestIsEntered_ThenShouldSendRequestToCache(){
		RequestCache cache = new RequestCache();
		RequestHandler requestHandler = new RequestHandler(cache);
		requestHandler.sendRequest(input);
		assertThat(cache.getNextRequest().toString(), is(input));
	}
}
