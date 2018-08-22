package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 2407번: 조합
 *
 *	@see https://www.acmicpc.net/problem/2407/
 *
 */
public class Boj2407 {
	private static final int INF = 101;
	private static BigInteger[][] dp = new BigInteger[INF][INF];
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		K = K > (N / 2) ? N - K : K;			// 조합의 정의에 따른 K 값 재 설정
		
		System.out.println(combine(N, K).toString());	// 조합 메소드를 통한 결과 출력
	}
	
	/**
	 * 조합 메소드
	 * 
	 */
	private static BigInteger combine(int n, int r) {
		if(r == 0 || n == r) return BigInteger.ONE;		// 해당 조건은 1 반환
		if(dp[n][r] != null) return dp[n][r];			// 결과의 값이 존재하면 배열 값 반환
		
		dp[n][r] = new BigInteger("0");					// 객체 생성
		
		return dp[n][r] = dp[n][r].add(combine(n-1, r-1)).add(combine(n-1, r));		// 조합의 정의
	}
}
