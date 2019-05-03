package breadth_first_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 13549번: 숨바꼭질 3
 *
 *	@see https://www.acmicpc.net/problem/13549/
 *
 */
public class Boj13549 {
	private static final int[] DIRECTION = {1, -1, 2};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		System.out.println(bfs(N, K));
	}
	
	private static int bfs(int start, int end) {
		int[] visit = new int[100_001];
		Arrays.fill(visit, Integer.MAX_VALUE);
		
		Queue<Integer> q = new LinkedList<>();
		q.offer(start);
		
		visit[start] = 1;
		
		while(!q.isEmpty()) {
			int current = q.poll();
			
			for(final int D: DIRECTION) {
				int next = 0;
				
				if(D == 2) {
					next = current * D;
					
					if(next > 100_000 || next < 0) continue;
					if(visit[next] > visit[current]) {			// 순간이동 하는 경우
						visit[next] = visit[current];
						q.offer(next);
					}
				}
				else {
					next = current + D;
					
					if(next > 100_000 || next < 0) continue;
					if(visit[next] > visit[current] + 1) {		// 걷는 경우
						visit[next] = visit[current] + 1;
						q.offer(next);
					}
				}
			}
		}
		
		return visit[end] - 1;
	}
}
