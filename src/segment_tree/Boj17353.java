package segment_tree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 17353번: 하늘에서 떨어지는 1, 2, ..., R-L+1개의 별
 *
 * @see https://www.acmicpc.net/problem/17353/
 *
 */
public class Boj17353 {
	private static int start = 1;
	private static long[] tree;
	private static int[] count;

	private static int N;

	private static final String NEW_LINE = "\n";

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		init();

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = start; i < N + start; i++) {
			tree[i] = Long.parseLong(st.nextToken());
		}

		StringBuilder sb = new StringBuilder();
		int Q = Integer.parseInt(br.readLine());

		while(Q-- > 0) {
			st = new StringTokenizer(br.readLine());
			int method = Integer.parseInt(st.nextToken());

			if (method == 1) {
				int L = Integer.parseInt(st.nextToken()) - 1;
				int R = Integer.parseInt(st.nextToken()) - 1;

				add(L, R, 1, 0, start - 1);
			}
			else {
				int X = Integer.parseInt(st.nextToken());
				sb.append(sum(X - 1, X - 1, 1, 0, start - 1)).append(NEW_LINE);
			}
		}

		System.out.print(sb.toString());
	}

	private static void init(){
		while(start <= N) start <<= 1;

		tree = new long[start * 2];
		count = new int[start * 2];
	}

	private static int[] makeSon(int node){
		int son = node * 2;
		return new int[]{son, ++son};
	}

	private static void add(int s, int e, int node, int ns, int ne){
		if(e < ns || ne < s) return;
		if(s <= ns && ne <= e) {
			tree[node] += ns - s + 1;			// dropped stars
			count[node]++;						// dropped count

			return;
		}

		int[] son = makeSon(node);
		int mid = (ns + ne) / 2;

		add(s, e, son[0], ns, mid);
		add(s, e, son[1], mid + 1, ne);
	}

	private static long sum(int s, int e, int node, int ns, int ne){
		if(e < ns || ne < s) return 0;
		if(s <= ns && ne <= e) return tree[node];

		int[] son = makeSon(node);
		int mid = (ns + ne) / 2;

		return sum(s, e, son[0], ns, mid) + sum(s, e, son[1], mid + 1, ne) + tree[node] + count[node] * (s - ns);		// sum with count
	}
}
