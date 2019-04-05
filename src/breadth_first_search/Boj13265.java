package breadth_first_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 13265번: 색칠하기
 *
 *	@see https://www.acmicpc.net/problem/13265/
 *
 */
public class Boj13265 {
	private static final String I = "impossible";
	private static final String P = "possible";
	private static final String NEW_LINE = "\n";
	
	private static class Pair{
		int edge;
		int color;
		
		public Pair(int edge, int color) {
			this.edge = edge;
			this.color = color;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			ArrayList<Integer>[] graph = new ArrayList[N];
			for(int i = 0; i < N; i++) {
				graph[i] = new ArrayList<>();
			}
			
			while(M-- > 0) {
				st = new StringTokenizer(br.readLine());
				int edge1 = Integer.parseInt(st.nextToken()) - 1;
				int edge2 = Integer.parseInt(st.nextToken()) - 1;
				
				graph[edge1].add(edge2);
				graph[edge2].add(edge1);
			}
			
			sb.append(bfs(N, graph) ? P: I).append(NEW_LINE);
		}
		
		System.out.println(sb);
	}
	
	private static boolean bfs(int n, ArrayList<Integer>[] list) {
		int[] visit = new int[n];
		
		for(int i = 0; i < n; i++) {
			if(visit[i] != 0) continue;
			
			Queue<Pair> q = new LinkedList<>();
			q.offer(new Pair(i, 1));
			
			visit[i] = 1;
			
			while(!q.isEmpty()) {
				Pair current = q.poll();
				
				for(int next: list[current.edge]) {
					if(visit[next] == 0) {
						visit[next] = current.color * -1;			// 다음 색 지정
						
						q.offer(new Pair(next, visit[next]));
					}
					else {
						if(visit[next] != current.color * -1) return false; 		// 이미 색칠되어있는데 현재 칠해야되는 색이 아닌 경우
						else continue;
					}
				}
			}
		}
		
		return true;
	}
}
