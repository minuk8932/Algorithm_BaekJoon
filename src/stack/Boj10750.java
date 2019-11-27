package stack;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.InputMismatchException;

/**
 * 
 * 	@author exponential-e
 *	백준 10750번: Censoring
 *
 *	@see https://www.acmicpc.net/problem/10750/
 *
 */
public class Boj10750 {
	public static void main(String[] args) throws Exception {
		InputReader in = new InputReader(System.in);
		System.out.println(censoring(in.readString().toCharArray(), in.readString().toCharArray()));
	}
	
	private static String censoring(char[] S, char[] T) {
		ArrayDeque<Character> stack = new ArrayDeque<>();
		ArrayList<Character> tmp = new ArrayList<>();
		
		for(int i = 0; i < S.length; i++) {
			tmp = new ArrayList<>();
			
			stack.push(S[i]);
			
			if(i >= T.length - 1 && S[i] == T[T.length - 1]) {
				int cnt = 0, idx = 0;

				for(int j = T.length - 1; j >= 0; j--) {
					if(stack.isEmpty())	break;
					tmp.add(idx, stack.pop());				// make substring
					
					if(tmp.get(idx) == T[j]) cnt++;
					else break;
					
					idx++;
				}
				
				if(cnt == T.length) continue;				// is matched
				idx = tmp.size() - 1;
				
				for(int j = idx; j >= 0; j--) {
					stack.push(tmp.get(j));
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();

		while(!stack.isEmpty()) {
			sb.append(stack.pop());
		}
		
		return sb.reverse().toString();
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
