package stack;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayDeque;
import java.util.InputMismatchException;

/**
 * 
 * 	@author minchoba
 *	백준 10799번: 쇠막대기
 *
 *	@see https://www.acmicpc.net/problem/10799/
 *
 */
public class Boj10799 {
	private static final char OPEN = '(';
	
	public static void main(String[] args) throws Exception{
		InputReader in = new InputReader(System.in);
		ArrayDeque<Character> stack = new ArrayDeque<>();
		char[] arr = in.readString().toCharArray();
		
		int count = 0;
		char before = ' ';
		
		for(char c: arr) {
			if(c == OPEN) {
				stack.push(c);
			}
			else {
				if(stack.isEmpty()) continue;
				stack.pop();

				count += before == OPEN ? stack.size(): 1;		// 레이저인가? 사이즈만큼 : 1;
			}
			
			before = c;
		}
		
		System.out.println(count);		// 막개 갯수 출력
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

		public String readString() {
			int c = read();
			while (isSpaceChar(c)) {
				c = read();
			}
			StringBuilder res = new StringBuilder();
			do {
				res.appendCodePoint(c);
				c = read();
			} while (!isSpaceChar(c));
			return res.toString();
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
