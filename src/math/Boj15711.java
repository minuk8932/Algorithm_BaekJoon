package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * 	@author exponential-e
 *	백준 15711번: 환상의 짝꿍
 *
 *	@see https://www.acmicpc.net/problem/15711/
 *
 */
public class Boj15711 {
	private static final String Y = "YES\n";
	private static final String N = "NO\n";
	
	private static boolean[] prime = new boolean[2_000_001];
	private static ArrayList<Integer> primes = new ArrayList<>();
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		isPrime();
		
		while(T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			sb.append(isCouple(Long.parseLong(st.nextToken()),  Long.parseLong(st.nextToken())) ? Y: N);
		}
		
		System.out.println(sb.toString());
	}
	
	private static boolean isCouple(long a, long b) {
		long sum = a + b;
		
		if(sum < 4) return false;
		if(sum % 2 == 0) return true;
		
		sum -= 2;								// sum = 2 + (sum - 2)
		
		for(int p: primes) {
			long pow = (long) p * p;
			if(pow > sum) break;				// limit
			if(sum % p == 0) return false;		// not couple
		}
		
		return true;
	}
	
	private static void isPrime() {
		Arrays.fill(prime, true);
		
		prime[0] = prime[1] = false;
		
		for(int i = 2; i < prime.length; i++) {
			if(!prime[i]) continue;
			
			for(int j = i + i; j < prime.length; j += i) {
				prime[j] = false;
			}
		}
		
		for(int i = 0; i < prime.length; i++) {
			if(prime[i]) primes.add(i);
		}
	}
}
