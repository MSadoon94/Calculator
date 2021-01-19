public class RequestAnalyzer {

	private ProcessorControl processor;
	private RequestBuilder builder;
	private RequestFormatter formatter;
	public RequestAnalyzer(ProcessorControl processor, RequestBuilder builder){
		this.processor = processor;
		this.builder = builder;
		formatter = new RequestFormatter();
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
			double[] formattedInput =
					formatStringsToDoubles(input.split("[" + addition + "]"));
			builder.buildAdditionSection(formattedInput);
		}
		if(input.contains(subtraction)){
			double[] formattedInput =
					formatStringsToDoubles(input.split("[" + subtraction + "]"));
			builder.buildSubtractionSection(formattedInput);
		}
	}
	private double[] formatStringsToDoubles(String[] strings){
		return formatter.format(strings);
	}


}
