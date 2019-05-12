package back_tracking;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 15658번: 연산자 끼워넣기 2
 *
 *	@see https://www.acmicpc.net/problem/15658/
 *
 */
public class Boj15658 {
	private static int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] arr = new int[N];
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		int[] formula = new int[4];
		for(int i = 0; i < 4; i++) {
			formula[i] = Integer.parseInt(st.nextToken());
		}
		
		backTracking(N, arr, formula[0], formula[1], formula[2], formula[3], 0, arr[0]);
		System.out.println(max + "\n" + min);
	}
	
	private static void backTracking(int n, int[] arr, int add, int sub, int mul, int div, int current, int value) {	
		if(current == n - 1) {
			max = Math.max(max, value);		// 연산 결과로 최대 최솟값
			min = Math.min(min, value);
			
			return;
		}
			
		if(add > 0) backTracking(n, arr, add - 1, sub, mul, div, current + 1, value + arr[current + 1]);		// 가능한 연산자를 하나씩 끼워넣기
		if(sub > 0) backTracking(n, arr, add, sub - 1, mul, div, current + 1, value - arr[current + 1]);
		if(mul > 0) backTracking(n, arr, add, sub, mul - 1, div, current + 1, value * arr[current + 1]);
		if(div > 0) backTracking(n, arr, add, sub, mul, div - 1, current + 1, value / arr[current + 1]);

	}
}
