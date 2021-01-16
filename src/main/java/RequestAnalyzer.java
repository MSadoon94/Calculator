import java.util.ArrayList;
import java.util.Arrays;


public class RequestAnalyzer {

	private ProcessorControl processor;
	private RequestBuilder builder;
	public RequestAnalyzer(ProcessorControl processor, RequestBuilder builder){
		this.processor = processor;
		this.builder = builder;
	}

	public void analysis(String input){
		builder.addOriginalInput(input);
		String addition = "+";
		if (input.contains(addition)){
			builder.buildAdditionSection(new ArrayList<>(Arrays.asList(input.split("[" + addition + "]"))));
		}
		sendToProcessor();
	}
	private void sendToProcessor(){
		AnalyzedRequest request = builder.getBuiltRequest();
		processor.processRequest(request);
	}


}
