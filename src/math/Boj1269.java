package math;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.InputMismatchException;

/**
 * 
 * 	@author minchoba
 *	백준 1269번: 대칭 차집합
 *
 *	@see https://www.acmicpc.net/problem/1269/
 *
 */
public class Boj1269 {
	public static void main(String[] args) throws Exception{
		// Fast In을 통한 입력
		InputReader in = new InputReader(System.in);
		int A = in.readInt();
		int B = in.readInt();
		
		int[] setA = new int[A];
		int[] setB = new int[B];
		
		for(int i = 0; i < A; i++) {
			setA[i] = in.readInt();
		}
		
		for(int i = 0; i < B; i++) {			// 각 원소들을 집합 별로 저장
			setB[i] = in.readInt();
		}
		
		HashMap<Integer, Boolean> hm = new HashMap<>();
		for(int i = 0; i < A; i++) {
			hm.put(setA[i], true);
		}
		
		int res = 0;
		
		for(int i = 0; i < B; i++) {
			if(!hm.containsKey(setB[i])) res++;		// 집합 B의 차집합 갯수
		}
		
		hm = new HashMap<>();
		for(int i = 0; i < B; i++) {
			hm.put(setB[i], true);
		}
		
		for(int i = 0; i < A; i++) {
			if(!hm.containsKey(setA[i])) res++;		// 집합 A의 차집합 갯수
		}
		
		System.out.println(res);		// 결과 값 출력
	}
	
	/**
	 * Fast In
	 * @author minchoba
	 *
	 */
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
