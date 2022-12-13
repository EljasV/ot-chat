package veijalainen.eljas.otharjoitustyo.util;

/**
 * A utility class to make it easier to understand code where something is not correct.
 * Contains information about if the Result is successful or not.
 * @param <T> The expected type, which otherwise would be used in place of this class. Can be {@link Void} type if not needed.
 * @param <E> Error type, containing information on what went wrong. Can be {@link Void} type if not needed.
 */
public class Result<T, E> {
	boolean success;
	T t;
	E e;

	/**
	 * Get the {@link T} type, if this result is successful.
	 * @return Returns an object supplied in making this object, if successful.
	 */
	public T get() {
		return t;
	}

	/**
	 * Get the {@link E} type, if this result is unsuccessful.
	 * @return Returns an object supplied in making this object, if unsuccessful.
	 */
	public E getError() {
		return e;
	}

	/**
	 * Makes a new successful result object.
	 * @param t The object the new {@link Result} will contain
	 * @return The new {@link Result}
	 * @param <T> Type of successful result
	 * @param <E> Error type of unsuccessful result
	 */
	public static <T, E> Result<T, E> successful(T t) {
		Result<T, E> result = new Result<>();
		result.t = t;
		result.e = null;
		result.success = true;
		return result;
	}

	/**
	 * Makes a new unsuccessful result object.
	 * @param e The error the new {@link Result} will contain
	 * @return The new {@link Result}
	 * @param <T> Type of successful result
	 * @param <E> Error type of unsuccessful result
	 */
	public static <T, E> Result<T, E> unsuccessful(E e) {
		Result<T, E> result = new Result<>();
		result.t = null;
		result.e = e;
		result.success = false;
		return result;
	}

	private Result() {
	}


	/**
	 * Tells if the result is considered successful or unsuccessful.
	 * @return true if the result is successful, false otherwise.
	 */
	public boolean success() {
		return success;
	}
}
