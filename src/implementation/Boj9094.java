package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 9094번: 수학적 호기심
 *
 *	@see https://www.acmicpc.net/problem/9094/
 *
 */
public class Boj9094 {
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			
			int res = 0;
			
			for(int i = 1; i < n; i++) {
				for(int j = i + 1; j < n; j++) {				// 0 ~ n 사이의 숫자 중
					int mod = (i * i + j * j + m) % (i * j);	// 해당 조건의 정수를 만족하는 수의 쌍을 찾음
					
					res = mod == 0 ? res + 1 : res;
				}
			}
			
			sb.append(res).append(NEW_LINE);		// 갯수를 버퍼에 담고
		}
		
		System.out.println(sb.toString());			// 결과 값 한번에 출력
	}
}
