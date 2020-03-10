import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj17353 {
	private static final int INF = 1 << 20;
	private static int start = INF / 2;
	private static long tree[] = new long[INF];
	private static long lazy[] = new long[INF];

	private static final String NEW_LINE = "\n";

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = start; i < N + start; i++) {
			tree[i] = Long.parseLong(st.nextToken());
		}

		init();

		StringBuilder sb = new StringBuilder();
		int Q = Integer.parseInt(br.readLine());

		while(Q-- > 0) {
			st = new StringTokenizer(br.readLine());
			int method = Integer.parseInt(st.nextToken());

			if (method == 1) {
				int A = Integer.parseInt(st.nextToken());
				int B = Integer.parseInt(st.nextToken());

				add(A, B, 1,1, 0, start);
			}
			else {
				int X = Integer.parseInt(st.nextToken()) - 1;
				sb.append(sum(X, 1, 0, start)).append(NEW_LINE);
			}
		}

		for(int i = start; i < start + N; i++){
			System.out.print(tree[i] + " ");
		}
		System.out.println();

		System.out.print(sb.toString());
	}

	private static int[] makeSon(int node){
		return new int[]{node * 2, node * 2 + 1};
	}

	private static void init(){
		for(int i = start - 1; i > 0; i--){
			int[] son = makeSon(i);
			tree[i] = tree[son[0]] + tree[son[1]];
		}
	}

	private static void propagation(int node, int s, int e){
		if(lazy[node] == 0) return;

		if(node < start){
			int[] son = makeSon(node);
			lazy[son[0]] += lazy[node];
			lazy[son[1]] += lazy[node];
		}

		tree[node] += lazy[node] * (e - s);
		lazy[node] = 0;
	}

	private static void add(int s, int e, int k, int node, int S, int E){
		propagation(node, S, E);

		if(e <= S || E <= s) return;
		if(s <= S && E <= e){
			lazy[node] += k;
			propagation(node, S, E);
			return;
		}

		System.out.println(S + " " + E + " " + k);

		int mid = (S + E) / 2;
		int[] son = makeSon(node);

		add(s, e, k + 1, son[0], S, mid);
		add(s, e, k + 1, son[1], mid, E);

		tree[node] = tree[son[0]] + tree[son[1]];
	}

	private static long sum(int x, int node, int S, int E){
		propagation(node, S, E);
		return tree[x + start];
	}
}
