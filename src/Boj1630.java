import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Boj1630 {
	private static final int MOD = 987_654_321;
	private static boolean[] isPrime;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		prime(N);
		System.out.println(getResult(N));
	}
	
	private static void prime(int n) {
		isPrime = new boolean[n + 1];
		Arrays.fill(isPrime, true);
		
		isPrime[0] = isPrime[1] = false;
		for(int i = 2; i < n + 1; i++) {
			if(!isPrime[i]) continue;
			
			for(int j = i + i; j < n + 1; j += i) {
				isPrime[j] = false;
			}
		}
	}
	
	private static long getResult(int n) {
		long gcd = 1;
		
		for(int i = 2; i < n; i++) {
			if(isPrime[i]) gcd = ((gcd % MOD) * i) % MOD;
		}
		
		return gcd;
	}
}
