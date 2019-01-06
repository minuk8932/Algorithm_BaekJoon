import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.InputMismatchException;

/**
 * 
 * 	@author minchoba
 *	백준 2110번: 공유기 설치
 *
 *	@see https://www.acmicpc.net/problem/2110/
 *
 */
public class Boj2110 {
	public static void main(String[] args) throws Exception{
		InputReader in = new InputReader(System.in);
		int N = in.readInt();
		int C = in.readInt();
		
		int[] router = new int[N];
		for(int i = 0; i < N; i++) {
			router[i] = in.readInt();
		}
		
		Arrays.sort(router);
		System.out.println(binarySearch(router, C));	// 결과 출력
	}
	
	private static int binarySearch(int[] arr, int hits) {		
		int start = arr[0], end = arr[arr.length - 1] - arr[0];
		int res = 0;
		
		while(start <= end) {
			int mid = (start + end) / 2;
			int count = getCount(arr[0], mid, arr);		// mid(간격)에 따른 공유기 설치 갯수 반환
			
			if(hits <= count) {
				start = mid + 1;		// 갯수가 더 많은 경우
				res = mid;				// 같은 경우
			}
			else {
				end = mid - 1;
			}
		}
		
		return res;
	}
	
	private static int getCount(int first, int target, int[] arr) {
		int cnt = 1;
		
		for(int i = 1; i < arr.length; i++) {
			if(first + target <= arr[i]) {
				cnt++;
				first = arr[i];
			}
		}
		
		return cnt;
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
