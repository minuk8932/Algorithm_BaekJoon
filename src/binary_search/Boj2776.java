package binary_search;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.InputMismatchException;

/**
 * 
 * 	@author minchoba
 *	백준 2776번: 암기왕
 *
 *	@see https://www.acmicpc.net/problem/2776/
 *
 */
public class Boj2776 {
	public static void main(String[] args) throws Exception{
		InputReader in = new InputReader(System.in);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = in.readInt();
		
		while(T-- > 0) {
			int N = in.readInt();
			
			int[] one = new int[N];
			for(int i = 0; i < N; i++) {
				one[i] = in.readInt();
			}
			
			int M = in.readInt();
			
			int[] two = new int[M];
			for(int i = 0; i < M; i++) {
				two[i] = in.readInt();
			}
			
			Arrays.sort(one);
			for(int i = 0; i < M; i++) {
				bw.write(binarySearch(N, i, one, two) + "\n");		// 이분탐색으로 해당 값이 존재하는지 판별
			}
		}
		
		bw.flush();
	}
	
	private static int binarySearch(int n, int idx, int[] arr1, int[] arr2) {	
		int target = arr2[idx];
		int start = 0, end = n;
			
		while(start <= end) {
			int mid = (start + end) / 2;
			if(mid < 0 || mid >= n) return 0;		// 범위 초과의 경우 해당 값이 존재하지 않음
				
			if(arr1[mid] > target) {
				end = mid - 1;
			}
			else if(arr1[mid] < target) {
				start = mid + 1;
			}
			else {									// 타겟이 존재하는 경우
				return 1;
			}
		}
		
		return 0;					// 탐색 완료시까지 동일한 값을 찾지 못한 경우
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
