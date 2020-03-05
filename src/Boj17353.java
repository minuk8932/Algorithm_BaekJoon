import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj17353 {
	private static int start;
	private static long tree[],lazy[];

	private static final String NEW_LINE = "\n";

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		start = 1;
		while(start <= N) start <<= 1;

		tree = new long[start * 2];
		lazy = new long[start * 2];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			long num = Long.parseLong(st.nextToken());
			init(i, num);
		}

		StringBuilder sb = new StringBuilder();
		int Q = Integer.parseInt(br.readLine());

		while(Q-- > 0) {
			st = new StringTokenizer(br.readLine());
			int method = Integer.parseInt(st.nextToken());

			if (method == 1) {
				int A = Integer.parseInt(st.nextToken()) - 1;
				int B = Integer.parseInt(st.nextToken()) - 1;

				setRange(A, B, 1, 0, start - 1, 1);
			}
			else {
				int X = Integer.parseInt(st.nextToken()) - 1;
				sb.append(getSum(X, X, 1, 0, start - 1)).append(NEW_LINE);
			}
		}

		System.out.print(sb.toString());
	}
	private static void setRange(int L, int R, int idx, long left, long right, long num){
		if(lazy[idx] != 0){
			tree[idx] += (right - left + 1) * lazy[idx];

			if(right!=left){							// save lazy
				lazy[idx * 2] += lazy[idx];
				lazy[idx * 2 + 1] += lazy[idx];
			}

			lazy[idx] = 0;
		}

		if (L > right || R < left) return;
		if(L <= left && right <= R){
			tree[idx] += (right - left + 1) * num;

			if(left != right){
				lazy[idx * 2] += num;
				lazy[idx * 2 + 1] += num;
			}

			return;
		}

		long mid = (left + right) / 2;

		setRange(L, R, idx * 2, left, mid, num);				// update recursion
		setRange(L, R, idx * 2 + 1, mid + 1, right, num);
		tree[idx] = tree[idx * 2] + tree[idx * 2 + 1];
	}

	private static void init(int idx, long val) {
		int index = start + idx;
		tree[index] = val;

		while (index > 1) {
			index /= 2;
			tree[index] = tree[index * 2] + tree[index * 2 + 1];
		}
	}

	private static long getSum(int L, int R, int idx, long left, long right) {		// make sum with lazy
		if(lazy[idx] != 0){
			tree[idx] += (right - left + 1) * lazy[idx];

			if(right != left){
				lazy[idx * 2] += lazy[idx];
				lazy[idx * 2 + 1] += lazy[idx];
			}

			lazy[idx] = 0;
		}

		if (L > right || R < left) return 0;
		if (L <= left && right <= R) return tree[idx];

		long mid = (left + right) / 2;
		return getSum(L, R, idx * 2, left, mid) + getSum(L, R, idx * 2 + 1, mid + 1, right);
	}
}
