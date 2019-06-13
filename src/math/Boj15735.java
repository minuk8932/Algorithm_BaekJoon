package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 15735번: 삼각
 *
 *	@see https://www.acmicpc.net/problem/15735/
 *
 */
public class Boj15735 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		System.out.println(triangleCount(N));
	}
	
	private static long triangleCount(int n) {
		long[] count = new long[n];
		count[0] = 1;
		
		for(int i = 1; i < n; i++) {			// 이전 합 미리 구하기
			count[i]  = count[i - 1] + (i + 1);
		}		
		
		return getSum(n, count);
	}
	
	private static long getSum(int n, long[] arr) {
		long res = 0;
		for(int i = 0; i < n; i++) {
			res += arr[i];
		}
		
		if(n % 2 == 0) {						// 짝수 높이
			for(int i = 0; i < n - 1; i += 2) {
				res += arr[i];
			}
		}
		else {									// 홀수 높이
			for(int i = 1; i < n - 1; i += 2) {
				res += arr[i];
			}
		}
		
		return res;
	}
}
