package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 15638번: No Description
 *
 *	@see https://www.acmicpc.net/problem/15638/
 *
 */
public class Boj15638 {	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		System.out.println(isPrime(N) ? "Yes": "No");
	}
	
	private static boolean isPrime(int n) {
		int loop = (int) (Math.sqrt(n)) + 1;
		
		for(int i = 2; i < loop; i++) {
			if(n % i == 0) return false;	// 아닌 경우
		}
		
		return true;			// 소수인 경우
	}
}
