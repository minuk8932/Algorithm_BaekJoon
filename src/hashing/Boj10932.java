package hashing;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 
 * 	@author exponential-e
 *	백준 10932번: SHA-512
 *
 *	@see https://www.acmicpc.net/problem/10932/
 *
 */
public class Boj10932 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println(hashing(br.readLine()));
	}
	
	private static String hashing(String plain) throws NoSuchAlgorithmException{
		MessageDigest md = MessageDigest.getInstance("SHA-512");
		md.update(plain.getBytes());
		
		return byteToHex(md.digest());
	}
	
	private static String byteToHex(byte[] arr) {
		StringBuilder sb = new StringBuilder();
		
		for(byte b: arr) {
			sb.append(String.format("%02x", b));
		}
		
		return sb.toString();
	}
}
