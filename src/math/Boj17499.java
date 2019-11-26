package math;
import java.io.IOException;
import java.io.InputStream;
import java.util.InputMismatchException;

/**
 * 
 * 	@author exponential-e
 *	백준 17499번: 수열과 시프트 쿼리
 *
 *	@see https://www.acmicpc.net/problem/17499/
 *
 */
public class Boj17499 {
	private static final String SPACE = " ";
	
	public static void main(String[] args) throws Exception{
		InputReader in = new InputReader(System.in);
		int N = in.readInt();
		int Q = in.readInt();
	
		long[] arr = new long[N];
		for(int i = 0; i < N; i++) {
			arr[i] = in.readLong();
		}
		
		int cursor = 0;
		
		while(Q-- > 0) {
			int cmd = in.readInt();
			
			if(cmd == 1) {							// adder
				int idx = in.readInt() - 1;
				arr[(idx + cursor) % N] += in.readInt();
			}
			else if(cmd == 2){						// shift right
				cursor -= in.readInt();
				if(cursor < 0) {
					cursor %= N;
					
					if(cursor < 0) cursor += N;
				}
			}
			else {									// shift left
				cursor += in.readInt();
			}
			
			cursor %= N;
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < N; i++) {
			sb.append(arr[(i + cursor) % N]).append(SPACE);
		}
		
		System.out.println(sb.toString());
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
