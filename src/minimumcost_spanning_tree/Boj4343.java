package minimumcost_spanning_tree;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 4343번: Arctic Network
 *
 *	@see https://www.acmicpc.net/problem/4343/
 *
 */
public class Boj4343 {
	private static int[] parent;
	private static final String NEW_LINE = "\n";
	
	private static class Communication implements Comparable<Communication>{
		int edge1;
		int edge2;
		double cost;
		
		public Communication(int edge1, int edge2, double cost) {
			this.edge1 = edge1;
			this.edge2 = edge2;
			this.cost = cost;
		}

		@Override
		public int compareTo(Communication c) {
			return this.cost < c.cost ? -1 : 1;
		}
	}
	
	private static class Pair{
		int x, y;
		
		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		
		while(N-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int S = Integer.parseInt(st.nextToken());
			int P = Integer.parseInt(st.nextToken());
			
			init(P);
			
			Pair[] base = new Pair[P];
			for(int i = 0; i < P; i++) {
				st = new StringTokenizer(br.readLine());
				base[i] = new Pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}
			
			PriorityQueue<Communication> state = new PriorityQueue<>();
			boolean[][] isLinked = new boolean[P][P];
			
			for(int i = 0; i < P; i++) {
				for(int j = i + 1; j < P; j++) {
					if(isLinked[i][j]) continue;
					isLinked[i][j] = isLinked[j][i] = true;
					
					double d = getDistance(base[i], base[j]);
					state.offer(new Communication(i, j, d));		// 노드 간 거리 저장
				}
			}
			
			double result = kruskal(P, S, state);
			sb.append(String.format("%.2f", result)).append(NEW_LINE);
		}
		
		System.out.println(sb);
	}
	
	private static double getDistance(Pair p1, Pair p2) {
		return Math.sqrt((p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y));
	}
	
	private static void init(int size) {
		parent = new int[size];
		
		for(int i = 0; i < size; i++) {
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
	
	private static double kruskal(int size, int sat, PriorityQueue<Communication> pq) {
		double max = 0;
		LinkedList<Double> list = new LinkedList<>();
		
		while(!pq.isEmpty()) {
			Communication current = pq.poll();
			
			if(!merge(current.edge1, current.edge2)) {		// 연결되는 네트워크를 모두 리스트에 저장
				list.add(current.cost);
			}
		}
		
		if(sat == 1) return list.removeLast();			// 1인 경우 위성 통신 불가
		
		while(sat-- > 0) {								// 통신에 가장 오래 걸리는 것 중 위성 설치 갯수 만큼 제외
			max = list.removeLast();					// 마지막 값이 연결하는데 필요한 비용 중 최대
		}
		
		return max;
	}
}
