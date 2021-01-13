public class RequestObserver implements Observer {

	private Processor processor;

	public RequestObserver(Processor processor){
		this.processor = processor;
	}

	public void update(String update) {
		processor.calculate(update);
	}
}
