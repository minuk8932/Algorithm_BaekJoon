package binary_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author exponential-e
 *	백준 17393번: 다이나믹 롤러
 *
 *	@see https://www.acmicpc.net/problem/17393/
 *
 */
public class Boj17393 {
	private static final String SPACE = " ";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		long[] ink = new long[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			ink[i] = Long.parseLong(st.nextToken());
		}
		
		long[] vis = new long[N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			vis[i] = Long.parseLong(st.nextToken());
		}
		
		System.out.println(painting(N, ink, vis));
	}
	
	private static String painting(int n, long[] k, long[] v) {
		int[] res = new int[n];
		
		for(int i = n - 1; i >= 0; i--) {
			long target = k[i];
			
			int start = i, end = n - 1;
			int val = -1;
			
			while(start <= end) {						// find can painting
				int mid = (start + end) / 2;
				
				if(v[mid] <= target) {
					val = mid;
					start = mid + 1;
				}
				else{
					end = mid - 1;
				}
			}
			
			res[i] = val - i;
		}
		
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < n; i++) {					// painting
			sb.append(res[i]).append(SPACE);
		}

		return sb.toString();
	}
}
