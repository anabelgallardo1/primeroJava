package us.lsi.tools;

public class MutableType<T> {
	
	public static <T> MutableType<T> of(T e) {
		return new MutableType<T>(e);
	}
	
	public T value;
	
	public T newValue(T newValue) {
		T old = value;
		this.value = newValue;
		return old;
	}
	
	private MutableType(T e) {
		super();
		this.value = e;
	}
	@Override
	public String toString() {
		return value.toString();
	}
	

}
