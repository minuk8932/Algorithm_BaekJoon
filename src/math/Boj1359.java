package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 1359번: 복권
 *
 *	@see https://www.acmicpc.net/problem/1359/
 *
 */
public class Boj1359 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		System.out.println(getResult(N, M, K));
	}
	
	private static double getResult(int n, int m, int k) {
		double result = 0;
		
		for(int i = k; i <= m; i++) {
			if(i > m || m - i > n - m) continue;
			result += combination(m, i) * combination(n - m, m - i);		// m개 중 동일한 (1 ~ k)개를 고르는 동시에, 
		}																	// 고르지 않은 (n-m)개 중 고르지 못한 나머지가 존재하는 경우
		
		int nCr = combination(n, m);			// 전체 n개 중 m개를 무작위로 뽑는 경우의 수
		return result / nCr;
	}
	
	private static int combination(int n, int r) {
		if(n == r || r == 0) return 1;
		return combination(n - 1, r - 1) + combination(n - 1, r);
	}
}
