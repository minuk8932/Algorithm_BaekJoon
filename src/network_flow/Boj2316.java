package network_flow;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 2316번: 도시 왕복하기
 *
 *	@see https://www.acmicpc.net/problem/2316/
 *
 */
public class Boj2316 {
	private static int[][] capacity;
	private static int[][] flow;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int P = Integer.parseInt(st.nextToken());
		
		ArrayList<Integer>[] path = new ArrayList[N * 2];
		for(int i = 0; i < N * 2; i++) {
			path[i] = new ArrayList<>();
		}
		
		capacity = new int[N * 2][N * 2];
		flow = new int[N * 2][N * 2];
		
		for(int i = 2; i < N; i++) {
			capacity[i][i + N] = 1;		// 분할 노드 초기화
			
			path[i].add(i + N);
			path[i + N].add(i);
		}
		
		while(P-- > 0) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken()) - 1;
			int to = Integer.parseInt(st.nextToken()) - 1;
			
			capacity[from + N][to] = 1;		//	분할 노드로부터 들어오는
			capacity[to + N][from] = 1;		//	분할 노드로 나가는
			
			path[from + N].add(to);			// 양방향 노드 및 분할 노드 간선 추가
			path[to].add(from + N);
			path[from].add(to + N);
			path[to + N].add(from);
		}
		
		System.out.println(networkFlow(N, path, N, 1));
	}
	
	private static int networkFlow(int n, ArrayList<Integer>[] list, int source, int sink) {
		int result = 0;
		int[] visit = new int[n * 2];
		
		while(true) {						// 일반적인 에드몬드 카프 알고리즘
			Arrays.fill(visit, -1);
			
			Queue<Integer> q = new LinkedList<>();
			q.offer(source);
			
			while(!q.isEmpty()) {
				int current = q.poll();
				
				for(int next: list[current]) {
					if(visit[next] != -1) continue;
					
					if(capacity[current][next] - flow[current][next] > 0) {
						visit[next] = current;
						if(next == sink) break;
						
						q.offer(next);
					}
				}
			}
			
			if(visit[sink] == -1) break;
			
			int minFlow = Integer.MAX_VALUE;
			for(int i = sink; i != source; i = visit[i]) {
				minFlow = Math.min(minFlow, capacity[visit[i]][i] - flow[visit[i]][i]);
			}
			
			for(int i = sink; i != source; i = visit[i]) {
				flow[visit[i]][i] += minFlow;
				flow[i][visit[i]] -= minFlow;
			}

			result += minFlow;
		}
		
		return result;
	}
}
