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
 *	백준 1162번: 도로 포장
 *
 *	@see https://www.acmicpc.net/problem/1162/
 *
 */
public class Boj1162 {
	private static final long INF = Long.MAX_VALUE;
	
	private static class Node implements Comparable<Node>{
		int edge;
		long cost;
		int pave;
		
		public Node(int edge, long cost) {
			this.edge = edge;
			this.cost = cost;
		}
		
		public Node(int edge, long cost, int pave) {
			this.edge = edge;
			this.cost = cost;
			this.pave = pave;
		}

		@Override
		public int compareTo(Node n) {
			return this.cost < n.cost ? -1 : 1;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		ArrayList<Node>[] road = new ArrayList[N];
		for(int i = 0; i < N; i++) {
			road[i] = new ArrayList<>();
		}
		
		while(M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int edge1 = Integer.parseInt(st.nextToken()) - 1;
			int edge2 = Integer.parseInt(st.nextToken()) - 1;
			int cost = Integer.parseInt(st.nextToken());
			
			road[edge1].add(new Node(edge2, cost));
			road[edge2].add(new Node(edge1, cost));
		}
		
		System.out.println(dijkstra(N, K, road));
	}
	
	private static long dijkstra(int n, int k, ArrayList<Node>[] list) {
		long[][] cost = new long[n][k + 1];
		for(int i = 0; i < n; i++) {
			Arrays.fill(cost[i], INF);
		}
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(0, 0, k));
			
		cost[0][k] = 0;
			
		while(!pq.isEmpty()) {
			Node current = pq.poll();

			if(cost[current.edge][current.pave] < current.cost) continue;
			
			for(Node next: list[current.edge]) {
				if(current.pave > 0) {
					if(cost[next.edge][current.pave - 1] > cost[current.edge][current.pave]) {				// paving
						cost[next.edge][current.pave - 1] = cost[current.edge][current.pave];
						pq.offer(new Node(next.edge, cost[next.edge][current.pave - 1], current.pave - 1));
					}
				}

				if(cost[next.edge][current.pave] <= cost[current.edge][current.pave] + next.cost) continue;	// not paving
				cost[next.edge][current.pave] = cost[current.edge][current.pave] + next.cost;
				pq.offer(new Node(next.edge, cost[next.edge][current.pave], current.pave));
			}
		}
		
		return getMinCost(n, k, cost);
	}
	
	private static long getMinCost(int n, int k, long[][] arr) {
		long min = INF;
		
		for(int i = 0; i < k + 1; i++) {
			if(arr[n - 1][i] < min) min = arr[n - 1][i];
		}
		
		return min;
	}
}
