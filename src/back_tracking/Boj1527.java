package back_tracking;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 1527번: 금민수의 개수
 *
 *	@see https://www.acmicpc.net/problem/1527/
 *
 */
public class Boj1527 {
	private static int result = 0;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		
		int[] arr = {4, 7};
		
		for(int i = 0; i < arr.length; i++) {
			backTracking(A, B, arr, arr[i]);		// 결과값이 int 범위를 벗어나 무작위로 변경되는 것을 방지
		}
		
		System.out.println(result);
	}
	
	private static void backTracking(int low, int high, int[] arr, long current) {
		if(current > high) return;
		if(low <= current && current <= high) result++;	// 범위 내 금민수의 갯수
		
		for(int idx = 0; idx < arr.length; idx++){
			long next = current * 10 + arr[idx];		// 다음 금민수
			
			backTracking(low, high, arr, next);
		}
	}
}
