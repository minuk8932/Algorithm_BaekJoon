package binary_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 
 * 	@author minchoba
 *	백준 12945번: 재미있는 박스 정리
 *
 *	@see https://www.acmicpc.net/problem/12945/
 *
 */
public class Boj12945 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[] V = new int[N];
		for(int i = 0; i < N; i++) {
			V[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(V);
		System.out.println(binarySearch(N, V));
	}
	
	private static int binarySearch(int n, int[] v) {
		int count = 0;
		int idx = -1;
		
		for(int target = 0; target < n; target++) {			
			int start = 0, end = n;
			
			while(start <= end) {				// 작은 박스부터 들어 갈 수 있는 target 박스 찾기
				int mid = (start + end) / 2;
				
				if(v[mid] * 2 <= v[target]) {
					start = mid + 1;
					idx = Math.max(idx, mid);
				}
				else {
					end = mid - 1;
				}
			}
			
			int diff = idx - (target - count) + 1;				// 이번에 들어간 박스 - (이번에 채워진 박스 - 제거된 박스 갯수) + 1
			if((diff < 0 ? 0: diff) + count < idx + 1) count++;	// 이번에 박스가 제대로 들어갔다면, 제거 갯수 + 1
		}
		
		return n - count;
	}
}
