package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 10811번: 바구니 뒤집기
 *
 *	@see https://www.acmicpc.net/problem/10811/
 *
 */
public class Boj10811 {
	private static final String SPACE = " ";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[N];
		for(int i = 0; i < N; i++) {
			arr[i] = i;
		}
		
		while(M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int i = Integer.parseInt(st.nextToken()) - 1;
			int j = Integer.parseInt(st.nextToken()) - 1;
			
			if(i == j) continue;
			arr = getSwitch(arr, i, j);
 		}
		
		System.out.println(getBasket(arr));
	}
	
	private static int[] getSwitch(int[] arr, int from, int to) {
		int[] tmp = new int[arr.length];
		int adder = 0;
		
		for(int i = from; i < to + 1; i++) {			// 범위에 맞춰 바구니 뒤집기
			tmp[to - adder++] = arr[i];
		}
		
		for(int i = from; i < to + 1; i++) {
			arr[i] = tmp[i];
		}
		
		return arr;
	}
	
	private static StringBuilder getBasket(int[] arr) {
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < arr.length; i++) {
			sb.append(arr[i] + 1).append(SPACE);
		}
		
		return sb;
	}
}
