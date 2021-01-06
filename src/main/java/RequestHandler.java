
public class RequestHandler {
	private CacheServices cache;
	public RequestHandler(CacheServices cache){
		this.cache = cache;
	}
	public void sendRequest(String in) {
		cache.addRequest(new Request(in));
	}

}
