package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 9289번: Morse Code
 *
 *	@see https://www.acmicpc.net/problem/9289/
 *
 */
public class Boj9289 {
	private static final String[] MORSE = {".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", ".."
											, ".---", "-.-", ".-..", "--", "-.", "---", ".--.", "--.-", ".-."
											, "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--.."};
	
	private static final String CASE = "Case ";
	private static final String COLON = ": ";
	private static final String NEW_LINE = "\n";
	
	private static HashMap<String, Character> hm = new HashMap<>();
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < 26; i++) {			// 모스부호에 해당하는 알파벳을 담아줌
			hm.put(MORSE[i], (char) (i + 65));
		}
		
		for(int t = 0; t < T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String words = null;
			
			for(int i = 0; i < 5; i++) {		// 해쉬맵에서 키에 알맞은 값을 하나씩 꺼내오는 연산
				if(i == 0) {
					words = hm.get(st.nextToken()).toString();		// 첫번째 값은 그대로 할당
				}
				else {
					words += hm.get(st.nextToken());		// 나머지는 이어서 뒤쪽에 붙여줌
				}
			}
			
			sb.append(CASE).append(t + 1).append(COLON).append(words).append(NEW_LINE);	// 모스부호에 해당하는 문자열을 버퍼에 담음
		}
		
		System.out.println(sb.toString());		// 결과값 한번에 출력
	}
}
