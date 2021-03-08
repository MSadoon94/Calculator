package org.calculator.user;
import org.calculator.common.Request;
import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class InputCacheTest {

	@Test
	void shouldStoreInput(){
		InputCache cache = new InputCache();
		Request request = new Request("2");
		cache.addRequest(request);
		assertThat(cache.getRequest(request.hashCode()).hashCode(), is(equalTo(request.hashCode())));
	}

}
