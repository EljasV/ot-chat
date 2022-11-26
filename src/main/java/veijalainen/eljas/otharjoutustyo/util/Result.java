package veijalainen.eljas.otharjoutustyo.util;

public class Result<T, E> {
	boolean success;
	T t;
	E e;

	public T get() {
		return t;
	}

	public E getError() {
		return e;
	}

	public static <T, E> Result<T, E> successful(T t) {
		Result<T, E> result = new Result<>();
		result.t = t;
		result.e = null;
		result.success = true;
		return result;
	}

	public static <T, E> Result<T, E> unsuccessful(E e) {
		Result<T, E> result = new Result<>();
		result.t = null;
		result.e = e;
		result.success = false;
		return result;
	}

	private Result() {
	}


	public boolean success() {
		return success;
	}
}
