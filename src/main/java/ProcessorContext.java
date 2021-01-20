public class ProcessorContext {
	private OperationStrategy strategy;
	public void setStrategy(OperationStrategy strategy){
		this.strategy = strategy;
	}
	public double executeStrategy(Request request){
		return strategy.execute(request);
	}
}
