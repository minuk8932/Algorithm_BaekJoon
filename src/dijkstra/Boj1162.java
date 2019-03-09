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
		
		ArrayList<Node>[] road = new ArrayList[N + 1];
		for(int i = 0; i < N + 1; i++) {
			road[i] = new ArrayList<>();
		}
		
		while(M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int edge1 = Integer.parseInt(st.nextToken());
			int edge2 = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			road[edge1].add(new Node(edge2, cost));
			road[edge2].add(new Node(edge1, cost));
		}
		
		System.out.println(dijkstra(N, K, road));
	}
	
	private static long dijkstra(int n, int k, ArrayList<Node>[] list) {
		long[][] isVisited = new long[n + 1][k + 1];
		for(int i = 0; i < n + 1; i++) {
			Arrays.fill(isVisited[i], INF);
		}
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(1, 0, 0));
			
		isVisited[1][0] = 0;
			
		while(!pq.isEmpty()) {
			Node current = pq.poll();
			
			for(Node next: list[current.edge]) {
				if(isVisited[next.edge][current.pave] > isVisited[current.edge][current.pave] + next.cost) {		// 포장 없이 이동
					isVisited[next.edge][current.pave] = isVisited[current.edge][current.pave] + next.cost;
					
					pq.offer(new Node(next.edge, isVisited[next.edge][current.pave], current.pave));
					
					if(current.pave < k) {					// 포장 횟수가 남은 경우
						int nextPave = current.pave + 1;
						
						if(isVisited[next.edge][nextPave] > current.cost) {		// 포장 후 이동
							isVisited[next.edge][nextPave] = current.cost;
								
							pq.offer(new Node(next.edge, isVisited[next.edge][nextPave], nextPave));
						}
					}
				}
			}
		}
		
		return getMinCost(n, k, isVisited);
	}
	
	private static long getMinCost(int n, int k, long[][] arr) {
		long min = INF;
		
		for(int i = 0; i < k + 1; i++) {			// 0 ~ k 번 포장 중 최소 비용
			if(arr[n][i] < min) min = arr[n][i];
		}
		
		return min;
	}
}
