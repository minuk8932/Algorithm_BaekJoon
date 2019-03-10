package search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 11504번: 돌려 돌려 돌림판!
 *
 *	@see https://www.acmicpc.net/problem/11504/
 *
 */
public class Boj11504 {
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			int X = 0, Y = 0;
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < M; i++) {
				X += Math.pow(10, M - 1 - i) * Integer.parseInt(st.nextToken());
			}
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < M; i++) {
				Y += Math.pow(10, M - 1 - i) * Integer.parseInt(st.nextToken());
			}
			
			int[] wheel = new int[N];
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++) {
				wheel[i] = Integer.parseInt(st.nextToken());
			}
			
			sb.append(process(N, M, X, Y, wheel)).append(NEW_LINE);
		}
		
		System.out.println(sb);
	}
	
	private static int process(int n, int m, int lower, int upper, int[] arr) {
		int count = 0;
		
		for(int start = 0; start < n; start++) {			// 0 ~ n - 1 번째까지 하나씩 m자리 만큼 숫자를 생성
			int value = 0, expo = 0;
			
			for(int idx = start; idx < m + start; idx++) {
				value += Math.pow(10, m - 1 - expo) * arr[idx % n];				
				expo++; 
			}
			
			if(value >= lower && value <= upper) count++;	// 범위 내에 숫자가 속하는 경우 +1
		}
		
		return count;
	}
}
