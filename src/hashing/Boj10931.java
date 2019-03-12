package hashing;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 
 * 	@author minchoba
 *	백준 10931번: SHA-384
 *
 *	@see https://www.acmicpc.net/problem/10931/
 *
 */
public class Boj10931 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println(hashing(br.readLine()));
	}
	
	private static String hashing(String str) throws NoSuchAlgorithmException {
		StringBuilder sb = new StringBuilder();
		byte[] crypto = sha384(str);
		
		for(byte b: crypto) {						// 각 바이트를 Hexa로 바꿔줌 (byteToHex)
			sb.append(String.format("%02x", b));
		}
		
		return sb.toString();			// 생성된 암호화 문자열 반환
	}
	
	private static byte[] sha384(String msg) throws NoSuchAlgorithmException{
		MessageDigest md = MessageDigest.getInstance("SHA-384");				// 문자열을 Security Hashing Algorithm 384로 바이트 배열화
		md.update(msg.getBytes());
		
		return md.digest();			// 바이트 배열 반환
	}
}
