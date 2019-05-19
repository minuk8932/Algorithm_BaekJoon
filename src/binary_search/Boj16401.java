package binary_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 16401번: 과자나눠주기
 *
 *	@see https://www.acmicpc.net/problem/16401/
 *	
 */
public class Boj16401 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		
		int[] snack = new int[N];
		int max = 0;
		long target = 0;
		
		for(int i = 0; i < N; i++) {
			snack[i] = Integer.parseInt(st.nextToken());
			if(snack[i] > max) max = snack[i];
			
			target += snack[i];
		}
		
		System.out.println(binarySearch(M, N, snack, max, target >= M ? 1: 0));
	}
	
	private static long binarySearch(int m, int n, int[] arr, int end, long leng) {
		int start = 0;
		
		while(start <= end) {
			int mid = (start + end) / 2;
			
			int count = 0;
			for(int i = 0; i < n; i++) {		// mid 길이로 조카들에게 분배 가능한지 확인
				if(mid == 0) break;
				count += (arr[i] / mid);
			}
			
			if(count >= m) {					// 가능한경우 길이를 더 키워서 진행
				leng = mid;
				start = mid + 1;
			}
			else {								// 불가능한 경우 길이를 줄여봄
				end = mid - 1;
			}
		}
		
		return leng;
	}
}
