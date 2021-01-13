public interface UiBoundary {
	void attachObserver(Observer observer);
	Request getRequest();
	void setResponse(String response);
}
