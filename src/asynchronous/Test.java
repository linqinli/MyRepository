package asynchronous;

class MyCallback implements Callback {

	public void execute(String result) {
		System.out.println(result);
	}
	
}

public class Test {

	public static void main(String[] args) {
		XMLHttpRequest xmlhttp = new XMLHttpRequest();
		xmlhttp.open("GET", "http://www.baidu.com", true);
		xmlhttp.setCallback(new MyCallback());
		xmlhttp.send();
		System.out.println("Get request has been sent.\n");
	}

}
