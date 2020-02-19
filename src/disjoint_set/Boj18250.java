package disjoint_set;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 18250번: 철도 여행
 *
 * @see https://www.acmicpc.net/problem/18250/
 *
 */
public class Boj18250 {
	private static int[] parent;
	private static int[] terminals;

	private static class Pair{
		int node1;
		int node2;

		public Pair(int node1, int node2){
			this.node1 = node1;
			this.node2 = node2;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		ArrayList<Pair> pairs = new ArrayList<>();
		int[] nodes = new int[N];
		terminals = new int[N];

		while(M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;

			pairs.add(new Pair(a, b));

			nodes[a]++;
			nodes[b]++;
		}

		init(N, nodes);
		for(Pair p: pairs) {
			merge(p.node1, p.node2);			// make path
		}

		System.out.println(getPath(N));
	}

	private static void init(int n, int[] nodes){
		parent = new int[n];

		for(int i = 0; i < n; i++){
			terminals[i] = nodes[i] % 2;
			parent[i] = -1;
		}
	}

	private static int find(int x){
		if(parent[x] < 0) return x;
		else return parent[x] = find(parent[x]);
	}

	private static void merge(int x, int y){
		x = find(x);
		y = find(y);

		if(x == y) return;

		if(parent[x] < parent[y]){
			parent[x] += parent[y];
			parent[y] = x;
			terminals[x] += terminals[y];				// terminal nodes count
		}
		else{
			parent[y] += parent[x];
			parent[x] = y;
			terminals[y] += terminals[x];
		}
	}
	
	private static int getPath(int n) {
		int result = 0;

		for(int cur = 0; cur < n; cur++){
			int p = find(cur);
			if(parent[p] == -1 || p != cur) continue;	// one node or is not terminal

			result += terminals[p] == 0 ? 1: terminals[p] / 2;	// just one path?
		}

		return result;
	}
}
