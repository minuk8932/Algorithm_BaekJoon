package binary_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * 	@author exponential-e
 *	백준 6209번: 제자리 멀리 뛰기
 *
 *	@see https://www.acmicpc.net/problem/6209/
 *
 */
public class Boj6209 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int d = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		int[] island = new int[n + 1];
		island[0] = d;
		
		for(int i = 1; i < n + 1; i++) {
			island[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(island);
		System.out.println(binarySearch(n, m, island));
	}
	
	private static int binarySearch(int n, int m, int[] arr) {
		int end = arr[n] + 1;
		int start = 0, res = 0;
		
		while(start <= end) {
			int mid = (start + end) / 2;
			int count = 0, prev = 0;
			
			for(int i = 0; i < arr.length; i++) {
				if(arr[i] - prev < mid) count++;			// check interval
				else prev = arr[i];
			}
			
			if(count > m) {
				end = mid - 1;
			}
			else {
				start = mid + 1;
				res = mid;									// find min
			}
		}
		
		return res;
	}
}
