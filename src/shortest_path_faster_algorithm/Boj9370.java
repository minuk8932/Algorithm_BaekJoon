package shortest_path_faster_algorithm;
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
 *	백준 9370번: 미확인 도착지
 *
 *	@see https://www.acmicpc.net/problem/9370/
 *
 */
public class Boj9370 {
	private static boolean[] visit;
	
	private static final String SPACE = " ";
	private static final String NEW_LINE = "\n";
	
	private static class Node{
		int vertex;
		int cost;
		
		public Node(int vertex, int cost) {
			this.vertex = vertex;
			this.cost = cost;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			int t = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken()) - 1;
			int g = Integer.parseInt(st.nextToken()) - 1;
			int h = Integer.parseInt(st.nextToken()) - 1;
			
			ArrayList<Node>[] path = new ArrayList[n];
			for(int i = 0; i < n; i++) {
				path[i] = new ArrayList<>();
			}
			
			int subCost = 0;
			
			while(m-- > 0) {
				st = new StringTokenizer(br.readLine());
				int vertex1 = Integer.parseInt(st.nextToken()) - 1;
				int vertex2 = Integer.parseInt(st.nextToken()) - 1;
				int cost = Integer.parseInt(st.nextToken());
				
				path[vertex1].add(new Node(vertex2, cost));
				path[vertex2].add(new Node(vertex1, cost));
				
				if((vertex1 == g && vertex2 == h) || (vertex1 == h && vertex2 == g)) subCost = cost;		// g <-> h 비용 미리 저장
			}
			
			visit = new boolean[n];
			
			while(t-- > 0) {
				int e = Integer.parseInt(br.readLine()) - 1;				
				
				int minCost = spfa(path, n, s, e);		// s -> e 최단 경로
				int result = subCost;
				
				result += spfa(path, n, s, g);			// s -> g -> h -> e 최단 경로
				result += spfa(path, n, h, e);
				
				if(result == minCost) {					// 최단 경로가 같다면 g -> h 포함
					visit[e] = true;
					continue;
				}
				
				result = subCost;
				result += spfa(path, n, s, h);			// s -> h -> g -> e 최단경로
				result += spfa(path, n, g, e);
				
				if(minCost == result) visit[e] = true;	// 최단 경로가 같다면 h -> g 포함
			}
			
			for(int i = 0; i < n; i++) {
				if(visit[i]) sb.append(i + 1).append(SPACE);
			}
			sb.append(NEW_LINE);
		}
		
		System.out.println(sb);
	}
	
	private static int spfa(ArrayList<Node>[] path, int N, int start, int end) {
		boolean[] inQ = new boolean[N];
		
		int[] cost = new int[N];
		Arrays.fill(cost, Integer.MAX_VALUE);
		
		Queue<Node> q = new LinkedList<>();
		q.offer(new Node(start, 0));
		inQ[start] = true;
		
		cost[start] = 0;
		
		while(!q.isEmpty()) {
			Node current = q.poll();
			inQ[current.vertex] = false;
			
			for(Node next: path[current.vertex]) {
				if(cost[next.vertex] > next.cost + cost[current.vertex]) {
					cost[next.vertex] = next.cost + cost[current.vertex];
					
					if(!inQ[next.vertex]) {
						inQ[next.vertex] = true;
						
						q.offer(new Node(next.vertex, cost[next.vertex]));
					}
				}
			}
		}
		
		return cost[end];
	}
}
