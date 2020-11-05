package segment_tree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 1168번: 조세퍼스 문제 2
 *
 *	@see https://www.acmicpc.net/problem/1168/
 *
 */
public class Boj1168 {
	private static final String PRE = "<";
	private static final String SPACE = ", ";
	private static final String POST = ">";

	private static int[] tree;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		initiator(N);
		System.out.println(josephus(N, M));
	}

	private static void initiator(int n) {
		int size = n << 2;
		tree = new int[size];

		for (int i = 0; i < n; i++) {
			update(i + 1, 1, 1, 1, n);
		}
	}

	private static String josephus(int n, int m){
		StringBuilder sb = new StringBuilder();
		sb.append(PRE);

		int mod = n;
		int current = m;

		for (int i = 1; i <= n; i++) {
			int src;

			if(i == 1) {
				sb.append(current).append(SPACE);						// save sequence
			}
			else {
				src = sum(1, current, 1, 1, n) + m;		// next target position
				src %= mod;

				sb.append(current = getNext(src == 0 ? mod: src, 1, 1, n)).append(SPACE);
			}

			update(current, 0, 1, 1, n);
			mod--;
		}

		return sb.substring(0, sb.length() - 2) + POST;
	}

	private static int sum(int start, int end, int node, int left, int right) {
		if (right < start || end < left) return 0;
		if (start <= left && right <= end) return tree[node];

		int mid = (right + left) >> 1;
		int[] son = makeSon(node * 2);

		return sum(start, end, son[0], left, mid) + sum(start, end, son[1], mid + 1, right);
	}

	private static int getNext(int value, int node, int left, int right) {
		if (left == right) return left;

		int mid = (left + right) >> 1;
		int[] son = makeSon(node * 2);

		if (tree[son[0]] >= value) return getNext(value, son[0], left, mid);
		else return getNext(value - tree[son[0]], son[1], mid + 1, right);
	}

	private static void update(int target, int value, int node, int left, int right) {
		if (target < left || right < target) return;
		if (left == right) {
			tree[node] = value;
			return;
		}

		int mid = (left + right) >> 1;
		int[] son = makeSon(node * 2);

		update(target, value, son[0], left, mid);
		update(target, value, son[1], mid + 1, right);

		tree[node] = tree[son[0]] + tree[son[1]];
	}

	private static int[] makeSon(int son) {
		return new int[] {son, ++son};
	}
}
