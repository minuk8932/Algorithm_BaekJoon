package back_tracking;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author exponential-e
 *	백준 1145번: 적어도 대부분의 배수
 *
 *	@see https://www.acmicpc.net/problem/1145/
 *
 */
public class Boj1145 {
	private static boolean[] used;
	private static int min = 1_000_001;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int[] input = new int[5];
		for(int i = 0; i < 5; i++) {
			input[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i = 1; i < 6; i++) {
			used = new boolean[5];
			backTracking(input, i, i, 0);			// make value sets
		}
		
		System.out.println(min);
	}
	
	private static void backTracking(int[] arr, int current, int value, int count) {
		if(used[current - 1]) return;
		used[current - 1] = true;
		
		if(count == 2) {
			int prev = arr[(value % 10) - 1];
			value /= 10;
			
			int via = arr[(value % 10) - 1];
			value /= 10;
			
			int gcd = gcd(prev, via);				// get gcd
			int lcm = prev / gcd * via;
			
			int post = arr[(value % 10) - 1];
			gcd = gcd(lcm, post);
			
			int res = lcm / gcd * post;
			if(res < min) min = res;				// find lcm three datas
			
			return;
		}
		
		for(int next = current + 1; next < 6; next++) {
			if(used[next - 1]) continue;
			
			backTracking(arr, next, value * 10 + next, count + 1);
			used[next - 1] = false;
		}
	}
	
	private static int gcd(int a, int b) {
		if(b == 0) return a;
		return gcd(b, a % b);
	}
}
