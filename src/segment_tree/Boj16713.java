package segment_tree;
import java.io.IOException;
import java.io.InputStream;
import java.util.InputMismatchException;

/**
 * 
 * 	@author minchoba
 *	백준 16713번: Generic Queries
 *
 *	@see https://www.acmicpc.net/problem/16713/
 *
 */
public class Boj16713 {
	private static int[] arr;
	
	public static void main(String[] args) throws Exception{
		InputReader in = new InputReader(System.in);
		int N = in.readInt();
		int Q = in.readInt();
		
		int S = 1;
		while(S < N) S <<= 1;
		
		arr = new int[S * 2];
		for(int i = S; i < S + N; i++) {
			arr[i] = in.readInt();
		}
		
		init();
		int res = 0;		// XOR 항등원
		
		while(Q-- > 0) {
			int s = in.readInt();
			int e = in.readInt();
			
			res ^= getSectionXOR(s + S - 1, e + S - 1);
		}
		
		System.out.println(res); // 결과
	}
	
	private static void init() {
		for(int i = arr.length - 1; i > 0; i -= 2) {		// 초기 부분 XOR
			arr[i / 2] = arr[i] ^ arr[i - 1];
		}
	}
	
	private static int getSectionXOR(int start, int end) {
		int sum = 0;
		
		while(start <= end) {
			if(start % 2 == 1) {		// 상위 트리 XOR
				sum ^= arr[start++];
			}
			
			if(end % 2 == 0) {
				sum ^= arr[end--];
			}
			
			start /= 2;
			end /= 2;
		}
		
		return sum;
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
}
