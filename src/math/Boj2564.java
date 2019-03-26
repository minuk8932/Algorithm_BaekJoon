package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 2564번: 경비원
 *
 *	@see https://www.acmicpc.net/problem/2564/
 *
 */
public class Boj2564 {
	private static int size;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(br.readLine());
		
		size = N * 2 + M * 2;
		
		int[] pos = new int[T + 1];
		for(int i = 0; i < T + 1; i++) {
			st = new StringTokenizer(br.readLine());
			int way = Integer.parseInt(st.nextToken());
			int val = Integer.parseInt(st.nextToken());
			
			pos[i] = putHorizontalLine(N, M, val, way);		// 각 방향에 따라 연산된 값을 통해 상점과 경비를 수평선에 위치시킴
		}
		
		System.out.println(process(T, pos));
	}
	
	private static int process(int t, int[] arr) {
		int res = 0;
		
		for(int i = 0; i < t; i++) {
			int forward = Math.abs(arr[t] - arr[i]);
			int backward = getReverse(arr[t], arr[i]);

			res += Math.min(forward, backward);				// 수평선에서 최단 거리
		}
		
		return res;
	}
	
	private static int getReverse(int target, int shop) {
		return shop > target ? target + size - shop: size + shop - target;
	}
	
	private static int putHorizontalLine(int n, int m, int target, int condition) {		
		switch (condition) {
		case 1:
			return target = m - target;
			
		case 2:
			return target += (m + n);
			
		case 3:
			return target += m;
		}
		
		return target = (size - target);
	}
}
