package baekjoon_contest;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.InputMismatchException;

public class BSIS_ProblemG {
	private static final String NEW_LINE = "\n";
	private static final int INF = 500_001;
	
	private static InputReader in = new InputReader(System.in);
	private static OutputWriter out = new OutputWriter(System.out);
	private static StringBuilder sb = new StringBuilder();
	private static int cnt = 0;
	private static int one = 0;
	
	private static Water[] init = new Water[INF];
	private static Water[] w = new Water[INF];
	private static int[][] nums = new int[INF][1 << 10];
	
	private static final int N = in.readInt();
	private static final int Q = in.readInt();

	public static void main(String[] args) throws Exception {
		input();
		Range();
		
		out.print(sb.toString());
	}

	private static class Water {
		int idx;
		int dist;

		public Water(int idx, int dist) {
			this.idx = idx;
			this.dist = dist;
		}
	}
	
	private static void input() {
		w[0] = new Water(0, 0);
		init[0] = new Water(0, 0);

		for (int i = 0; i < N; i++) {
			int idx = -(i + 1);
			int dist = in.readInt();
			
			w[i + 1] = new Water(idx, dist);
			if(w[i + 1].dist == 1) one++;
			
			init[i + 1] = new Water(idx, dist);
		}
	}
	
	private static void Range() {
		for (int i = 0; i < Q; i++) {
			int T = in.readInt();
			int L = in.readInt();
			int R = in.readInt();
			
			cnt = 0;
			
			if(one == N) {
				if(R >= T && L <= T - N) {
					cnt += N + 1;
				}
				
				else {
					if(R > T) {
						if(L <= T) cnt += T - L + 1;
					}
					
					else if(L < T - N) {
						if(R >= T - N) cnt += R - (T - N) + 1;
					}
					
					else {
						cnt += R - L + 1;
					}
				}
			}
			else {
				init();
				process(T, L, R);
			}
			
			sb.append(cnt).append(NEW_LINE);
		}
	}

	private static void process(int T, int L, int R) {
		w[0].idx = T;
		
		for(int j = 1; j < N + 1; j++) {
			
			if(j > 1 && w[j].dist % w[j - 1].dist != 0) {
				int val = w[j].dist / w[j - 1].dist;
				w[j].dist = (val + 1) * w[j - 1].dist;
				
				if(w[j].dist < w[j - 1].dist) w[j].dist = w[j - 1].dist;
			}
			
			if(w[j - 1].idx - w[j].idx > w[j].dist)	w[j].idx += w[j].dist * (T / w[j].dist);
			if(w[j].idx >= L && w[j].idx <= R) cnt++;
			
			System.out.print(w[j].idx + " ");
		}

		System.out.println();
		
		if(T >= L && T <= R) cnt++;
	}
	
	private static void init() {
		w[0] = new Water(init[0].idx, init[0].dist);
		
		for(int i = 1; i < N + 1; i++) {
			w[i] = new Water(init[i].idx, init[i].dist);
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
			public boolean isSpaceChar(int ch);
		}
	}

	private static class OutputWriter {
		private final PrintWriter writer;

		public OutputWriter(OutputStream outputStream) {
			writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(outputStream)));
		}

		public void print(Object... objects) {
			for (int i = 0; i < objects.length; i++) {
				if (i != 0) {
					writer.print(' ');
				}
				writer.print(objects[i]);
			}
			writer.flush();
		}
	}
}
