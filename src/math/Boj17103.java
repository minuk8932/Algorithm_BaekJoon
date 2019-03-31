package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 
 * 	@author minchoba
 *	백준 17103번: 골드바흐 파티션
 *
 *	@see https://www.acmicpc.net/problem/17103/
 *
 */
public class Boj17103 {
	private static boolean[] prime = new boolean[1_000_000];
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		isPrime();
		
		while(T-- > 0) {
			int N = Integer.parseInt(br.readLine());
			int count = 0;
			
			for(int i = 2; i < N / 2 + 1; i++) {		// 반만 나누면 모든 소수 합을 찾을 수 있다
				if(prime[i] && prime[N - i]) count++;
			}
			
			sb.append(count).append(NEW_LINE);
		}
		
		System.out.println(sb);
	}
	
	private static void isPrime() {
		Arrays.fill(prime, true);
		prime[0] = prime[1] = false;
		
		for(int i = 2; i < 1_000; i++) {
			if(!prime[i]) continue;
			
			for(int j = i + i; j < prime.length; j += i) {
				if(!prime[j]) continue;
				prime[j] = false;
			}
		}
	}
}
