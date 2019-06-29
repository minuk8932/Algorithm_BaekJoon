package math.modular_inverse;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author exponential-e
 *	백준 11401번: 이항계수 3
 *
 *	@see https://www.acmicpc.net/problem/11401/
 *
 */
public class Boj11401 {
	private static final int MOD = 1_000_000_007;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		System.out.println(getCombination(N, K));
	}
	
	private static long getCombination(int n, int k) {
		if(k == 0 || n == k) return 1;							// nCo, nCn
		long nFactorial = 1;
		
		for(int i = 0; i < n; i++) {
			nFactorial = ((i + 1) * (nFactorial % MOD)) % MOD;	// n!
		}

		long[] inverse = new long[n + 1];
		inverse[n] = getNInverse(nFactorial, MOD - 2);					// n!으로 n번째 역원 구하기
		
		for(int i = n - 1; i > 0; i--) {
			inverse[i] = ((inverse[i + 1] % MOD) * (i + 1)) % MOD;
		}
		
		long result = ((nFactorial % MOD) * (inverse[n - k] % MOD)) % MOD;		// n! / (n - k)!		
		return (result * inverse[k]) % MOD;										// n! / ((n - k)! * k!) = nCk
	}
	
	private static long getNInverse(long f, long m) {
		long res = 1;
		
		while(m > 0) {
			if(m % 2 == 1) res = (f * (res % MOD)) % MOD;		// m이 홀수 -> n! * res
			
			f = (f * f) % MOD;									// f 값 연산을 통한 분할 정복
			m /= 2;
		}
		
		return res;
	}
}
