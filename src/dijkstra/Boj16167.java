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
 *	백준 16167번: A Great Way
 *
 *	@see https://www.acmicpc.net/problem/16167/
 *
 */
public class Boj16167 {
	private static final String NO_ANSWER = "It is not a great way.";
	private static final String SPACE = " ";
	private static final int INF = 100_000;
	
	private static class Node implements Comparable<Node>{
		int edge;
		long cost;
		
		public Node(int edge, long cost) {
			this.edge = edge;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node n) {
			return this.cost < n.cost ? -1: 1;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		
		ArrayList<Node>[] graph = new ArrayList[N + 1];
		for(int i = 1; i < N + 1; i++) {
			graph[i] = new ArrayList<>();
		}
		
		while(R-- > 0) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int basic = Integer.parseInt(st.nextToken());
			int minutes = Integer.parseInt(st.nextToken());
			int time = Integer.parseInt(st.nextToken());
			
			int cost = time <= 10 ? basic : basic + (time - 10) * minutes;		// 경로의 비용
			graph[from].add(new Node(to, cost * INF + 1));						// 노드수 합산
		}
		
		System.out.println(dijkstra(N, graph));
	}
	
	private static StringBuilder dijkstra(int n, ArrayList<Node>[] list) {
		StringBuilder sb = new StringBuilder();
		long[] isVisited = new long[n + 1];
		Arrays.fill(isVisited, Long.MAX_VALUE);
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(1, 0));
			
		isVisited[1] = 0;
			
		while(!pq.isEmpty()) {
			Node current = pq.poll();
			if(isVisited[current.edge] != current.cost) continue;
			
			for(Node next: list[current.edge]) {
				if(isVisited[next.edge] >= isVisited[current.edge] + next.cost) {
					isVisited[next.edge] = isVisited[current.edge] + next.cost;		// 비용과 노드 갯수
					
					pq.offer(new Node(next.edge, isVisited[next.edge]));
				}
			}
		}
		
		return isVisited[n] == Long.MAX_VALUE ? sb.append(NO_ANSWER) : sb.append(isVisited[n] / INF).append(SPACE).append(isVisited[n] % INF + 1);
	}
}
