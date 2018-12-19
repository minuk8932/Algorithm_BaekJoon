package dynamic_programming;
import java.io.IOException;
import java.io.InputStream;
import java.util.InputMismatchException;

/**
 * 
 * 	@author minchoba
 *	백준 12015번: 가장 긴 증가하는 부분 수열 2
 *
 * 	@see https://www.acmicpc.net/problem/12015/
 * 
 */
public class Boj12015 {
	private static final int INF = 1_000_001;
	
	public static void main(String[] args) throws Exception{
		InputReader in = new InputReader(System.in);
		int N = in.readInt();
		
		int[] A = new int[N];
		for(int i = 0; i < N; i++) {
			A[i] = in.readInt();
		}
		
		System.out.println(LIS(A));
	}
	
	private static int LIS(int[] arr) {
		int leng = 0;
		int[] dp = new int[arr.length];
		int[] trace = new int[INF];
		
		for(int i = 0; i < arr.length; i++) {
			if(i == 0 || arr[i] > dp[leng - 1]) {		// 수열 길이 갱신
				trace[arr[i]] = leng;
				dp[leng++] = arr[i];
			}
			else {
				int start = 0, end = leng, mid = 0;
				int idx = leng;
				
				while(start < end) {					// 이분 탐색으로 다음 증가 수열을 찾음
					mid = (start + end) / 2;
					
					if(dp[mid] >= arr[i]) {
						if(mid < idx) idx = mid;
						end = mid;
					}
					else {
						start = mid + 1;
					}
				}
				
				dp[idx] = arr[i];
				trace[arr[i]] = idx;
			}		
		}
		
		return leng;
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
