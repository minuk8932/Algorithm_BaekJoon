package minimumcost_spanning_tree;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 15591번: MooTube(Silver)
 *
 *	@see https://www.acmicpc.net/problem/15591/
 *
 */
public class Boj15591 {
	private static final String NEW_LINE = "\n";
	
	private static int[] parent;
	private static int[] result;
	
	private static class Node implements Comparable<Node>{
		int from;
		int to;
		int usado;
		
		public Node(int from, int to, int usado) {
			this.from = from;
			this.to = to;
			this.usado = usado;
		}

		@Override
		public int compareTo(Node n) {
			return this.usado > n.usado ? -1: 1;
		}
	}
	
	private static class Query implements Comparable<Query>{
		int index;
		int usado;
		int video;
		
		public Query(int index, int usado, int video) {
			this.index = index;
			this.usado = usado;
			this.video = video;
		}

		@Override
		public int compareTo(Query q) {
			return this.usado > q.usado ? -1: 1;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		
		init(N);
		
		Node[] link = new Node[N - 1];
		
		for(int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int p = Integer.parseInt(st.nextToken()) - 1;
			int q = Integer.parseInt(st.nextToken()) - 1;
			int r = Integer.parseInt(st.nextToken());
			
			link[i] = new Node(p, q, r);
		}
		
		PriorityQueue<Query> quest = new PriorityQueue<>();
		result = new int[Q];
		
		for(int i = 0; i < Q; i++) {
			st = new StringTokenizer(br.readLine());
			quest.offer(new Query(i, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()) - 1));
		}
		
		makeResult(N, link, quest);
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < result.length; i++) {
			sb.append(result[i]).append(NEW_LINE);
		}
		
		System.out.println(sb);
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
	
	private static void makeResult(int n, Node[] arr, PriorityQueue<Query> pq) {
		int next = 0;
		Arrays.sort(arr);
		
		while(!pq.isEmpty()) {
			Query current = pq.poll();			// 유사도가 높은것 부터 연산
			
			for(; next < n - 1; next++) {
				if(arr[next].usado >= current.usado) merge(arr[next].to, arr[next].from);		// 주어진 유사도 보다 높은 유사도를 가지면 묶어줌
				else break;
			}
			
			result[current.index] = parent[find(current.video)] == -1 ? 0: -parent[find(current.video)] - 1;		// 그때 집합 갯수
		}
	}
}
