package dynamic_programming;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * @author minchoba
 *	백준 2294번 : 동전2
 *
 *	@see https://www.acmicpc.net/problem/2294
 *
 */

public class Boj2294 {
	private static final String SPACE = " ";
	private static final int IMPOSSIBLE = -1;
	private static final int MAX = 100_001;
	
	public static void main(String[] args) throws Exception{
		//버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		int[] coins = new int[n + 1];
		
		for(int i = 1; i < n + 1; i++){										// 소지하고있는 값의 종류를 coin 배열에 입력
			coins[i] = Integer.parseInt(br.readLine());
		}
		br.close();
		
		Arrays.sort(coins);												// 오름차순 정리
		
		int[] dp = new int[k + 1];
		Arrays.fill(dp, MAX);												// 최소값을 구하기 위해 가격의 최대값(10000)+1을 각 배열마다 입력
		dp[0] = 0;
		
		for(int i = 1; i < n + 1; i++){
			for(int j = coins[i]; j < k + 1; j++){							// coin 배열에 저장된 값을 통해 해당 coin내의 값 부터 구하려는 값까지
				dp[j] = Math.min(dp[j], dp[j - coins[i]] + 1);		// coin 배열 내의 값으로 만들 수 있는 가지 수를 체크하는데, 그 중 최솟값을 dp 배열에 할당
																					// 만들어 낼 수 있다면, 해당 dp에 이전 dp값 +1을 하여 그 중 최솟값을 넘겨주는 방식
			}
		}

		if(dp[k] == MAX){
			System.out.println(IMPOSSIBLE);							// 최종까지 갔을 때 만약 주어진 동전들로 최종 값을 만들어 내지 못 할 경우 : -1 출력
		}
		else{
			System.out.println(dp[k]);									// 정상적으로 처리 됐을 경우 : 결과 값 출력
		}
	}
}
