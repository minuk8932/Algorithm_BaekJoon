package segment_tree;
import java.io.IOException;
import java.io.InputStream;
import java.util.InputMismatchException;

/**
 * 
 * 	@author minchoba
 *	백준 12847번: 꿀 아르바이트
 *
 *	@see https://www.acmicpc.net/problem/12847/
 *
 */
public class Boj12847 {	
	public static void main(String[] args) throws Exception{
		InputReader in = new InputReader(System.in);
		int n = in.readInt();
		int m = in.readInt();
		
		int s = 1;
		while(s < n) s <<= 1;
		
		long[] seg = new long[s * 2];
		for(int i = s; i < s + n; i++) {
			seg[i] = in.readInt();
		}
		
		seg = init(seg);
		long max = 0;
		int start = 0;
		int end = m - 1;
		
		while(end < n) {
			long res = getSectionSum(start + s, end + s, seg);
			max = Math.max(res, max);		// 최대 급여
			
			start++;
			end++;
		}
		
		System.out.println(max);		// 결과 출력
	}
	
	private static long[] init(long[] arr) {
		for(int i = arr.length - 1; i > 0; i -= 2) {
			arr[i / 2] = arr[i] + arr[i - 1];
		}
		
		return arr;
	}
	
	private static long getSectionSum(int left, int right, long[] tree) {
		long total = 0;

		while(left <= right) {							// 세그먼트 트리 탐색
			if(left % 2 == 1) total += tree[left++];
			if(right % 2 == 0) total += tree[right--];
				
			left /= 2;
			right /= 2;
		}
		
		return total;		// 합 반환
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
