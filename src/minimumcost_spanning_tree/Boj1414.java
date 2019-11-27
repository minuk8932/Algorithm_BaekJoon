package minimumcost_spanning_tree;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

/**
 * 
 * 	@author exponential-e
 *	백준 1414번: 불우이웃돕기
 *
 *	@see https://www.acmicpc.net/problem/1414/
 *
 */
public class Boj1414 {
	private static int[] parent;
	private static final int INF = 100;
	
	private static class Node implements Comparable<Node>{
		int com1;
		int com2;
		int cost;
		
		public Node(int com1, int com2, int cost) {
			this.com1 = com1;
			this.com2 = com2;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node n) {
			return this.cost < n.cost ? -1: 1;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		init(N);
		PriorityQueue<Node> pq = new PriorityQueue<>();
		int total = 0;
		
		for(int i = 0; i < N; i++) {
			String line = br.readLine();
			
			for(int j = 0; j < N; j++) {
				char val = line.charAt(j);
				int cost = 0;
				
				if(val == '0') cost = INF;									// convert values
				else if(val >= 'a' && val <= 'z') cost = val - 'a' + 1;
				else cost = val - 'A' + 27;
				
				total += cost == INF ? 0: cost;
				pq.offer(new Node(i, j, cost));
			}
		}
		
		System.out.println(kruskal(N, pq, total));
	}
	
	private static int kruskal(int n, PriorityQueue<Node> pq, int total) {
		int max = 0;
		
		while(!pq.isEmpty()) {
			Node current = pq.poll();
			
			if(current.cost == INF) continue;					// no wires
			if(merged(current.com1, current.com2)) continue;	// linked
			max += current.cost;
		}
		
		int sets = 0;
		for(int i = 0; i < n; i++) {							// is unconnected? check
			if(parent[i] < 0) sets++;
		}
		
		return sets >= 2 ? -1: total - max;
	}
	
	private static void init(int n) {
		parent = new int[n];
		for(int i = 0; i < n; i++) {
			parent[i] = -1;
		}
	}
	
	private static int find(int x) {
		if(parent[x] < 0) return x;
		else return parent[x] = find(parent[x]);
	}
	
	private static boolean merged(int x, int y) {
		x = find(x);
		y = find(y);
		
		if(x == y) return true;
		
		if(parent[x] < parent[y]) {
			parent[x] += parent[y];
			parent[y] = x;
		}
		else {
			parent[y] += parent[x];
			parent[x] = y;
		}
		
		return false;
	}
}
