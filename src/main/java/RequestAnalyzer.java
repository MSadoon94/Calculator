public class RequestAnalyzer {

	private ProcessorControl processor;
	private RequestBuilder builder;
	public RequestAnalyzer(ProcessorControl processor, RequestBuilder builder){
		this.processor = processor;
		this.builder = builder;
	}

	public void analysis(String input){
		builder.addOriginalInput(input);
		buildRequest(input);
		sendToProcessor();
	}
	private void sendToProcessor(){
		Request request = builder.getBuiltRequest();
		processor.processRequest(request);
	}
	private void buildRequest(String input){
		String addition = "+";
		String subtraction = "-";
		if (input.contains(addition)){
			builder.buildAdditionSection(
					input.split("[" + addition + "]"));
		}
		if(input.contains(subtraction)){
			builder.buildSubtractionSection(
					input.split("[" + subtraction + "]"));
		}
	}


}
