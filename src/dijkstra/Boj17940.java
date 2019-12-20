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
 *	백준 17940번: 지하철
 *
 *	@see https://www.acmicpc.net/problem/17940/
 *
 */
public class Boj17940 {
	private static final int INF = 1_000_000_000;
	private static final int TRANSFER = 10_000_000;
	
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
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		boolean[] station = new boolean[N];
		for(int i = 0; i < N; i++) {
			int idx = Integer.parseInt(br.readLine());
			if(idx == 0) continue;
			station[i] = true;
		}
		
		ArrayList<Node>[] path = new ArrayList[N];
		for(int i = 0; i < N; i++) {
			path[i] = new ArrayList<>();
		}
		
		for(int from = 0; from < N; from++) {
			st = new StringTokenizer(br.readLine());
			
			for(int to = 0; to < N; to++) {
				int cost = Integer.parseInt(st.nextToken());
				if(cost == 0) continue;
				
				if(station[from] ^ station[to]) path[from].add(new Node(to, cost + TRANSFER));		// different each other
				else path[from].add(new Node(to, cost));
			}
		}
		
		System.out.println(getDistance(N, M, station, path));
	}
	
	private static String getDistance(int n, int e, boolean[] stat, ArrayList<Node>[] list) {
		int[] cost = new int[n];
		Arrays.fill(cost, INF);
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		cost[0] = 0;
		
		pq.offer(new Node(0, 0));
		
		while(!pq.isEmpty()) {
			Node current = pq.poll();
			
			for(Node next: list[current.node]) {
				if(cost[next.node] > cost[current.node] + next.cost) {				// find Shortest path
					cost[next.node] = cost[current.node] + next.cost;
						
					pq.offer(new Node(next.node, cost[next.node]));
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		return sb.append(cost[e] / TRANSFER).append(" ").append(cost[e] % TRANSFER).toString();
	}
}
