package bandwidth_first_serch;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj1967 {
	private static final String SPACE = " ";

	private static final int[][] DIRECTIONS = { { 1, 0 }, { -1, 0 } };
	private static final int LEFT = 0;
	private static final int RIGHT = 1;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		ArrayList<Node>[] tree = new ArrayList[n + 1];

		for (int i = 1; i < n + 1; i++) {
			tree[i] = new ArrayList<>();
		}

		while (n-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
			int parent = Integer.parseInt(st.nextToken());
			int son = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());

			tree[parent].add(new Node(son, weight));
			tree[son].add(new Node(parent, weight));
		}

		int[] isVisited = new int[n + 1];

		for (int left = 1; left < n + 1; left++) {
			if (isVisited[left] == 0) {
				isVisited[left] = 1;

				Node node = tree[left].get(0);

				Queue<Node> queue = new LinkedList<>();
				queue.offer(new Node(left, node.cost));
				
				while (!queue.isEmpty()) {
					Node current = queue.poll();
					
					for (final int[] DIRECTION : DIRECTIONS) {
						int nextLeft = current.idx + DIRECTION[LEFT];
						int nextRight = current.idx + DIRECTION[RIGHT];
					}
				}
			}
		}

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
