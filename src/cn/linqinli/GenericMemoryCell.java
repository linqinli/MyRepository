package cn.linqinli;

public class GenericMemoryCell<T> {
	private T storedValue;
	public T read() {
		return storedValue;
	}
	public void write(T value) {
		storedValue = value;
	}
}
