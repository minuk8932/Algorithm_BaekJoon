package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 5363번: 요다
 *
 *	@see https://www.acmicpc.net/problem/5363/
 *
 */
public class Boj5363 {
	private static final int INF = 101;
	private static final String SPACE = " ";
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		while(N-- > 0) {
			String[] yLang = new String[INF];
			String line = br.readLine();
			
			StringTokenizer st = new StringTokenizer(line);
			for(int i = 0; i < yLang.length; i++) {
				try {
					yLang[i] = st.nextToken();		// 다음 토큰이 없을 떄 까지 반복문 실행
				}
				catch(Exception e) {
				}
			}
			
			for(int i = 2; i < yLang.length; i++) {
				if(yLang[i] == null) break;			// 다음 문자로 null이 들어올 경우 반복문 종료
				sb.append(yLang[i]).append(SPACE);	// 2 ~ 마지막 단어까지 버퍼에 담아줌
			}
			
			sb.append(yLang[0]).append(SPACE).append(yLang[1]).append(NEW_LINE);	// 0, 1번 인덱스의 단어를 버퍼에 담아줌
		}
		
		System.out.println(sb.toString());		// 결과값 한번에 출력
	}
}
