package minimumcost_spanning_tree;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 13905번: 세부
 *
 *	@see https://www.acmicpc.net/problem/13905/
 *
 */
public class Boj13905 {
	private static int[] parent;
	
	private static class Node implements Comparable<Node>{
		int edge1;
		int edge2;
		int cost;
		
		public Node(int edge1, int edge2, int cost) {
			this.edge1 = edge1;
			this.edge2 = edge2;
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
		
		init(N);
		
		st = new StringTokenizer(br.readLine());
		int s = Integer.parseInt(st.nextToken()) - 1;
		int e = Integer.parseInt(st.nextToken()) - 1;
		
		PriorityQueue<Node> path = new PriorityQueue<>();
		while(M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken()) - 1;
			int to = Integer.parseInt(st.nextToken()) - 1;
			int cost = Integer.parseInt(st.nextToken());
			
			path.add(new Node(from, to, -cost));			// 비용 내림차순
		}
		
		System.out.println(kruskal(N, s, e, path));
	}
	
	private static void init(int n) {
		parent = new int[n];
		for(int i = 0; i < n; i++) {
			parent[i] = -1;
		}
	}
	
	private static int find(int x) {
		if(parent[x] < 0) return x;
		return parent[x] = find(parent[x]);
	}
	
	private static boolean merge(int x, int y) {
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
	
	private static int kruskal(int n, int start, int end, PriorityQueue<Node> pq) {
		int result = 0;
		
		while(!pq.isEmpty()) {
			Node current = pq.poll();
			
			if(!merge(current.edge1, current.edge2)) {
				if(find(start) == find(end)) {			// 시작과 끝이 서로 연결된 경우 비용 저장 후 종료
					result = -current.cost;
					break;
				}
			}
		}
		
		int sParent = parent[start] < 0 ? parent[start]: parent[find(start)];		// 시작 끝의 부모가 같은지 판별
		int eParent = parent[end] < 0 ? parent[end]: parent[find(end)];
		
		return sParent == eParent ? result: 0;
	}
}
