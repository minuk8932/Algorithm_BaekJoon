package hashing;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 
 * 	@author minchoba
 *	백준 10927번: MD5
 *
 *	@see https://www.acmicpc.net/problem/10927/
 *
 */
public class Boj10927 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String plainText = br.readLine();
		
		System.out.println(encoding(plainText));
	}
	
	private static StringBuilder encoding(String plain) throws NoSuchAlgorithmException {
		StringBuilder MD5 = new StringBuilder();
		
		MessageDigest md = MessageDigest.getInstance("MD5");		// 암호화 결함이 발견되어 SHA와 같은 다른 알고리즘 사용을 권장함
																	// 메세지 축약 알고리즘, 파일 무결성 검사에 사용됨
		md.update(plain.getBytes());
		byte[] byteData = md.digest();						// 입력된 스트링을 바이트로 변환
		
		for(int i = 0; i < byteData.length; i++) {
			MD5.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));		// MD5는 16진수 32자리
		}
		
		return MD5;
	}
}
