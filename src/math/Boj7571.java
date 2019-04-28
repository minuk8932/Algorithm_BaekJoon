package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 7571번: 점 모으기
 *
 *	@see https://www.acmicpc.net/problem/7571/
 *
 */
public class Boj7571 {	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] x = new int[M];
		int[] y = new int[M];
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			x[i] = Integer.parseInt(st.nextToken());
			y[i] = Integer.parseInt(st.nextToken());
		}
		
		System.out.println(getMinDistance(N, M, x, y));
	}
	
	private static int getMinDistance(int n, int m, int[] x, int[] y) {
		int distance = 0;
		
		Arrays.sort(x);
		Arrays.sort(y);
		
		int xAvg = x[m / 2];			// 중앙 값 나누어 지정
		int yAvg = y[m / 2];
		
		for(int i = 0; i < m; i++) {
			if(x[i] > xAvg) distance += x[i] - xAvg;		// 중앙 값에 대한 거리 합
			else distance += xAvg - x[i];
			
			if(y[i] > yAvg) distance += y[i] - yAvg;
			else distance += yAvg - y[i];
		}
		
		return distance;
	}
}
