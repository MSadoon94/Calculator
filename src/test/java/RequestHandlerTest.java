import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class RequestHandlerTest {

	private String input = "2";

	@Test
	public void whenRequestIsEntered_ThenShouldSendRequestToCache(){
		RequestHandler handler = new RequestHandler();
		handler.sendRequest(input);
		assertThat(handler.getRequest().toString(), is(input));
	}
}
