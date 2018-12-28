package dynamic_programming;
import java.io.IOException;
import java.io.InputStream;
import java.util.InputMismatchException;

/**
 * 
 * 	@author minchoba
 *	백준 1818번: 책정리
 *
 *	@see https://www.acmicpc.net/problem/1818/
 *
 */
public class Boj1818 {
	public static void main(String[] args) throws Exception{
		InputReader in = new InputReader(System.in);
		int N = in.readInt();
		int[] books = new int[N];
		
		for(int i = 0; i < N; i++) {
			books[i] = in.readInt();
		}
		
		System.out.println(LIS(books, N));		// 결과 출력
	}
	
	private static int LIS(int[] arr, int n) {
		int dPoint = 0;
		int[] dp = new int[n];
		int switching = 0;
		
		dp[0] = arr[0];
		
		for(int idx = 1; idx < n; idx++) {
			if(dp[dPoint] < arr[idx]) {			// 책번호 순서대로
				dp[++dPoint] = arr[idx];
			}
			
			else {
				int choose = binarySearch(0, dPoint, arr[idx], dp);		// 순서에 안맞는 경우, 이분 탐색으로 위치 지정
				dp[choose] = arr[idx];
				switching++;
			}
		}
		
		return switching;
	}
	
	private static int binarySearch(int start, int end, int target, int[] dp) {
		int mid = 0;
		
		while(start < end) {
			mid = (start + end) / 2;
			
			if(dp[mid] < target) {
				start = mid + 1;
			}
			else if(dp[mid] > target){
				end = mid;
			}
            else{				// 제자리를 찾음
                return end;
            }
		}
		
		return end;
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
