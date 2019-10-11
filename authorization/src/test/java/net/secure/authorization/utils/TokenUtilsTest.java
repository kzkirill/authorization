package net.secure.authorization.utils;

import static net.secure.authorization.utils.TokenUtils.checkToken;
import static net.secure.authorization.utils.TokenUtils.getTokent;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Test;

public class TokenUtilsTest {

	@Test
	public void testTokenGood() {
		String userlogin1 ="login@On34e";
		Date expirationDateOneMinuteFromNow = new Date(System.currentTimeMillis() + 60000);
		String goodToken = getTokent(userlogin1, expirationDateOneMinuteFromNow);
		assertTrue(checkToken(goodToken));
	}

	@Test
	public void testTokenChanged() {
		String userlogin1 ="login@On34e";
		Date expirationDateOneMinuteFromNow = new Date(System.currentTimeMillis() + 60000);
		String goodToken = getTokent(userlogin1, expirationDateOneMinuteFromNow);
		assertFalse(checkToken(goodToken + "sdfsdf456"));
	}

	@Test(expected = Throwable.class)
	public void testTokenExpired() {
		String userlogin1 ="login@On34e";
		Date expiredDate = new Date(System.currentTimeMillis() - 60000);
		String expiredToken = getTokent(userlogin1, expiredDate);
		checkToken(expiredToken);
	}

}
