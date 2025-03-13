package dev.ropimasi.demo.login.utility;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;




public class MyMD5 {

	public static String encrypt(String input) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] messageDigest = md.digest(pairShuffle1(input).getBytes());
			return bytesToHex(messageDigest);

		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}



	private static String bytesToHex(byte[] bytes) {
		StringBuilder result = new StringBuilder();
		for (byte b : bytes) {
			result.append(String.format("%02x", b));
		}
		return result.toString();
	}



	private static String pairShuffle1(String strIn) {
		if (strIn == null || strIn.isEmpty() || strIn.isBlank()) {
			return "";
		}

		char[] caracteres = strIn.toCharArray();
		int comprimento = caracteres.length;

		for (int i = 0; i < comprimento - 1; i += 2) {
			char temp = caracteres[i];
			caracteres[i] = caracteres[i + 1];
			caracteres[i + 1] = temp;
		}

		return new String(caracteres);
	}
}
