package search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author exponential-e
 *	백준 18243번: Small World Network
 *
 *	@see https://www.acmicpc.net/problem/18243/
 *
 */
public class Boj18243 {
	private static final String AC = "Small World!";
	private static final String WA = "Big World!";
	
	private static int[] parent;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		ArrayList<Integer>[] rel = new ArrayList[N];
		parent = new int[N];

		for(int i = 0; i < N; i++) {
			rel[i] = new ArrayList<>();
			parent[i] = -1;
		}
		
		while(K-- > 0) {
			st = new StringTokenizer(br.readLine());
			int node1 = Integer.parseInt(st.nextToken()) - 1;
			int node2 = Integer.parseInt(st.nextToken()) - 1;
			
			rel[node1].add(node2);
			rel[node2].add(node1);
		}
		
		System.out.println(result(N, rel));
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
	
	private static String result(int n, ArrayList<Integer>[] list) {
		Queue<Integer> q = new LinkedList<>();
		
		for(int start = 0; start < n; start++) {
			int[] visit = new int[n];
			q.offer(start);
			visit[start] = 1;
			
			while(!q.isEmpty()) {
				int current = q.poll();
				
				for(int next: list[current]) {
					if(visit[next] != 0) continue;
					visit[next] = visit[current] + 1;
					
					merge(start, next);
					
					if(visit[next] > 7) return WA;				// is small
					q.offer(next);
				}
			}
		}
		
		int count = 0;
		for(int i = 0; i < n; i++) {							// is one
			if(parent[i] < 0) count++;
		}
		
		return count <= 1 ? AC: WA;
	}
}
