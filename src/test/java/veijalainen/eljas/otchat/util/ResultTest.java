package veijalainen.eljas.otchat.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class ResultTest {


	@Test
	public void getReturnsCorrectValue() {
		Result<Integer, Void> result = Result.successful(1);
		assertEquals(1, (int) result.get());
	}

	@Test
	public void getErrorReturnsCorrectValue() {
		String s = "ERROR";
		Result<Void, String> result = Result.unsuccessful(s);
		assertEquals(s, result.getError());
	}

	@Test
	public void successfulMakesSuccessfulResult() {
		Result<Void, Void> result = Result.successful(null);
		assertTrue(result.success());
	}

	@Test
	public void unsuccessfulMakesNonSuccessfulResult() {
		Result<Void, Void> result = Result.unsuccessful(null);
		assertFalse(result.success());
	}

}