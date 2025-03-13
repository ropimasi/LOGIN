package dev.ropimasi.demo.login.utility;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;




public class MySHA512 {

	public static String encrypt(String input) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-512");
			byte[] messageDigest = md.digest(pairShuffle2(input).getBytes(StandardCharsets.UTF_8));
			return bytesParaHex(messageDigest);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}



	private static String bytesParaHex(byte[] bytes) {
		StringBuilder result = new StringBuilder();
		for (byte b : bytes) {
			result.append(String.format("%02x", b));
		}
		return result.toString();
	}



	private static String pairShuffle2(String strIn) {

		if (strIn == null || strIn.isEmpty() || strIn.isBlank()) {
			return "";
		}

		char[] caracteres = strIn.toCharArray();
		int comprimento = caracteres.length;
		char[] resultado = new char[comprimento];
		int inicio = 0;
		int fim = comprimento - 1;
		int indiceResultado = 0;

		while (inicio <= fim) {
			if (inicio != fim) {
				resultado[indiceResultado++] = caracteres[inicio++];
				resultado[indiceResultado++] = caracteres[fim--];
			} else {
				resultado[indiceResultado] = caracteres[inicio];
				break;
			}
		}

		return new String(resultado);
	}

}
