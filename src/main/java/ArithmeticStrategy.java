public class ArithmeticStrategy implements OperationStrategy{
	private Request request;
	public double execute(Request request) {
		this.request = request;
		return subtract() + addition();
	}
	private double subtract(){
		if(request.getSubtractions() == null){
			return 0;
		}
		double[] subtractions = request.getSubtractions();
		double value = subtractions[0];
		for (int i = 1; i < subtractions.length; i++){
			value -= subtractions[i];
		}
		return value;
	}

	private double addition(){
		if(request.getAdditions() == null){
			return 0;
		}
		double[] additions = request.getAdditions();
		double value = additions[0];
		for (int i = 1; i < additions.length; i++){
			value += additions[i];
		}
		return value;
	}
}
