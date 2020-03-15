import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj14504 {
	private static int[] tree;
	private static int[] lazy;

	private static int N, K, S = 1;
	private static final String NEW_LINE = "\n";

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		init();

		st = new StringTokenizer(br.readLine());
		for(int i = S; i < S + N; i++){
			tree[i] = Integer.parseInt(st.nextToken());
		}

		for(int i = S - 1; i > 0; i--){
			int[] son = makeSon(i);
			tree[i] = tree[son[0]] | tree[son[1]];
		}

		StringBuilder sb = new StringBuilder();
		int M = Integer.parseInt(br.readLine());

		while(M-- > 0){
			st = new StringTokenizer(br.readLine());
			int cmd = Integer.parseInt(st.nextToken());
			int i = Integer.parseInt(st.nextToken()) - 1;
			int j = Integer.parseInt(st.nextToken()) - 1;
			int k = Integer.parseInt(st.nextToken());

			if(cmd == 2){
				update(i, i, k, 1, 0, N - 1);
			}
			else{
				sb.append(compare(i, j, 1, 0, N - 1)).append(NEW_LINE);
			}
		}

		System.out.println(sb.toString());
	}

	private static void init(){
		while(S <= N) S <<= 1;

		tree = new int[S * 2];
		lazy = new int[S * 2];
	}

	private static int[] makeSon(int node){
		int son = node * 2;
		return new int[]{son, ++son};
	}

	private static void propagation(int node, int start, int end, boolean flag){
		if(lazy[node] == 0) return;

		if(start != end){
			int[] son = makeSon(node);
			lazy[son[0]] = Math.max(lazy[node], lazy[son[0]]);
			lazy[son[1]] = Math.max(lazy[node], lazy[son[1]]);
		}

		if(flag) {
			tree[node] = lazy[node];
			lazy[node] = 0;
		}
	}

	private static void update(int left, int right, int val, int node, int start, int end){
		propagation(node, start ,end, false);

		if(right < start || end < left) return;
		if(left <= start && end < right){
			lazy[node] = val;
			propagation(node, start, end, false);

			return;
		}

		int[] son = makeSon(node);
		int mid = (start + end) / 2;

		update(left, right, val, son[0], start, mid);
		update(left, right, val, son[1], mid + 1, end);

		tree[node] = val;
	}

	private static int compare(int left, int right, int node, int start, int end){
		propagation(node, start, end, true);

		if(right < start || end < left) return 0;
		if(left <= start && end < right) return tree[node];

		int[] son = makeSon(node);
		int mid = (start + end) / 2;

		return compare(left, right, son[0], start, mid) | compare(left, right, son[1], mid + 1, end);
	}
}
