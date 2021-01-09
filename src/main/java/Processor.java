import java.text.DecimalFormat;
import java.util.HashMap;

public class Processor {
	private RequestServices reqServices;
	public Processor(RequestServices services){
		reqServices = services;
	}

	public Answer calculate(){
		Request request = reqServices.getRequest();
		return request.toString().contains("+") ? addition(request) : uncalculated(request);
	}

	private Answer addition(Request request) {
		DecimalFormat df = new DecimalFormat("#.00");
		double calculated = 0;
		String[] parsed = request.toString().split("\\+",0);
		for (String value : parsed){
			calculated += Double.parseDouble(value);
			df.format(calculated);
		}

		return new Answer(String.valueOf(calculated));
	}
	private Answer uncalculated(Request request){
		return new Answer(request.toString());
	}

}
