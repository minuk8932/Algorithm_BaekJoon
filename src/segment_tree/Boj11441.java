package segment_tree;
import java.io.IOException;
import java.io.InputStream;
import java.util.InputMismatchException;

/**
 * 
 * 	@author minchoba
 *	백준 11441번: 합 구하기
 *
 *	@see https://www.acmicpc.net/problem/11441/
 *
 */
public class Boj11441 {
	private static final char NEW_LINE = '\n';
	
	public static void main(String[] args) throws Exception{
		InputReader in = new InputReader(System.in);
		int N = in.readInt();
		
		int S = 1;
		while(S < N) S <<= 1;
		
		int[] seg = new int[S * 2];
		for(int i = S; i < S + N; i++) {
			seg[i] = in.readInt();
		}
		
		seg = init(seg);		// 트리 구성
		
		int M = in.readInt();
		StringBuilder sb = new StringBuilder();
		
		while(M-- > 0) {
			int from = in.readInt();
			int to = in.readInt();
			
			sb.append(sectionSum(seg, from + S - 1, to + S - 1)).append(NEW_LINE);		// 부분 합 실행
		}
		
		System.out.println(sb);		// 결과 출력
	}
	
	private static int[] init(int[] arr) {
		for(int i = arr.length - 1; i > 0; i -= 2) {
			arr[i / 2] = arr[i] + arr[i - 1];
		}
		
		return arr;
	}
	
	private static int sectionSum(int[] tree, int left, int right) {
		int total = 0;
		
		while(left <= right) {
			if(left % 2 == 1) total += tree[left++];
			if(right % 2 == 0) total += tree[right--];
			
			left /= 2;
			right /= 2;
		}
		
		return total;
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
