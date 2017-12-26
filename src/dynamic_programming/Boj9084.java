package dynamic_programming;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * @author minchoba
 *
 *	백준 9084번 : 동전
 *	
 *	@see https://www.acmicpc.net/problem/9084
 *
 */

public class Boj9084 {
	private static final String SPACE = " ";
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		
		while(T-- > 0){
			int N = Integer.parseInt(br.readLine());
			int[] coins = new int[N + 1];
			
			StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
			for(int i = 1; i < N + 1; i++){
				coins[i] = Integer.parseInt(st.nextToken());				
			}
			
			int cost = Integer.parseInt(br.readLine());			// 구할 비용
			int[] dp = new int[cost + 1];									// 비용에 따른 배열 크기 설정
																					// 소지하고 있는 동전 가치(coins[])을 통해 값이 1~cost까지 증가 할 때 몇개 씩 필요한지 검사
			
			dp[0] = 1;
			
			for(int i = 1; i < N + 1; i++){
				for(int j = coins[i]; j < cost + 1; j++){
					dp[j] += dp[j - coins[i]];								// dp 배열을 1씩 추가하면서 이전 coin 배열에 있던 값(해당 코인을 통해 만들 수 있는 값의 경우의 수)
																					//	만큼 뺀 것의 배열, 즉 coin에 할당 되어있는 값 마다의 경우의 수를 계산하여 dp 배열에 입력 
				}
			}
		
			sb.append(dp[cost]).append(NEW_LINE);
		}
		br.close();
		
		System.out.println(sb.toString());							// 결과 출력
	}
}