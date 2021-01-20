import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.calculator.request.RequestHandler;
import org.calculator.request.RequestServices;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class RequestHandlerTest {

	private String request = "2";
	private RequestServices handler;

	@BeforeEach
	void setUp(){
		handler = new RequestHandler();
	}

	@Test
	void whenRequestIsPassed_ThenShouldSendRequestToCache(){
		handler.addRequest(request);
		assertThat(handler.getRequest().toString(), is(request));
	}

}
