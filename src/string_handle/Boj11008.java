package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 11008번: 복붙의 달인
 *
 *	@see https://www.acmicpc.net/problem/11008/
 *
 */
public class Boj11008 {
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		// 버퍼릁 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String s = st.nextToken();
			String p = st.nextToken();
			
			int totalLeng = s.length();	// 각 문자열의 길이를 저장
			int setLeng = p.length();
			int res = 0;
			
			s = s.replace(p, "");		// s에서 p와 같은 문자열을 모두 제거
			
			int implyLeng = s.length();		// 제거하고 남은 길이를 저장
			
			res = (totalLeng - implyLeng) / setLeng + implyLeng;	// 전체 길이에서 제거하고 남은 길이를 뺸 후 그 결과를 비교문자열의 길이로 나누면
																	// 중복 문자열의 갯수가 나옴 이에 제거하고 남은 길이를 더하면 결과값
			sb.append(res).append(NEW_LINE);		// 최종 값을 버퍼에 담은 후
		}
		
		System.out.println(sb.toString());		// 결과값 한번에 출력
	}
}
