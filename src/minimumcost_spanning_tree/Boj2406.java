package minimumcost_spanning_tree;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 2406번: 안정적인 네트워크
 *
 *	@see https://www.acmicpc.net/problem/2406/
 *
 */
public class Boj2406 {
	private static int[] parent;
	
	private static final String SPACE = " ";
	private static final String NEW_LINE = "\n";
	
	private static class Network implements Comparable<Network>{
		int edge1;
		int edge2;
		int cost;
		
		public Network(int edge1, int edge2) {
			this.edge1 = edge1;
			this.edge2 = edge2;
		}
		
		public Network(int edge1, int edge2, int cost) {
			this.edge1 = edge1;
			this.edge2 = edge2;
			this.cost = cost;
		}

		@Override
		public int compareTo(Network n) {
			return this.cost < n.cost ? -1: 1;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		init(n);
		
		while(m-- > 0) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			merge(from, to);
		}
		
		
		PriorityQueue<Network> net = new PriorityQueue<>();
		boolean[][] isVisited = new boolean[n + 1][n + 1];
		
		for(int i = 1; i < n + 1; i++) {
			st = new StringTokenizer(br.readLine());
			
			for(int j = 1; j < n + 1; j++) {		
				int cost = Integer.parseInt(st.nextToken());
				
				if(i == 1 || j == 1 || isVisited[i][j]) continue;		// 이미 방문 또는 본사 연결 네트워크 제거
				isVisited[i][j] = isVisited[j][i] = true;
				
				if(cost != 0) net.offer(new Network(i, j, cost));		// 같은 지사의 연결인 경우 제외
			}
		}
		
		System.out.println(kruskal(n, net));
	}
	
	private static void init(int N) {
		parent = new int[N + 1];
		for(int i = 0; i < N + 1; i++) {
			parent[i] = -1;
		}
	}
	
	private static int find(int x) {
		if(parent[x] < 0) return x;
		else return parent[x] = find(parent[x]);
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
	
	private static StringBuilder kruskal(int N, PriorityQueue<Network> pq) {
		ArrayList<Network> nodes = new ArrayList<>();
		long cost = 0;
		
		while(!pq.isEmpty()) {
			Network current = pq.poll();
			
			if(!merge(current.edge1, current.edge2)) {				// 아직 연결 안된 지사 중		
				cost += current.cost;									// 최소로 연결 가능한 지사와 묶어줌
				nodes.add(new Network(current.edge1, current.edge2));
			}
		}
		
		StringBuilder sb = new StringBuilder();
		int total = nodes.size();
		
		sb.append(cost).append(SPACE).append(total).append(NEW_LINE);
		
		for(Network pair: nodes) {
			sb.append(pair.edge1).append(SPACE).append(pair.edge2).append(NEW_LINE);
		}
		
		return sb;
	}
}
