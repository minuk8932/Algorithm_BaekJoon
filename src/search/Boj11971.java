package search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author exponential-e
 *	백준 11971번: 속도 위반
 *
 *	@see https://www.acmicpc.net/problem/11971/
 *
 */
public class Boj11971 {	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] rex = new int[101];
		int prev = 0;
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int post = Integer.parseInt(st.nextToken()) + prev;
			int v = Integer.parseInt(st.nextToken());
			
			for(int j = prev; j < post + 1; j++) {
				rex[j] = v;
			}

			prev = post;
		}
		
		int[] nor = new int[101];
		prev = 0;
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int post = Integer.parseInt(st.nextToken()) + prev;
			int v = Integer.parseInt(st.nextToken());
			
			for(int j = prev; j < post + 1; j++) {
				nor[j] = v;
			}

			prev = post;
		}
		
		System.out.println(illegal(rex, nor));
	}
	
	private static int illegal(int[] rex, int[] user) {
		int max = 0;
		
		for(int i = 0; i < 101; i++) {
			if(rex[i] < user[i]) max = Math.max(max, user[i] - rex[i]);		// 속도 위반한 경우
		}
		
		return max;
	}
}
