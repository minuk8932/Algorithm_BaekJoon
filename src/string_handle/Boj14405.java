package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 14405번: 피카츄
 *
 *	@see https://www.acmicpc.net/problem/14405/
 *
 */
public class Boj14405 {
	private static final String[] POKE = {"pi", "ka", "chu"};
	private static final String SPACE = " ";
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String word = br.readLine();
		boolean res = true;

		for(final String REMOVE : POKE) {			// 해당 음절 제거
			word = word.replaceAll(REMOVE, SPACE);
		}
		
		for(char tmp: word.toCharArray()) {
			if(tmp >= 'a' && tmp <= 'z') {		// 제거된 음절에 아직 알파벳 소문자가 포함되었다면
				res = false;					// 결과 변수에 거짓을 담고 반복문 종료
				break;
			}
		}
		
		System.out.println(res ? "YES" : "NO");		// 결과 변수에 따른 결과값 출력
	}
}
