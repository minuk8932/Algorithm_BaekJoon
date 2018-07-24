package dynamic_programming;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 11051번: 이항계수 2
 *
 *	@see https://www.acmicpc.net/problem/11052/
 *
 */
public class Boj11051 {
	private static final int MOD = 10_007;
	private static final int INF = 1_001;
	
	private static long[][] dp = new long[INF][INF];
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		System.out.println(combine(N, K) % MOD);	// 조합 메소드를 통한 이항계수 구하기
	}
	
	private static long combine(int n, int k) {
		if(k == 0 || n == k) return 1;			// 0, 1 의경우 조합의 정의에 따라 1
		if(dp[n][k] > 0) return dp[n][k] % MOD;	// dp 배열에 값이 존재하는 경우
		
		return dp[n][k] = (combine(n-1, k-1) + combine(n-1, k)) % MOD; // 처음 계산하는 조합인 경우 페르마 소정리 이용
	}
}
