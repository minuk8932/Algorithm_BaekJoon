package math.modular_inverse;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 15791번: 세진이의 미팅
 *
 *	@see https://www.acmicpc.net/problem/15791/
 *
 */
public class Boj15791 {
	private static final int MOD = 1_000_000_007;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		System.out.println(getPair(N, M));
	}
	
	private static long getPair(int n, int m) {			// nCm 구하기
		long result = 1;
		long under = 1;
		
		for(int i = 1; i < n + 1; i++) {
			result = ((result % MOD) * (i % MOD)) % MOD;
		}
		
		for(int i = 1; i < m + 1; i++) {
			under = ((under % MOD) * (i % MOD)) % MOD;
		}
		
		for(int i = 1; i < n - m + 1; i++) {
			under = ((under % MOD) * (i % MOD)) % MOD;
		}
		
		result *= modularInverse(under, MOD - 2);		// 페르마의 소정리를 이용한 역원 구하기
		return result % MOD;
	}
	
	private static long modularInverse(long a, long mod) {
		if (mod == 0) return 1;
	    if ((mod & 1) == 1) return (a * modularInverse(a, mod - 1) % MOD) % MOD;
	    return modularInverse((a * a) % MOD, mod / 2) % MOD;
	}
}
