public class UI{
	public void sendRequest(String in, CacheServices cache) {
		CalculationRequest calculation = new CalculationRequest(in);
		cache.addRequest(calculation);
	}
}
