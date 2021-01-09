
public class RequestHandler implements RequestServices {
	private RequestCache cache = new RequestCache();
	public void sendRequest(String in) {
		cache.addRequest(new Request(in));
	}
	public Request getRequest(){
		return cache.getNextRequest();
	}


}
