
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj1967 {
	private static final String SPACE = " ";

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		ArrayList<Node>[] tree = new ArrayList[n + 1];

		for (int i = 1; i < n + 1; i++) {
			tree[i] = new ArrayList<>();
		}

		for (int i = 1; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
			int parent = Integer.parseInt(st.nextToken());
			int son = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());

			tree[parent].add(new Node(son, weight));
			tree[son].add(new Node(parent, weight));
		}

		int sum = 0;
		int res = 0;
		
		int[] isVisited = new int[n + 1];

		for (int left = 1; left < n + 1; left++) {
			Node node = tree[left].get(0);
			sum = node.cost;
			isVisited[left] = 1;
			
			Queue<Node> queue = new LinkedList<>();
			queue.offer(new Node(left, node.cost));

			while (!queue.isEmpty()) {
				Node poll = queue.poll();
				int current = poll.idx;

				for (Node next : tree[current]) {	
					System.out.print(current + " " +next.idx + "\t");
					if (next.idx > 0 && next.idx < n + 1) {
						if (isVisited[next.idx] == 0) {
							isVisited[next.idx] = 1;
							sum += next.cost;
							
//							System.out.print(current + " " + next.idx + "\t");

							queue.offer(new Node(next.idx, next.cost));
						}
					}
				}
				
				res = Math.max(res, sum);
			}
			System.out.println();
		}

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
}
