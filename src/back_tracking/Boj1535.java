package back_tracking;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 1535번: 안녕
 *
 *	@see https://www.acmicpc.net/problem/1535/
 *
 */
public class Boj1535 {
	private static boolean[] visit;
	private static int max;
	
	private static class Pair{
		int sad;
		int happy;
		
		public Pair(int sad, int happy) {
			this.sad = sad;
			this.happy = happy;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		int N = Integer.parseInt(br.readLine());
		Pair[] feel = new Pair[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			feel[i] = new Pair(Integer.parseInt(st.nextToken()), 0);
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			feel[i] = new Pair(feel[i].sad, Integer.parseInt(st.nextToken()));
		}
		
		for(int i = 0; i < N; i++){
		    visit = new boolean[N];
		    backTracking(N, feel, i, 100 - feel[i].sad, feel[i].happy);
        }
			
		System.out.println(max);
	}
	
	private static void backTracking(int n, Pair[] p, int current, int s, int h) {
		if(visit[current]) return;
		visit[current] = true;
		
		if(s <= 0) return;		// 슬픔이 0보다 작거나 같은경우
		if(h > max) max = h;	// 최대 기쁨 저장
		
		for(int next = current + 1; next < n; next++) {
			if(visit[next]) continue;
			
			backTracking(n, p, next, s - p[next].sad, h + p[next].happy);		// 최적의 기쁨 조건을 찾음
			visit[next] = false;
		}
	}
}
