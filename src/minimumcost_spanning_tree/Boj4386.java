package minimumcost_spanning_tree;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 4386번: 별자리 만들기 by kruskal
 *
 *	@see https://www.acmicpc.net/problem/4386/
 *
 */
public class Boj4386 {
	private static int[] parent;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		double[][] point = new double[n][2];
		parent = init(n);
		
		for(int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			point[i][0] = Double.parseDouble(st.nextToken());
			point[i][1] = Double.parseDouble(st.nextToken());
		}
		
		PriorityQueue<Node> stars = makePath(point, n);
		System.out.println(kruskal(stars));
	}
	
	public static class Node implements Comparable<Node>{
		int node1;
		int node2;
		double cost;
		
		public Node(int node1, int node2, double cost) {
			this.node1 = node1;
			this.node2 = node2;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node n) {
			return this.cost < n.cost ? -1 : 1;
		}
	}
	
	private static int[] init(int N) {
		int[] arr = new int[N];
		
		for(int i = 0; i < N; i++) {
			arr[i] = i;
		}
		
		return arr;
	}
	
	private static PriorityQueue<Node> makePath(double[][] arr, int N){
		PriorityQueue<Node> pq = new PriorityQueue<>();
		
		for(int i = 0; i < N; i++) {
			for(int j = i + 1; j < N; j++) {
				double distance = getDistance(arr[i][0], arr[i][1], arr[j][0], arr[j][1]);		// 별들 간 거리 계산
				pq.offer(new Node(i, j, distance));
			}
		}
		
		return pq;
	}
	
	private static double getDistance(double x1, double y1, double x2, double y2) {
		double d = Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2);
		return Math.sqrt(d);
	}
	
	private static int find(int from) {
		if(parent[from] == from) return from;
		else return find(parent[from]);
	}
	
	private static boolean isCycle(int x, int y) {
		x = find(x);
		y = find(y);
		
		if(x == y) return true;
		else return false;
	}
	
	private static void merge(int x, int y) {
		x = find(x);
		y = find(y);
		
		if(x < y) parent[y] = x;
		else parent[x] = y;
	}
	
	private static double kruskal(PriorityQueue<Node> pq) {
		double minCost = 0;
		
		while(!pq.isEmpty()) {
			Node next = pq.poll();
			
			if(!isCycle(next.node1, next.node2)) {		// 아직 연결안된 최소 거리?
				merge(next.node1, next.node2);			// 연결
				 minCost += next.cost;					// 거리 합
			}
		}
		
		minCost = getResult(minCost);
		return minCost;
	}
	
	private static double getResult(double value) {		// 소수점 2째자리 밑으로 버림
		value = Math.floor(value * 100) / 100;
		
		return value;
	}
}
