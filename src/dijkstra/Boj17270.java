package dijkstra;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author exponential-e
 *	백준 17270번: 연예인은 힘들어
 *
 *	@see https://www.acmicpc.net/problem/17270/
 *
 */
public class Boj17270 {
	private static ArrayList<Node>[] path;
	private static final int INF = 1_000_000_001;
	
	private static class Node implements Comparable<Node>{
		int node;
		int cost;
		
		public Node(int node, int cost) {
			this.node = node;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node n) {
			return this.cost < n.cost ? -1: 1;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new	BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		path = new ArrayList[V];
		for(int i = 0; i < V; i++) {
			path[i] = new ArrayList<>();
		}
		
		while(M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int vertex1 = Integer.parseInt(st.nextToken()) - 1;
			int vertex2 = Integer.parseInt(st.nextToken()) - 1;
			int value = Integer.parseInt(st.nextToken());
			
			path[vertex1].add(new Node(vertex2, value));
			path[vertex2].add(new Node(vertex1, value));
		}
		
		st = new StringTokenizer(br.readLine());
		int J = Integer.parseInt(st.nextToken()) - 1;
		int S = Integer.parseInt(st.nextToken()) - 1;
		
		int[][] result = new int[2][V];
		
		result[0] = dijkstra(J, V);
		result[1] = dijkstra(S, V);
		
		int cost = INF;
		int spec = INF;
		int index = -1;
		
		for (int e = 0; e < V; e++) {
			if (e == J || e == S || result[0][e] == INF || result[1][e] == INF) continue;

			int sum = result[0][e] + result[1][e];
			cost = Math.min(cost, sum);									// find the shortest path
		}
		
		for (int e = 0; e < V; e++) {
			if (e == J || e == S || result[0][e] == INF || result[1][e] == INF) continue;
			
			int sum = result[0][e] + result[1][e];
			if (cost != sum || result[0][e] > result[1][e]) continue;

			if (spec >= result[0][e]) {									// find min j, min idx
				spec = result[0][e];
				index = e;
			}
		}

		System.out.print(index == -1 ? -1: index + 1);
	}
	
	private static int[] dijkstra(int start, int size) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(start, 0));
		
		int[] dist = new int[size];
		Arrays.fill(dist, INF);
		
		while(!pq.isEmpty()) {
			Node current = pq.poll();
			if(current.cost > dist[current.node]) continue;
			
			for(Node next: path[current.node]) {				
				if(dist[next.node] > next.cost + current.cost) {
					dist[next.node] = next.cost + current.cost;
					
					pq.offer(new Node(next.node, dist[next.node]));
				}
			}
		}
		
		return dist;
	}
}
