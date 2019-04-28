package search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 7795번: 먹을 것인가 먹힐 것인가
 *
 *	@see https://www.acmicpc.net/problem/7795/
 *
 */
public class Boj7795 {
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			int[] A = new int[N];
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++) {
				A[i] = Integer.parseInt(st.nextToken());
			}
			
			int[] B = new int[M];
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < M; i++) {
				B[i] = Integer.parseInt(st.nextToken());
			}
			
			sb.append(linearSearch(N, M, A, B)).append(NEW_LINE);
		}
		
		System.out.println(sb.toString());
	}
	
	private static int linearSearch(int n, int m, int[] arr, int[] brr) {
		int count = 0, tmp = 0;
		int search = 0;
		
		Arrays.sort(brr);			// 정렬
		Arrays.sort(arr);
		
		for(int i = 0; i < n; i++) {
			int target = arr[i];
			
			while(search < m && target > brr[search]) {
				tmp++;				// 현재 i에게 먹히는 수
				search++;
			}
			
			count += tmp;			// 누적 합
		}
		
		return count;
	}
}
