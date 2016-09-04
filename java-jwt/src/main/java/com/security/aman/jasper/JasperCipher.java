package com.security.aman.jasper;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.HashMap;
import java.util.Map;

import com.auth0.jwt.JWTSigner;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.JWTVerifyException;

public class JasperCipher {

	private static final String SECRET;

	// Secret token read it from file
	static {
		SECRET = "my secret";
	}

	private static JWTSigner signer = new JWTSigner(SECRET);

	// Verifier works with default HS256 algorithm
	private static JWTVerifier verifier = new JWTVerifier(SECRET);

	/*
	 * This is for encryption you may not need to use this Test only
	 */

	public String encrypt(Map<String, Object> userDetails) {

		String token = signer.sign(userDetails);
		return token;
	}

	/**
	 * Use this to decrypt Handle Errors
	 * 
	 * @param token
	 * @return
	 */
	public Map<String, Object> decrypt(String token) {

		try {
			Map<String, Object> decoded = verifier.verify(token);

			return decoded;

		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SignatureException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JWTVerifyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	
	/**
	 * Test using main
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Starting --->");

		// Instance of class created to test
		JasperCipher jCipher = new JasperCipher();
		Map<String, Object> userDetails = new HashMap<String, Object>();
		userDetails.put("user", "aman");
		userDetails.put("technology", "Jasper reports");

		System.out.println("Going for Encryption-->");
		// Encrypt the map. Client/PHP should be doing this
		String token = jCipher.encrypt(userDetails);
		// Incoming token
		System.out.println("--- Token value: " + token);
		System.out.println("Going for Decryption -->");
		Map<String, Object> decodedUserDetails = jCipher.decrypt(token);

		// Access values
		System.out.println("Printing Decrypted values -->");
		System.out.println(decodedUserDetails);

		System.out.println("Decoded username is: " + decodedUserDetails.get("user"));
		System.out.println("Decoded technology is: " + decodedUserDetails.get("technology"));
	}

}
