public class RequestFormatter{

	public double[] format(String[] input) {
		double[] doubles = new double[input.length];
		for (int i = 0; i < input.length; i++){
			doubles[i] = Double.parseDouble(input[i]);
		}
		return doubles;
	}
}