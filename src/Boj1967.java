
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj1967 {
	private static final String SPACE = " ";
	
	private static ArrayList<Node>[] tree = null;
	private static int n = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());

		tree = new ArrayList[n + 1];
		for (int i = 1; i < n + 1; i++) {
			tree[i] = new ArrayList<>();
		}

		for (int i = 1; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
			int parent = Integer.parseInt(st.nextToken());
			int son = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());

			tree[parent].add(new Node(son, weight));
		}

		int sum = 0;
		int res = 0;
		
		bfs();

		System.out.println(res);
	}

	private static class Node {
		int idx;
		int cost;

		public Node(int idx, int cost) {
			this.idx = idx;
			this.cost = cost;
		}
	}
	
	private static void bfs(){
		for(int leaf = 1; leaf < n + 1; leaf++){
			int[] isVisited = new int[n + 1];
			
			Queue<Node> q = new LinkedList<>();
			q.offer(new Node(leaf, 0));
		}
	}
}
