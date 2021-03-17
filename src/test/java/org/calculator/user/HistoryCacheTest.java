package org.calculator.user;
import org.calculator.common.Request;
import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class HistoryCacheTest {

	@Test
	void shouldStoreInput(){
		HistoryCache cache = new HistoryCache();
		Request request = new Request("2");
		cache.addRequest(request);
		assertThat(cache.previous().hashCode(), is(equalTo(request.hashCode())));
	}

}
