package segment_tree;
import java.io.IOException;
import java.io.InputStream;
import java.util.InputMismatchException;

/**
 * 
 * 	@author minchoba
 *	백준 1257번: 커피숍 2
 *
 *	@see https://www.acmicpc.net/problem/1257/
 *
 */
public class Boj1275 {
	private static final char NEW_LINE = '\n';
	
	public static void main(String[] args) throws Exception{
		InputReader in = new InputReader(System.in);
		int N = in.readInt();
		int K = in.readInt();
		
		int S = 1;
		while(S < N) S <<= 1;
		
		long[] segmentTree = new long[S * 2];
		for(int i = S; i < S + N; i++) {
			segmentTree[i] = in.readInt();
		}
		
		int parent = S - 1;
		
		while(parent-- > 0) {			// 기존 트리
			int son = parent * 2;
			segmentTree[parent] = segmentTree[son] + segmentTree[son + 1];
		}
		
		StringBuilder sb = new StringBuilder();
		segmentTree[0] = 0;
		
		while(K-- > 0) {
			int x = in.readInt();
			int y = in.readInt();
			int a = in.readInt();
			long b = in.readLong();
			
			int from = x < y ? x : y;
			int to = x > y ? x : y;
			
			sb.append(getSectionSum(segmentTree, 1, from, to, 1, S)).append(NEW_LINE);		// 구간합
			segmentTree = setTree(segmentTree, S, a, b);			// 값 변경
		}
		
		System.out.println(sb);
	}
	
	private static long getSectionSum(long[] tree, int start, int from, int to, int l, int r) {
		if(r < from || l > to) return 0;
		if(from <= l && to >= r) return tree[start];
		
		int mid = (l + r) / 2;
		int next = start * 2;
		
		return getSectionSum(tree, next, from, to, l, mid) + getSectionSum(tree, next + 1, from, to, mid + 1, r);	// 올라가면서 구간합 갱신
	}
	
	private static long[] setTree(long[] tree, int start, int idx, long newNum) {
		int newIdx = idx + start - 1;
		tree[newIdx] = newNum;
		
		while(newIdx > 1) {			// 해당 인덱스 변경과 부모노드 값 변경
			newIdx /= 2;
			
			int son = newIdx * 2;
			tree[newIdx] = tree[son] + tree[son + 1];
		}
		
		return tree;
	}
	
	private static class InputReader {
		private InputStream stream;
		private byte[] buf = new byte[1024];
		private int curChar;
		private int numChars;
		private SpaceCharFilter filter;

		public InputReader(InputStream stream) {
			this.stream = stream;
		}

		public int read() {
			if (numChars == -1) {
				throw new InputMismatchException();
			}
			if (curChar >= numChars) {
				curChar = 0;
				try {
					numChars = stream.read(buf);
				} catch (IOException e) {
					throw new InputMismatchException();
				}
				if (numChars <= 0) {
					return -1;
				}
			}
			return buf[curChar++];
		}

		public int readInt() {
			int c = read();
			while (isSpaceChar(c)) {
				c = read();
			}
			int sgn = 1;
			if (c == '-') {
				sgn = -1;
				c = read();
			}
			int res = 0;
			do {
				if (c < '0' || c > '9') {
					throw new InputMismatchException();
				}
				res *= 10;
				res += c - '0';
				c = read();
			} while (!isSpaceChar(c));
			return res * sgn;
		}

		public long readLong() {
			int c = read();
			while (isSpaceChar(c)) {
				c = read();
			}
			int sgn = 1;
			if (c == '-') {
				sgn = -1;
				c = read();
			}
			long res = 0;
			do {
				if (c < '0' || c > '9') {
					throw new InputMismatchException();
				}
				res *= 10;
				res += c - '0';
				c = read();
			} while (!isSpaceChar(c));
			return res * sgn;
		}

		public boolean isSpaceChar(int c) {
			if (filter != null) {
				return filter.isSpaceChar(c);
			}
			return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
		}

		public interface SpaceCharFilter {
			public boolean isSpaceChar(int ch);
		}
	}
}
