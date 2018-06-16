package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 12606번: Reverse Words (Large)
 *
 *	@see https://www.acmicpc.net/problem/12606/
 *
 */
public class Boj12606 {
	private static final String NEW_LINE = "\n";
	private static final String CASE = "Case #";
	private static final String COLON = ": ";
	private static final String SPACE = " ";
	
	public static void main(String[] args) throws Exception{
		// 버퍼로 인한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		
		for(int i = 1; i < N + 1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int cnt = st.countTokens();
			
			if(cnt > 1) {							// 토큰수가 2개 이상인 경우
				String[] sent = new String[cnt];
				for(int j = cnt - 1; j >= 0; j--) {
					sent[j] = st.nextToken();		// 단어 별 역순 입력
				}
				
				String res = null;
				for(int j = 0; j < cnt; j++) {
					if(j == 0) {								// 결과 변수에 배열을 하나씩 붙여서 담아줌
						res = (sent[j] + SPACE);
					}
					else {
						res += (sent[j] + SPACE);
					}
				}
				
				sb.append(CASE).append(i).append(COLON).append(res).append(NEW_LINE);	// 버퍼에 해당 문자열을 담아줌
			}
			else {
				sb.append(CASE).append(i).append(COLON).append(st.nextToken()).append(NEW_LINE);	// 길이가 1인경우 바로 버퍼에 담아줌
			}
		}
		
		System.out.println(sb.toString());	// 결과 값 한번에 출력
	}
}
