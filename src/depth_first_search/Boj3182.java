package depth_first_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author exponential-e
 *	백준 3182번: 한동이는 공부가 싫어!
 *
 *	@see https://www.acmicpc.net/problem/3182
 *
 */
public class Boj3182 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[] arr = new int[N];
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine()) - 1;
		}
		
		System.out.println(study(N, arr));
	}
	
	private static int study(int n, int[] arr) {
		int max = 0;
		for(int i = 0; i < n; i++) {
			boolean[] asked = new boolean[n];
			int count = 1;
			int idx = arr[i];
			asked[i] = true;
			
			while(!asked[idx]) {
				asked[idx] = true;
				count++;
				
				idx = arr[idx];
			}
			
			if(count > max) max = count;			// find max ask
		}
		
		int res = Integer.MAX_VALUE;
		for(int i = 0; i < n; i++) {
			boolean[] asked = new boolean[n];
			int count = 1;
			int idx = arr[i];
			asked[i] = true;
			
			while(!asked[idx]) {
				asked[idx] = true;
				count++;
				
				idx = arr[idx];
			}
			
			if(count == max) res = Math.min(i, res);	// minimun nums
		}
		
		return res + 1;
	}
}
