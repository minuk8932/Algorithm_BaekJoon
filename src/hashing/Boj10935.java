package hashing;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Base64;
import java.util.Base64.Encoder;

/**
 * 
 * 	@author minchoba
 *	백준 10935번: BASE64 인코딩
 *
 *	@see https://www.acmicpc.net/problem/10935/
 *
 */
public class Boj10935 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		base64(br.readLine());
	}
	
	/**
	 * BASE64는 HTML 또는 Email과 같이 문자를 위한 Media에 Binary Data를 포함해야 될 필요가 있을 때,
	 * 포함된 Binary Data가 시스템 독립적으로 동일하게 전송 또는 저장되는걸 보장하기 위해 사용
	 *
	 * 특정 스트링을 암호화하여 서버에 전달했을 때에 #, @과 같은 기호들이 생겨 데이터 전송과 연동에 어려운 부분이 있기 때문에
	 * BASE64를 이용하여 인코딩한 후 디코딩하여 원래의 텍스트로 변환하여 사용
	 *
	 */
	private static void base64(String plainText) {			// BASE64 Encoding
		byte[] target = plainText.getBytes();
		Encoder en = Base64.getEncoder();
		
		System.out.println(new String(en.encode(target)));
	}
}
