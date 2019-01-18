package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 1456번: 거의 소수
 *
 *	@see https://www.acmicpc.net/problem/1456/
 *
 */
public class Boj1456 {
	private static final long INF = 100_000_000_000_000L;
	private static boolean[] isPrime = new boolean[10_000_000];
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());		
		long A = Long.parseLong(st.nextToken());
		long B = Long.parseLong(st.nextToken());
		
		getPrime();
		
		System.out.println(getAlmostPrime(A, B));
	}
	
	private static void getPrime() {					// 에라토스테네스의 체
		Arrays.fill(isPrime, true);
		isPrime[0] = isPrime[1] = false;
		
		for(int i = 2; i < isPrime.length; i++) {
			if(!isPrime[i]) continue;
			
			for(int j = i + i; j < isPrime.length; j += i) {
				isPrime[j] = false;
			}
		}
	}
	
	private static int getAlmostPrime(long from, long to) {
		int count = 0;
		
		for(int i = 0; i < isPrime.length; i++) {
			if(!isPrime[i]) continue;
			
			long value = i;
			
			while(true) {
				if(isOverFlow(value, i)) break;
				value *= i;
				
				if(value > to) break;
				if(value >= from && value % i == 0) count++;			// 소수의 제곱수가 맞으면 +1			
			}
		}
		
		return count;
	}
	
	private static boolean isOverFlow(long pow, long prime) {
		return pow > INF || pow < 0 || pow * prime > INF || pow * prime < 0 ? true: false;
	}
}
