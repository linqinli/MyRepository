package cn.linqinli;

public enum Singleton {
	INSTANCE;
	private Singleton() {
		System.out.println("enum");
	}
	public void execute(String arg) {
		
	}
	public static void main(String[] args) {
		System.out.println("main");
	}
}
