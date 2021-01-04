
public interface CacheServices {
	void addRequest(CalculationRequest in);
	CalculationRequest getNextRequest();
}
