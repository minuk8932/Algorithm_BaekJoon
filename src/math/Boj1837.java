package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 1837번: 암호 제작
 *
 *	@see https://www.acmicpc.net/problem/1837/
 *
 */
public class Boj1837 {
	private static boolean[] prime = new boolean[1_000_001];
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		String P = st.nextToken();
		int K = Integer.parseInt(st.nextToken());
		
		isPrime(K);
		System.out.println(goodOrBad(P, K));
	}
	
	private static void isPrime(int N) {
		Arrays.fill(prime, true);
		prime[0] = prime[1] = false;
		
		for(int i = 2; i < N + 1; i++) {		// Sieve of Eratosthenes
			if(!prime[i]) continue;
			
			for(int j = i + i; j < N + 1; j += i) {
				prime[j] = false;
			}
		}
	}
	
	private static StringBuilder goodOrBad(String p, int k) {
		StringBuilder sb = new StringBuilder();
		int modular = -1;
		
		for(int i = 2; i < k; i++) {
			if(prime[i]) modular = mod(p, i);
			if(modular == 0) return sb.append("BAD").append(" ").append(i);		// K보다 작은 소수의 배수인 경우
		}

		return sb.append("GOOD");
	}
	
	private static int mod(String str, int prime) {
		int result = 0;
		int leng = str.length();
		
		for(int i = 0; i < leng; i++) {				// 나누어 떨어지면 0 아니면 0 보다 큰 정수가 저장됨
			result = (result * 10 + (str.charAt(i) - '0')) % prime;
		}
		
		return result;
	}
}
