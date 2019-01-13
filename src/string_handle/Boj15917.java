package string_handle;
import java.io.IOException;
import java.io.InputStream;
import java.util.InputMismatchException;

/**
 * 
 * 	@author minchoba
 *	백준 15917번: 노 솔브 방지문제야!!
 *
 *	@see https://www.acmicpc.net/problem/15917/
 *
 */
public class Boj15917 {
	private static final char NEW_LINE = '\n';
	
	public static void main(String[] args) throws Exception{
		InputReader in = new InputReader(System.in);
		StringBuilder sb = new StringBuilder();
		int Q = in.readInt();
		
		while(Q-- > 0) {
			int a = in.readInt();
			String res = Integer.toBinaryString(a);		// 이진수로
			
			sb.append(getBinary(res)).append(NEW_LINE);		// 1이 2개 이상 있다면 2의 제곱수가 아님
		}
		
		System.out.println(sb);
	}
	
	private static int getBinary(String str) {
		int count = 0;
        int leng = str.length();
		
		for(int i = 0; i < leng; i++) {
			if(str.charAt(i) == '1') count++;
			
			if(count > 1) return 0;
		}
		
		return 1;
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
