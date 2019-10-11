package net.secure.authorization.utils;

import static org.junit.Assert.*;

import org.junit.Test;
import static net.secure.authorization.utils.PasswordUtils.*;

public class PasswordUtilsTest {

	private static final int saltLength = 5;

	@Test
	public void testPassword() {
		String salt1 = getSalt(saltLength);
		String password1 = "pAssWord$Num1";
		String securePassword1 = generateSecurePassword(password1, salt1);
		assertTrue(verifyUserPassword(password1, securePassword1, salt1));
		assertFalse(verifyUserPassword(password1 + "12sdf", securePassword1, salt1));
		assertFalse(verifyUserPassword(password1, securePassword1, salt1 + "12sdf"));
		assertFalse(verifyUserPassword(password1, securePassword1 + "12sdf", salt1));
	}

}
