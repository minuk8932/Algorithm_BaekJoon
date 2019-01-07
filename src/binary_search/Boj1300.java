package binary_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 1300번: K번째 수
 *
 *	@see https://www.acmicpc.net/problem/1300/
 *
 */
public class Boj1300 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int k = Integer.parseInt(br.readLine());
		
		System.out.println(binarySearch(N, k));		// 결과 출력
	}
	
	private static int binarySearch(int N, int K) {
		int start = 1;
		int end = K;
		int res = 0;
		
		while(start <= end) {
			int mid = (start + end) / 2;
			int count = getCount(N, mid);		// i 행에서 mid 보다 작거나 같은 수의 갯수

			if(count < K) {
				start = mid + 1;
			}
			else {
				res = mid;
				end = mid - 1;
			}
		}
		
		return res;
	}
	
	private static int getCount(int N, int M) {
		int cnt = 0;
		
		for (int i = 1; i < N + 1; i++) {
			cnt += (long) Math.min(M / i, N);
		}
		
		return cnt;
	}
}
