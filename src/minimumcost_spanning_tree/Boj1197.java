package minimumcost_spanning_tree;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 1197번: 최소 스패닝 트리
 *
 *	@see https://www.acmicpc.net/problem/1197/
 *
 */
public class Boj1197 {
	private static int[] parent;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		
		PriorityQueue<Node> map = new PriorityQueue<>();
		
		parent = new int[V + 1];
		for(int i = 1; i < V + 1; i++) {
			parent[i] = i;
		}
		
		for(int i = 1; i < E + 1; i++){
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			map.offer(new Node(from, to, cost));
			map.offer(new Node(to, from, cost));
		}
		
		System.out.println(kruskal(V, E, map));		// 결과 출력
	}
	
	private static class Node implements Comparable<Node>{
		int node1;
		int node2;
		int cost;
		
		public Node(int node1, int node2, int cost){
			this.node1 = node1;
			this.node2 = node2;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node n) {
			return this.cost < n.cost ? -1 : 1;
		}
	}
	
	private static int kruskal(int v, int e, PriorityQueue<Node> pq){
		int minCost = 0;
		
		while(!pq.isEmpty()) {
			Node next = pq.poll();
			
			if(!merge(next.node1, next.node2)) {		// 사이클이면 계산 안함
				minCost += next.cost;
			}
		}
		
		return minCost;
	}
	
	private static int find(int from) {
		if(parent[from] == from) return from;
		else return find(parent[from]);
	}
	
	private static boolean merge(int x, int y) {
		x = find(x);
		y = find(y);
		
		if(x == y) return true;		// cycle
		
		if(x < y) parent[y] = x;
		else parent[x] = y;
		
		return false;				// uncycle
	}
}
