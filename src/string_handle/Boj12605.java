package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 12605번: 단어순서 뒤집기
 *
 *	@see https://www.acmicpc.net/problem/12605/
 *
 */
public class Boj12605 {
	private static final String CASE = "Case #";
	private static final String COLON = ": ";
	private static final String NEW_LINE = "\n";
	private static final String SPACE = " ";
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < N; i++){
			StringTokenizer st = new StringTokenizer(br.readLine());
			String[] words = new String[st.countTokens()];
			
			for(int j = 0; j < words.length; j++) {		// 문자열을 공백으로 구분해 받고
				words[j] = st.nextToken();
			}
			
			sb.append(CASE).append(i + 1).append(COLON);
			for(int j = words.length - 1; j >= 0; j--) {	// 역순으로 문자열을 버퍼에 담음
				sb.append(words[j]).append(SPACE);
			}
			
			sb.append(NEW_LINE);
		}
		
		System.out.println(sb.toString());		// 결과값 한번에 출력
	}
}
