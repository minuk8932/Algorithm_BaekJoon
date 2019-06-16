package greedy;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 
 * 	@author minchoba
 *	백준 2012번: 등수 매기기
 *
 *	@see https://www.acmicpc.net/problem/2012/
 *
 */
public class Boj2012 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[] expect = new int[N];
		for(int i = 0; i < N; i++) {
			expect[i] = Integer.parseInt(br.readLine());
		}
		
		System.out.println(getMin(N, expect));
	}
	
	private static long getMin(int n, int[] arr) {
		long result = 0;
		Arrays.sort(arr);
		
		for(int i = 0; i < n; i++) {				// 정렬된 순서로 1등부터 n등까지 차이를 구함
			result += Math.abs(((i + 1) - arr[i]));
		}
		
		return result;
	}
}
