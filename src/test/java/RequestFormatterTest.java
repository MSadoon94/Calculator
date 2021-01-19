import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class RequestFormatterTest {

	@Test
	void whenPassedStringArray_ThenWillParseStringArrayToDoubleArrayWithSameValues(){
		RequestFormatter formatter = new RequestFormatter();
		String[] input = {"0", "2", "4", "6"};
		double[] doubles ={0.0, 2.0, 4.0, 6.0};
		double[] returned = formatter.format(input);

		assertThat(returned.getClass(), is(equalTo(double[].class)));
		assertThat(Arrays.equals(returned, doubles), is(true));
	}
}
