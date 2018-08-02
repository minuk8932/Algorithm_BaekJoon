import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj1167 {
	private static int start = 1;
	private static int max = 0;
	private static int maxIdx = 0;

	private static ArrayList<Node>[] tree = null;
	private static boolean[] isVisited = null;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		tree = new ArrayList[N + 1];
		for (int i = 1; i < N + 1; i++) {
			tree[i] = new ArrayList<>();
		}

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());

			while (true) {
				int to = Integer.parseInt(st.nextToken());
				if (to == -1) break;

				int val = Integer.parseInt(st.nextToken());
				tree[from].add(new Node(to, val));
			}
		}
		
	isVisited = new boolean[N + 1];
	dfs(1, 0);

	isVisited=new boolean[N+1];
	max = 0;
	dfs(maxIdx, 0);

	System.out.println(max);
}

	private static class Node {
		int edge;
		int cost;

		public Node(int edge, int cost) {
			this.edge = edge;
			this.cost = cost;

		}
	}

	private static void dfs(int start, int val) {
		isVisited[start] = true;

		for (Node n : tree[start]) {
			if (!isVisited[n.edge]) {
				dfs(n.edge, val + n.cost);
			}
		}
		
		if(val > max) {
			max = val;
			maxIdx = start;
		}
	}
}
