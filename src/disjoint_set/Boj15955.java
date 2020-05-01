package disjoint_set;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.PriorityQueue;

/**
 *
 * @author exponential-e
 * 백준 15955번: 부스터
 *
 * @see https://www.acmicpc.net/problem/15955/
 *
 */
public class Boj15955 {
	private static int[] parent;

	private static final String Y = "YES";
	private static final String N = "NO";
	private static final String NEW_LINE = "\n";

	private static PriorityQueue<Weight> sorted = new PriorityQueue<>();

	private static class Weight implements Comparable<Weight> {
		int from;
		int to;
		int w;

		public Weight(int from, int to, int w) {
			this.from = from;
			this.to = to;
			this.w = w;
		}

		@Override
		public int compareTo(Weight weight) {
			if(this.w < weight.w) return -1;
			else if(this.w > weight.w) return 1;
			else return 0;
		}
	}

	private static class Coordinate implements Comparable<Coordinate>{
		int index;
		int p;

		public Coordinate (int index, int p) {
			this.index = index;
			this.p = p;
		}

		@Override
		public int compareTo(Coordinate c) {
			if(this.p < c.p) return -1;
			else if(this.p > c.p) return 1;
			else return 0;
		}
	}

	private static class Query implements Comparable<Query>{
		int idx;
		int from;
		int to;
		int hp;

		public Query (int idx, int from, int to, int hp) {
			this.idx = idx;
			this.from = from;
			this.to = to;
			this.hp = hp;
		}

		@Override
		public int compareTo(Query q) {
			if(this.hp < q.hp) return -1;
			else if(this.hp > q.hp) return 1;
			else return 0;
		}
	}

	public static void main(String[] args) {
		InputReader in = new InputReader(System.in);
		int N = in.readInt();
		int Q = in.readInt();

		init(N);
		Coordinate[][] data = new Coordinate[2][N];

		for(int i = 0; i < N; i++) {
			int x = in.readInt();
			int y = in.readInt();

			data[0][i] = new Coordinate(i, x);
			data[1][i] = new Coordinate(i, y);		// make sorted array by x & y
		}

		for(int i = 0; i < 2; i++) {
			setting(data[i]);
		}

		Query[] queries = new Query[Q];
		for(int i = 0; i < Q; i++) {
			int A = in.readInt() - 1;
			int B = in.readInt() - 1;
			int X = in.readInt();

			queries[i] = new Query(i, A, B, X);			// offline queries to sort hp asc, cause continuous merge
		}

		System.out.print(judgement(Q, queries));
	}

	private static String judgement(int q, Query[] queries) {
		boolean[] result = new boolean[q];

		Arrays.sort(queries);

		for(Query query: queries) {
			while(!sorted.isEmpty()) {
				Weight current = sorted.peek();
				if (current.w > query.hp) break;

				merge(current.from, current.to);					// if current two nodes less needs hp ? then arrived
				sorted.poll();
			}

			result[query.idx] = find(query.from) == find(query.to);	// A, B arrived by hp ?
		}

		StringBuilder sb = new StringBuilder();

		for (boolean flag: result) {
			sb.append(flag ? Y: N).append(NEW_LINE);
		}

		return sb.toString();
	}

	private static void init (int n) {
		parent = new int[n];

		for(int i = 0; i < n; i++) {
			parent[i] = -1;
		}
	}

	private static int find (int x) {
		if(parent[x] < 0) return x;
		else return parent[x] = find(parent[x]);
	}

	private static void merge (int x, int y) {
		x = find(x);
		y = find(y);

		if(x == y) return;

		if(parent[x] < parent[y]) {
			parent[x] += parent[y];
			parent[y] = x;
		}
		else {
			parent[y] += parent[x];
			parent[x] = y;
		}
	}

	private static void setting (Coordinate[] data) {
		Arrays.sort(data);						// if using booster, then can ignore one side

		for(int i = 1; i < data.length; i++) {
			sorted.offer(new Weight(data[i - 1].index, data[i].index, Math.abs(data[i - 1].p - data[i].p)));
		}
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

		public boolean isSpaceChar(int c) {
			if (filter != null) {
				return filter.isSpaceChar(c);
			}
			return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
		}

		public interface SpaceCharFilter {
			boolean isSpaceChar(int ch);
		}
	}
}
