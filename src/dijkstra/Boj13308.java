package dijkstra;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author exponential-e
 *	백준 13308번: 주유소
 *
 *	@see https://www.acmicpc.net/problem/13308/
 *
 */
public class Boj13308 {
	private static class Node implements Comparable<Node>{
		int node;
		int cost;
		long dist;
		
		public Node(int node, int cost, long dist) {
			this.node = node;
			this.cost = cost;
			this.dist = dist;
		}
		
		public Node(int node, int cost) {
			this.node = node;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node n) {
			return this.dist < n.dist ? -1: 1;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] value = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			value[i] = Integer.parseInt(st.nextToken());
		}
		
		ArrayList<Node>[] path = new ArrayList[N];
		
		for(int i = 0; i < N; i++) {
			path[i] = new ArrayList<>();
		}
		
		while(M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(st.nextToken()) - 1;
			int n2 = Integer.parseInt(st.nextToken()) - 1;
			int cost = Integer.parseInt(st.nextToken());
			
			path[n1].add(new Node(n2, cost));
			path[n2].add(new Node(n1, cost));
		}
		
		System.out.println(dijkstra(N, path, value));
	}
	
	private static long dijkstra(int n, ArrayList<Node>[] list, int[] val) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(0, val[0], 0));
		
		boolean[][] visit = new boolean[n][3_001];
		
		while(!pq.isEmpty()) {
			Node current = pq.poll();
			
			if(visit[current.node][current.cost]) continue;
			if(current.node == n - 1) return current.dist;			// minimum
			visit[current.node][current.cost] = true;				// current place with cost?
			
			for(Node next: list[current.node]) {
				pq.offer(new Node(next.node, Math.min(current.cost, val[next.node]), current.cost * next.cost + current.dist));
			}
		}
		
		return 0;
	}
}
