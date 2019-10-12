package net.secure.authorization.utils;

import java.security.Key;
import java.util.Date;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;

public class TokenUtils {

	private static final String hostURL = "http://localhost";
	private static final String SUBJ_USER = "users/";
	private static Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

	public static String getToken(String userlogin, Date expirationDate) {
		String jwt = Jwts.builder().setIssuer(hostURL).setSubject(SUBJ_USER + userlogin).setExpiration(expirationDate)
//				.put("scope", "self api/buy")
				.signWith(key, SignatureAlgorithm.HS256).compact();
		return jwt;

	}

	public static boolean checkToken(String jwt) {
		String subject = "HACKER";

		try {
			Jws<Claims> jwtClaims = Jwts.parser().setSigningKey(key).parseClaimsJws(jwt);
			subject = jwtClaims.getBody().getSubject();
			// OK, you can trust this JWT
			return true;
		} catch (ExpiredJwtException e) {
			e.printStackTrace();
			return false;
		} catch (SignatureException e) {
			// don't trust this JWT!
			e.printStackTrace();
			return false;
		}

	}
}
