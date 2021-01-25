package segment_tree;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 2042번: 구간 합 구하기
 *
 *	@see https://www.acmicpc.net/problem/2042/
 *
 */
public class Boj2042 {
	private static final String NEW_LINE = "\n";
	private static long[] tree = null;

	private static int S = 1;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		init(N);
		
		for(int i = S; i < N + S; i++) {
			tree[i] = Integer.parseInt(br.readLine());
		}
		
		int parent = S - 1;
		while(parent-- > 0) {
			int son = parent << 1;
			tree[parent] = tree[son] + tree[son + 1];
		}
		
		StringBuilder sb = new StringBuilder();
		int loop = M + K;
		
		while(loop-- > 0) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			long c = Long.parseLong(st.nextToken());
			
			if(a == 1) setTree(S, b, c);
			else sb.append(getSectionSum(1, b, c, 1, S - 1)).append(NEW_LINE);
		}
		
		System.out.println(sb.toString());
	}

	private static void init(int n) {
		while(S < n) {
			S <<= 1;
		}

		tree = new long[S << 1];
	}

	/**
	 * Tree value initiation
	 *
	 * line 74: propagation
	 *
	 * @param start
	 * @param oldNumIdx
	 * @param newNum
	 */
	private static void setTree(int start, int oldNumIdx, long newNum) {
		int newIdx = start + oldNumIdx - 1;
		tree[newIdx] = newNum;
		
		while((newIdx >>= 1) > 1) {
			int son = newIdx << 1;
			tree[newIdx] = tree[son] + tree[son + 1];
		}
	}

	/**
	 * Make section sum
	 *
	 * line 93: out of range
	 *
	 * @param start
	 * @param from
	 * @param to
	 * @param l
	 * @param r
	 * @return		check (left + right)
	 */
	private static long getSectionSum(int start, long from, long to, int l, int r) {
		if(r < from || l > to) return 0;
		if(from <= l && to >= r) return tree[start];
		
		int mid = (l + r) >> 1;
		int next = start << 1;

		return getSectionSum(next, from, to, l, mid) + getSectionSum(next + 1, from, to, mid + 1, r);
	}
}
