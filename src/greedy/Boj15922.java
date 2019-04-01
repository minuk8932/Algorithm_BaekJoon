package greedy;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 15922번: 아우으 우아으이야!!
 *
 *	@see https://www.acmicpc.net/problem/15922/
 *
 */
public class Boj15922 {
	
	private static class Pair{
		int x;
		int y;
		
		public Pair(int x, int y) {
			this.x = x;
			this.y = y;  
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Pair[] p = new Pair[N];
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			p[i] = new Pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		System.out.println(getLength(N, p));
	}
	
	private static int getLength(int n, Pair[] arr) {
		Pair point = new Pair(arr[0].x, arr[0].y);
		int result = 0;
		
		for(Pair p: arr) {
			if(p.x == point.x && p.y == point.y) continue;
			
			if(point.y >= p.x) {						// 선분의 연장선
				if(point.y < p.y) {
					point = new Pair(point.x, p.y);
				}
			}
			else {										// 선분이 끊긴 경우
				result += Math.abs(point.x - point.y);
				point = new Pair(p.x, p.y);
			}
		}
		
		return result += Math.abs(point.x - point.y);	// 최종 남은 선분
	}
}
