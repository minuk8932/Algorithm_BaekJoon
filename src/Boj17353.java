import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj17353 {
	private static int start = 1;
	private static long[] tree;
	private static long[] lazy;

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
				int L = Integer.parseInt(st.nextToken());
				int R = Integer.parseInt(st.nextToken());

				add(L, R, 1, 0, start - 1);
				for(int i = 0; i < tree.length; i++) System.out.print(tree[i] + " ");
				System.out.println();
			}
			else {
				int X = Integer.parseInt(st.nextToken());
				sb.append(sum(X - 1, X, 1, 0, start - 1)).append(NEW_LINE);
			}
		}

		for(int i = 0; i < tree.length; i++) System.out.print(tree[i] + " ");
		System.out.println();

		System.out.print(sb.toString());
	}

	private static void init(){
		while(start <= N) start <<= 1;

		tree = new long[start * 2];
		lazy = new long[start * 2];
//		start /= 2;
	}

	private static int[] makeSon(int node){
		int son = node * 2;
		return new int[]{son, ++son};
	}

	private static void propagation(int node, int s, int e){
		if(lazy[node] == 0) return;

		if(node < start){
			int[] son = makeSon(node);

			lazy[son[0]] += lazy[node];
			lazy[son[1]] += lazy[node];
		}

		tree[node] += lazy[node] * (e - s + 1);
		lazy[node] = 0;
	}

	private static void add(int s, int e, int node, int ns, int ne){
		propagation(node, ns, ne);

		if(e < ns || ne < s) return;
		if(s <= ns && ne <= e) {
			lazy[node] += ns - s + 1;
			propagation(node, ns, ne);

			return;
		}

		int[] son = makeSon(node);
		int mid = (ns + ne) / 2;

		add(s, e, son[0], ns, mid);
		add(s, e, son[1], mid + 1, ne);

		tree[node] = tree[son[0]] + tree[son[1]];
	}

	private static long sum(int s, int e, int node, int ns, int ne){
		propagation(node, ns, ne);

		if(e < ns || ne < s) return 0;
		if(s <= ns && ne <= e) return tree[node];

		int[] son = makeSon(node);
		int mid = (ns + ne) / 2;

		return sum(s, e, son[0], ns, mid) + sum(s, e, son[1], mid + 1, ne);
	}
}
