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
	
	public static void main(String[] args) throws Exception{
		InputReader in = new InputReader(System.in);
		OutputWriter out = new OutputWriter(System.out);
		StringBuilder sb = new StringBuilder();

		int N = in.readInt();
		int Q = in.readInt();
		
		for(int i = 0; i < N; i++) {
			int a = in.readInt();
		}
		
		for(int i = 0; i < Q; i++) {
			int T = in.readInt();
			int L = in.readInt();
			int R = in.readInt();
			
			int cnt = 0;
			
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
			
			sb.append(cnt).append(NEW_LINE);
		}
		
		out.print(sb.toString());
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
