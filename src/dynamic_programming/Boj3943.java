package dynamic_programming;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 3943번: 헤일스톤 수열
 *
 *	@see https://www.acmicpc.net/problem/3943/
 *
 */
public class Boj3943 {
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		
		while(T-- > 0){
			int n = Integer.parseInt(br.readLine());
			int max = 0;
			
			if(n == 1)	max = 1;					// 시작수가 1이면 최댓값에 1을 넣
			
			while(n != 1){
				max = Math.max(n, max);		// 1이 아닌경우 각 계산 결과에서 최댓값을 계산해 가면서
				
				if(n % 2 == 0)		n /= 2;			// 짝수인 경우엔 n / 2, 홀수의 경우 n * 3 + 1의 연산을 시행
				else	n = n * 3 + 1;
			}
			
			sb.append(max).append(NEW_LINE);		// 모든 경우가 끝난 후 각 케이스의 최댓값을 버퍼에 저장 
		}
		
		System.out.println(sb.toString());		// 결과값 한번에 출력
	}
}
