package minimumcost_spanning_tree;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 1774번: 우주신과의 교감 by Kruskal
 *
 *	@see https://www.acmicpc.net/problem/1774/
 *
 */
public class Boj1774 {
	private static int[] parent;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		init(N);
		
		Point[] coordinates = new Point[N + 1];
		
		for(int i = 1; i < N + 1; i++) {				// i번째 좌표 저장
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			coordinates[i] = new Point(x, y);
		}
		
		double[][] distance = distInit(N, coordinates);
		
		while(M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int node1 = Integer.parseInt(st.nextToken());
			int node2 = Integer.parseInt(st.nextToken());
			
			merge(node1, node2);
			distance[node1][node2] = 0;
			distance[node2][node1] = 0;
		}
		
		PriorityQueue<Node> link = pqInit(N, distance);
		System.out.printf("%.2f", MST(link, coordinates));		// 소수점 둘째자리까지 결과 출력
	}
	
	private static class Point{
		int x;
		int y;
		
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	private static class Node implements Comparable<Node>{
		int from;
		int to;
		double cost;
		
		public Node(int from, int to, double cost) {
			this.from = from;
			this.to = to;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node n) {
			return this.cost < n.cost ? -1: 1;
		}
	}
	
	private static void init(int N) {
		parent = new int[N + 1];
		
		for(int i = 1; i < N + 1; i++) {
			parent[i] = i;
		}
	}
	
	private static double[][] distInit(int N, Point[] coor){
		double[][] arr = new double[N + 1][N + 1];
		
		for(int i = 1; i < N + 1; i++) {
			if(coor[i] == null) continue;
			
			for(int j = 1; j < N + 1; j++) {		// 존재하는 좌표에서 거리 구함
				if(coor[j] == null) continue;
				if(i == j) continue;
				
				double dist = getDist(coor[i], coor[j]);
				arr[i][j] = dist;
			}
		}
		
		return arr;
	}
	
	private static PriorityQueue<Node> pqInit(int N, double[][] arr){	// 우선순위 큐 초기화
		PriorityQueue<Node> pq = new PriorityQueue<>();
		
		for(int i = 1; i < N + 1; i++) {
			for(int j = 1; j < N + 1; j++) {
				if(arr[i][j] == 0) continue;
				
				pq.offer(new Node(i, j, arr[i][j]));
				pq.offer(new Node(j, i, arr[i][j]));
			}
		}
		
		return pq;
	}
	
	private static double getDist(Point p1, Point p2) {
		double powDist = Math.pow((p2.x - p1.x), 2) + Math.pow((p2.y - p1.y), 2);
		return powDist;
	}
	
	private static int find(int node) {
		if(parent[node] == node) return node;
		else return find(parent[node]);
	}
	
	private static void merge(int star1, int star2) {
		star1 = find(star1);
		star2 = find(star2);
		
		if(star1 < star2) parent[star2] = star1;
		else parent[star1] = star2;
	}
	
	private static boolean isCycle(int star1, int star2) {
		star1 = find(star1);
		star2 = find(star2);
		
		if(star1 == star2) return true;
		else return false;
	}
	
	private static double MST(PriorityQueue<Node> pq, Point[] p) {
		double bbangSang = 0;
		
		while(!pq.isEmpty()) {
			Node next = pq.poll();
			
			if(!isCycle(next.from, next.to)) {
				bbangSang += Math.sqrt(next.cost);
				merge(next.from, next.to);
			}
		}
		
		return bbangSang;
	}
}
