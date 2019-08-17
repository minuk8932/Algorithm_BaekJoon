package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author exponential-e
 *	백준 13412번: 서로소 쌍
 *
 *	@see https://www.acmicpc.net/problem/13412/
 *
 */
public class Boj13412 {
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			int N = Integer.parseInt(br.readLine());
			sb.append(counting(N)).append(NEW_LINE);
		}

		System.out.println(sb.toString());
	}
	
	private static int counting(int n){
		int loop = (int) Math.sqrt(n) + 1;
		int count = 1;
		
		for(int i = 2; i < loop; i++) {
			if(n % i != 0) continue;			// 해당 수가 약수 인가
			if(gcd(n / i, i) == 1) count++;		// 페어 약수와 서로소 인가
		}
		
		return count;
	}
	
	private static int gcd(int a, int b) {
		if(b == 0) return a;
		return gcd(b, a % b);
	}
}
