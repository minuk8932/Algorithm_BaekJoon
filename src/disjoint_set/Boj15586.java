package disjoint_set;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 15586번: MooTube (Gold)
 *
 *	@see https://www.acmicpc.net/problem/15586/
 *
 */
public class Boj15586 {
	private static final String NEW_LINE = "\n";
	private static int[] parent;
	
	private static class Node implements Comparable<Node> {
		int from;
		int to;
		int cost;
		
		public Node(int from, int to, int cost) {
			this.from = from;
			this.to = to;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node n) {
			return this.cost > n.cost ? -1 : 1;
		}
	}
	
	private static class Query implements Comparable<Query> {
		int seq;
		int video;
		int usado;
		
		public Query(int seq, int video, int usado) {
			this.seq = seq;
			this.video = video;
			this.usado = usado;
		}

		@Override
		public int compareTo(Query q) {
			return this.usado > q.usado ? -1 : 1;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		
		parent = new int[N];
		PriorityQueue<Query> query = new PriorityQueue<>();
		Node[] video = new Node[N - 1];
		init();
		
		for(int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			int usado = Integer.parseInt(st.nextToken());
			
			video[i] = new Node(x, y, usado);
		}
		
		Arrays.sort(video);
		
		for(int i = 0; i < Q; i++) {			
			st = new StringTokenizer(br.readLine());
			int k = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken()) - 1;
			query.offer(new Query(i, v, k));				// 유사도가 클 수록 집합자체는 작기 때문에 정렬이 필요
		}
		
		System.out.println(getUsado(N, Q, video, query));
	}
	
	private static StringBuilder getUsado(int n, int m, Node[] arr, PriorityQueue<Query> pq) {
		StringBuilder sb = new StringBuilder();
		int[] queryResult = new int[m];
		int next = 0;
		
		while(!pq.isEmpty()) {
			Query q = pq.poll();
			
			for(; next < n -1; next++) {
				if(arr[next].cost >= q.usado) merge(arr[next].to, arr[next].from);		// 추천 가능
				else break;																// 추천 경로 끊어짐, 유사도 크기에 따라 정렬되었으므로 짤라도 무관
			}
			
			queryResult[q.seq] = -parent[find(q.video)] - 1;		// 해당 노드의 부모 값 -1 (자신의 숫자 제외)
		}
		
		for(int i = 0; i < queryResult.length; i++) {
			sb.append(queryResult[i]).append(NEW_LINE);
		}
		
		return sb;
	}
	
	private static void init() {
		for(int i = 0; i < parent.length; i++) {
			parent[i] = -1;
		}
	}
	
	private static int find(int x) {
		if(parent[x] < 0) return x;
		else return parent[x] = find(parent[x]);
	}
	
	private static void merge(int x, int y) {
		x = find(x);
		y = find(y);
		
		if(x == y) return;
		
		if(parent[x] < parent[y]) {
			parent[x] += parent[y];
			parent[y] = x;
		}
		else {
			parent[y] += parent[x];
			parent[x] = y;
		}
	}
}
