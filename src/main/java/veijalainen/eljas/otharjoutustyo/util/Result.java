package veijalainen.eljas.otharjoutustyo.util;

public class Result<T, ET> {
	boolean success;
	T t;
	ET et;

	public T get() {
		return t;
	}

	public ET getError() {
		return et;
	}

	public static <T, ET> Result<T, ET> successful(T t) {
		Result<T, ET> result = new Result<>();
		result.t = t;
		result.et = null;
		result.success = true;
		return result;
	}

	public static <T, ET> Result<T, ET> unsuccessful(ET et) {
		Result<T, ET> result = new Result<>();
		result.t = null;
		result.et = et;
		result.success = false;
		return result;
	}

	private Result() {
	}


	public boolean success() {
		return success;
	}
}
