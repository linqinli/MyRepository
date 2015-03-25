package asynchronous;

public class XMLHttpRequest {
	public enum Method {
		GET, POST
	}

	private Method method;
	private String url;
	private boolean isAsynchronous;
	private Callback callback;

	public void open(String method, String url) {
		open(method, url, true);
	}

	public void open(String methodStr, String url, boolean isAsynchronous) {
		this.url = url;
		this.isAsynchronous = isAsynchronous;
		this.method = methodStr.equalsIgnoreCase(Method.GET.toString()) ? Method.GET
				: Method.POST;
	}

	public void setCallback(Callback callback) {
		this.callback = callback;
	}

	public void send() {
		switch (method) {
		case GET:
			sendGet();
			break;
		case POST:
			//TODO
			break;
		}
	}

	private void sendGet() {
		HttpGet httpGet = new HttpGet(url, callback, isAsynchronous);
		httpGet.send();
	}
}
