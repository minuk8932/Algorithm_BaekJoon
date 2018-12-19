package dijkstra;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 2211번: 네트워크 복구
 *
 *	@see https://www.acmicpc.net/problem/2211/
 *
 */
public class Boj2211 {
	private static final int INF = 100_000;
	private static final char NEW_LINE = '\n';
	private static final char SPACE = ' ';
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		ArrayList<Node>[] map = new ArrayList[N + 1];
		for(int i = 1; i < N + 1; i++) {
			map[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			map[from].add(new Node(to, cost));
			map[to].add(new Node(from, cost));
		}
		
		System.out.println(dijkstra(map, N));		// 결과 출력
	}
	
	private static class Node implements Comparable<Node>{
		int edge;
		int cost;
		
		public Node(int edge, int cost) {
			this.edge = edge;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node n) {
			return this.cost < n.cost ? -1 : 1;
		}
	}
	
	private static StringBuilder dijkstra(ArrayList<Node>[] graph, int n) {		
		int[] cost = new int[n + 1];
		int[] visited = new int[n + 1];
		
		Arrays.fill(cost, INF);
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(1, 0));
		cost[1] = 0;
		
		while(!pq.isEmpty()) {
			Node current = pq.poll();
			
			for(Node next: graph[current.edge]) {
				if(cost[next.edge] > cost[current.edge] + next.cost) {		// 작은 비용으로 초기화
					cost[next.edge] = cost[current.edge] + next.cost;
					visited[next.edge] = current.edge;						// 작은 비용이 발생한 경로 저장
					
					pq.offer(new Node(next.edge, cost[next.edge]));
				}
			}
		}
		
		return printRoute(visited);
	}
	
	private static StringBuilder printRoute(int[] arr) {
		StringBuilder sb = new StringBuilder();
		int counts = 0;
		
		for(int i = 1; i < arr.length; i++) {
			if(arr[i] == 0) continue;
			
			counts++;				// 최소 비용을 갖는 경로 갯수 저장
			sb.append(i).append(SPACE).append(arr[i]).append(NEW_LINE);	// 최소 비용 경로 저장
		}
		
		System.out.println(counts);
		return sb;
	}
}
