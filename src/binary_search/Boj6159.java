package binary_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 6159번: 코스튬 파티
 *
 *	@see https://www.acmicpc.net/problem/6159/
 *
 */
public class Boj6159 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		
		int[] cows = new int[N];
		for(int i = 0; i < N; i++) {
			cows[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(cows);								// 소 크기별 정렬
		System.out.println(binarySearch(N, S, cows));
	}
	
	private static int binarySearch(int n, int target, int[] arr) {
		int count = 0;
		
		for(int i = 0; i < n; i++) {			// 자신보다 큰 소와 크기를 비교해 쌍을 찾는다.
			int fix = arr[i];
			int start = i, end = n - 1;
			
			int pos = -1;
			
			while(start <= end) {
				int mid = (start + end) / 2;
				if(mid == i) break;				// 자기 자신이 두번들어 갈 순 없음
				
				if(fix + arr[mid] <= target) {
					pos = mid;
					start = mid + 1;
				}
				else {
					end = mid - 1;
				}
			}
			
			if(pos != -1) count += (pos - i);	// 자신과 들어갈 수 있는 (최대 소의 크기의 번호 - 자신의 번호): 마릿수
		}
		
		return count;
	}
}
