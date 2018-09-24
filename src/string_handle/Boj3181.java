package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 3181번: 줄임말 만들기
 *
 *	@see https://www.acmicpc.net/problem/3181/
 *
 */
public class Boj3181 {
	private static final String[] IGNORE = {"i", "pa", "te", "ni", "niti", "a", "ali", "nego", "no", "ili"};
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int loop = st.countTokens();
		
		for(int i = 0; i < loop; i++) {
			if(i > 0) {
				String tmp = st.nextToken();
				boolean isPassed = false;
				
				for(int j = 0; j < IGNORE.length; j++) {
					if(IGNORE[j].equals(tmp)) {			// 무시 될 문자열이 입력으로 들어오는 경우
						isPassed = true;
						break;
					}
				}
				
				if(isPassed) continue;				// 문자열 무시
				
				sb.append((char) (tmp.charAt(0) - 32));		// 줄임말이 가능한 문자열인 경우
			}
			else {										// 처음에 오는 문자열은 무조건 줄임말 처리
				sb.append((char) (st.nextToken().charAt(0) - 32));
			}
		}
		
		System.out.println(sb.toString());		// 결과 값 한번에 출력
	}
}
