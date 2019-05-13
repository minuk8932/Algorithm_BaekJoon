package brute_force;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 17074번: 정렬
 *
 *	@see https://www.acmicpc.net/problem/17074/
 *
 */
public class Boj17074 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		
		int index = -1;
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			
			if(i >= 1) {
				if(index == - 1 && arr[i - 1] > arr[i]) index = i - 1;
			}
		}
		
		System.out.println(index == -1 ? N: getCount(N, arr, index));
	}
	
	private static int getCount(int n, int[] arr, int except) {
		int[] cut = {except - 1, except, except + 1};
		int count = 0;
		
		for(int limit: cut) {
			if(limit < 0 || limit > n - 1) continue;
			boolean flag = true;
			
			for(int i = 1; i < limit; i++) {			// 경계를 기준으로 모두 탐색해봄
				if(arr[i - 1] > arr[i]) {
					flag = false;
					break;
				}
			}
			
			for(int i = n - 1; i > limit + 1; i--) {
				if(arr[i - 1] > arr[i]) {
					flag = false;
					break;
				}
			}
			
			if(limit > 0 && limit < n - 1) {
				if(arr[limit - 1] > arr[limit + 1]) flag = false;		// 하나 제거했을때 전체가 모두 정렬되어있는가
			}
			
			if(flag) count++;
		}
		
		return count;
	}
}
