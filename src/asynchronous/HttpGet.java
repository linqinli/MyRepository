package asynchronous;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;
import java.util.List;

public class HttpGet implements Runnable {
	private String urlNameString;
	private Callback callback;
	private boolean isAsynchronous;
	
	public HttpGet(String urlNameString, Callback callback, boolean isAsynchronous) {
		this.urlNameString = urlNameString;
		this.callback = callback;
		this.isAsynchronous = isAsynchronous;
	}

	public void send() {
		if(isAsynchronous) {
			Thread thread = new Thread(this);
			thread.start();
		} else {
			sendRequest();
		}
	}

	public void run() {
		sendRequest();
	}

	private void sendRequest() {
		StringBuilder result = new StringBuilder();
		BufferedReader in = null;
		try {
			URL url = new URL(urlNameString);
			URLConnection connection = url.openConnection();
			connection.connect();

			Map<String, List<String>> map = connection.getHeaderFields();
			// 遍历所有的响应头字段
			for (String key : map.keySet()) {
				System.out.println(key + "  " + map.get(key));
			}

			in = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result.append(line);
				result.append('\n');
			}

			if (callback != null) {
				callback.execute(result.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
