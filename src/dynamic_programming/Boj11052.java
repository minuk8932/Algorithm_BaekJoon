package dynamic_programming;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * 		@author minchoba 
 * 		백준 11052번 : 붕어빵 판매하기
 * 
 * 		@see https://www.acmicpc.net/problem/11052
 * 
 */
public class Boj11052 {

	private static int[] dp;
	private static int[] pack;
	private static int N;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		pack = new int[N + 1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= N; i++) {
			pack[i] = Integer.parseInt(st.nextToken());
		}

		dp = new int[N + 1];
		Arrays.fill(dp, -1);

		System.out.println(recursion(N));
	}

	/**
	 *
	 * recursion
	 *
	 * line 53: add next cards & current pack[i]
	 *
	 * @param cards
	 * @return memoization
	 */
	private static int recursion(int cards) {
		if(cards == 0) return 0;

		if(dp[cards] != -1) return dp[cards];
		int result = -1;

		for(int i = 1; i <= cards; i++) {
			result = Math.max(result, recursion(cards - i) + pack[i]);
		}

		return dp[cards] = result;
	}
}
