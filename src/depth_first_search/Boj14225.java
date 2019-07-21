package depth_first_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author exponential-e
 *	백준 14225번: 부분 수열의 합
 *
 *	@see https://www.acmicpc.net/problem/14225/
 *
 */
public class Boj14225 {
	private static boolean[] flag;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[] arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int sum = 0;
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			sum += arr[i];
		}
		
		System.out.println(getExcept(N, sum, arr));
	}
	
	private static int getExcept(int n, int m, int[] arr) {
		flag = new boolean[m + 1];
		
		for(int i = 0; i < n; i++) {
			recursion(n, arr, arr[i], i);
		}
		
		for(int i = 1; i < m + 1; i++) {
			if(!flag[i]) return i;
		}
		
		return m + 1;			// 모든 수가 나오는 경우
	}
	
	private static void recursion(int n, int[] arr, int value, int current) {
		flag[value] = true;
		
		for(int next = current + 1; next < n; next++) {
			recursion(n, arr, arr[next] + value, next);
		}
	}
}
