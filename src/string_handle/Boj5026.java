package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 5026번: 박사과정
 *
 *	@see https://www.acmicpc.net/problem/5026/
 *
 */
public class Boj5026 {
	private static final String NP = "P=NP";
	private static final String PASS = "skipped";
	private static final String NEW_LINE = "\n";
	private static final String PLUS = "+";
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		
		while(N-- > 0) {
			String line = br.readLine();
			
			if(line.equals(NP)) {					// 입력받은 값이 P=NP인 경우
				sb.append(PASS).append(NEW_LINE);	// skipped 출력 후 다음 반복문 진행
				continue;
			}
			
			StringTokenizer st = new StringTokenizer(line, PLUS);							// 연산이 들어온경우 +를 기준으로 자르고
			int sum = Integer.parseInt(st.nextToken()) + Integer.parseInt(st.nextToken());	// 자른 값을 합해
			
			sb.append(sum).append(NEW_LINE);		// 합을 버퍼에 담아줌
		}
		
		System.out.println(sb.toString());		// 결과값 한번에 출력
	}
}
