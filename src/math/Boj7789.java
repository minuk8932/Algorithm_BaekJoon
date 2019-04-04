package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 7789번: 텔레프라임
 *
 *	@see https://www.acmicpc.net/problem/7789/
 *
 */
public class Boj7789 {
	private static boolean[] isPrime = new boolean[10_000_000];
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		prime();
		
		int tel = Integer.parseInt(st.nextToken());
		int add = Integer.parseInt(st.nextToken());
		
		System.out.println((isPrime[tel] && isPrime[tel + (add * isPrime.length / 10)]) ? "Yes": "No");		// 둘다 소수면 yes
	}
	
	private static void prime() {
		Arrays.fill(isPrime, true);
		isPrime[0] = isPrime[1] = false;
		
		int size = (int) Math.sqrt(isPrime.length);
		
		for(int i = 2; i < size + 1; i++) {			// 에라토스테네스의 체
			if(!isPrime[i]) continue;
			
			for(int j = i + i; j < isPrime.length; j += i) {
				isPrime[j] = false;
			}
		}
	}
}
