package back_tracking;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 16936번: 나3곱2
 *
 *	@see https://www.acmicpc.net/problem/16936/
 *
 */
public class Boj16936 {
	private static final String SPACE = " ";
	
	private static boolean flag = false;
	private static int[] tmp;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		long[] arr = new long[N];
		tmp = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < N; i++) {
			arr[i] = Long.parseLong(st.nextToken());
		}
		
		for(int i = 0; i < N; i++) {		// 0번부터 완전 탐색
			Arrays.fill(tmp, -1);
			backTracking(N, arr, i, 0);
		}
	}
	
	private static void backTracking(int n, long[] arr, int current, int idx) {
		if(flag) return;
		
		tmp[current] = idx;
		
		if(idx == n - 1) {
			getResult(n, arr);		// 다 찾았으면 출력하고 재귀 종료
			
			flag = true;
			return;
		}
		
		for(int next = 0; next < n; next++) {
			if(tmp[next] != -1) continue;
			
			if((arr[current] % 3 == 0 && arr[current] / 3 == arr[next]) || (arr[current] * 2 == arr[next])) {	// 조건에 맞는 값이 존재하는 경우
				backTracking(n, arr, next, idx + 1);
			}
		}
	}
	
	private static void getResult(int n, long[] arr) {
		StringBuilder sb = new StringBuilder();
		int[] result = new int[n];
		
		for(int i = 0; i < n; i++) {
			result[tmp[i]] = i;
		}
		
		for(int i = 0; i < n; i++) {
			sb.append(arr[result[i]]).append(SPACE);
		}
		
		System.out.println(sb.toString());
	}
}
