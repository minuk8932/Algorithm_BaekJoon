import java.io.IOException;
import java.io.InputStream;
import java.util.InputMismatchException;

public class Boj17216 {
	public static void main(String[] args) throws Exception{
		InputReader in = new InputReader(System.in);
		int N = in.readInt();
		
		int[] arr = new int[N];
		for(int i = N - 1; i >= 0; i--) {
			arr[i] = in.readInt();
		}
		
		System.out.println(lis(N, arr));
	}
	
	private static int lis(int n, int[] arr) {
		int[] dp = new int[n];
		int seqLength = 0;
		
		dp[seqLength] = arr[0];
		
		for(int i = 1; i < n; i++) {
			if(arr[i] == dp[seqLength]) continue;
			
			if(arr[i] < dp[seqLength]) {
				int tmp = binarySearch(dp, 0, seqLength, arr[i]);
				dp[tmp] = arr[i];
			}
			else {
				dp[++seqLength] = arr[i];
			}
		}
		
		return seqLength + 1;
	}
	
	private static int binarySearch(int[] dp, int left, int right, int target) {
		int idx = 0;
		
		while(left <= right) {
			int mid = (left + right) / 2;
			
			if(dp[mid] >= target) {
				right = mid - 1;
				idx = mid;
			}
			else {
				left = mid + 1;
			}
		}

		return idx;
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
