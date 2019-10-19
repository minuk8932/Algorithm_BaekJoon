package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author exponential-e
 *	백준 1979번: 극적인 곱셈
 *
 *	@see https://www.acmicpc.net/problem/1979/
 *
 */
public class Boj1979 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		System.out.println(getExtreme(n, k));
	}
	
	private static String getExtreme(int n, int k) {
		if(n == 1) return ""+k;
			
		int[] arr = new int[1_000_000];
		arr[0] = k;
		
		int prev = 0;
		int idx = 0;
		
		do {
			int tmp = arr[idx++] * n + prev;			// 한자릿수 곱셈으로 이어붙이기
		    arr[idx] = tmp % 10;
		    prev = tmp / 10;
		} while(arr[idx] != k || prev != 0);
		
		if (arr[idx - 1] == 0) return "0";				// 구하지 못하는 경우
		
		StringBuilder sb = new StringBuilder();
		for (int i = idx - 1; i >= 0; i--) {
			sb.append(arr[i]);
		}
		
		return sb.toString();
	}
}
