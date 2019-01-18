package simulation;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Set;

/**
 * 
 * 	@author minchoba
 *	백준 13414번: 수강신청
 *
 *	@see https://www.acmicpc.net/problem/13414/
 *
 */
public class Boj13414 {
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		InputReader in = new InputReader(System.in);
		int K = in.readInt();
		int L = in.readInt();
		
		String[] students = new String[L];
		
		for(int i = L - 1; i >= 0; i--) {
			students[i] = in.readString();
		}
		
		System.out.println(possibler(students, K));
	}
	
	private static StringBuilder possibler(String[] arr, int limit) {
		ArrayDeque<String> stack = new ArrayDeque<>();
		Set<String> numberSet = new HashSet<>();
		
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < arr.length; i++) {			// 역순으로 들어온 학생들은 중복이 있는경우 가장 뒤에 깔리므로, 순위가 밀리는 학번은 알아서 처리
			if(numberSet.contains(arr[i])) continue;
			numberSet.add(arr[i]);
			stack.push(arr[i]);
		}
		
		while(!stack.isEmpty()) {		// 뒤에서 부터 즉, 가장 먼저 신청한 사람부터 하나씩 뽑아 버퍼 저장
			limit--;
			if(limit < 0) break;
			
			sb.append(stack.pop()).append(NEW_LINE);
		}
		
		return sb;
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
