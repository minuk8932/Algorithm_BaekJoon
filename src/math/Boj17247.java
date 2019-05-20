package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 17247번: 택시거리
 *
 *	@see https://www.acmicpc.net/problem/17247/
 *
 */
public class Boj17247 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] x = new int[2];
		int[] y = new int[2];
		int idx = 0;
		
		for(int i = 0; i < N; i++) {
			if(idx == 2) break;
			
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				if(Integer.parseInt(st.nextToken()) == 0) continue;
				x[idx] = i;
				y[idx++] = j;
			}
		}
		
		System.out.println(Math.abs(x[0] - x[1]) + Math.abs(y[0] - y[1]));
	}
}
