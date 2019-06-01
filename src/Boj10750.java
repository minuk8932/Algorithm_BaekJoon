import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayDeque;
import java.util.InputMismatchException;

public class Boj10750 {
	public static void main(String[] args) throws Exception {
		InputReader in = new InputReader(System.in);
		System.out.println(censoring(in.readString().toCharArray(), in.readString()));
	}
	
	private static String censoring(char[] S, String T) {
		ArrayDeque<Character> stack = new ArrayDeque<>();
		String revT = getRev(T.toCharArray());
		
		for(int i = 0; i < S.length; i++) {
			stack.push(S[i]);
			if(S[i] != revT.charAt(0)) continue;
			
			String str = "";
			int loop = revT.length();
			
			while(loop-- > 0) {
				if(stack.isEmpty()) break;
				str += stack.pop();
			}
			
			if(revT.equals(str)) continue;
			
			for(int j = str.length() - 1; j >= 0; j--) {
				stack.push(str.charAt(j));
			}
		}
		
		StringBuilder sb = new StringBuilder();

		while(!stack.isEmpty()) {
			sb.append(stack.pop());
		}
		
		return sb.reverse().toString();
	}
	
	private static String getRev(char[] arr) {
		StringBuilder sb = new StringBuilder();
		
		for(int i = arr.length - 1; i >= 0; i--) {
			sb.append(arr[i]);
		}
		
		return sb.toString();
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
