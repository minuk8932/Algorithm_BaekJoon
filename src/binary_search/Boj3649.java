package binary_search;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.InputMismatchException;

/**
 * 
 * @author minchoba
 * 백준 3649번: 로봇 프로젝트
 * 
 * @see https://www.acmicpc.net/problem/3649/
 *
 */
public class Boj3649 {
	private static final int NANO = 10_000_000;
	private static final String CORRECT = "yes";
	private static final String WRONG = "danger";
	private static final String SPACE = " ";
	
	public static void main(String[] args) throws Exception{
		InputReader in = new InputReader(System.in);
		
		while(true) {
			int x = 0;
			try {
				x = in.readInt() * NANO;
			}
			catch (Exception e) {
				break;
			}
			
			int n = in.readInt();
			
			int[] lego = new int[n];
			for(int i = 0; i < n; i++) {
				lego[i] = in.readInt();
			}
			
			Arrays.sort(lego);
			System.out.println(binarySearch(x, lego));		// 결과 출력
		}
	}
	
	private static StringBuilder binarySearch(int cm, int[] arr) {
		StringBuilder sb = new StringBuilder();
		
		int start = 0, end = arr.length - 1;
		boolean isTrue = false;
		
		while(start < end) {			
			if(arr[start] + arr[end] < cm) {		// 더 긴 블럭을 가져옴
				start += 1;
			}
			else if(arr[start] + arr[end] > cm){	// 더 짧은 블럭을 가져옴
				end -= 1;
			}
			else {					// 양 끝 합이 존재하는 경우
				isTrue = true;
				break;
			}
		}
		
		if(!isTrue) sb.append(WRONG);
		else sb.append(CORRECT).append(SPACE).append(arr[start]).append(SPACE).append(arr[end]);
		
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
