package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author exponential-e
 *	백준 1016번: 제곱ㄴㄴ 수
 *
 *	@see https://www.acmicpc.net/problem/1016/
 *
 */
public class Boj1016 {
	private static boolean[] prime = new boolean[1_000_001];
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		long min = Long.parseLong(st.nextToken());
		long max = Long.parseLong(st.nextToken());
		
		System.out.println(eratosthenes(min, max));
	}
	
	private static int eratosthenes(long m, long M) {
		for (long i = 2; i * i <= M; i++){
			long ipow = i * i;
			
			long start = m / ipow;
			if (start * ipow != m) start++;
			
			for (long j = start; ipow * j <= M; j++) {		// do seive
				prime[(int) (ipow * j - m)] = true;
			}
		}
		
		int count = 0;
		
		for (int i = 0; i <= M - m; i++) {					// remained
			if (!prime[i]) count++;
		}
		
		return count;
	}
}
