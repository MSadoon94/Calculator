import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

public class RequestAnalyzer {

	public AnalyzedRequest analysis(String input){
		AnalyzedRequest request = new AnalyzedRequest(input);
		String addition = "+";
		if (input.contains(addition)){
			request.setAdditions(new ArrayList<>(Arrays.asList(input.split("[" + addition + "]"))));
		}
		return request;
	}

}
