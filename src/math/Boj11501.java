package math;
import java.io.IOException;
import java.io.InputStream;
import java.util.InputMismatchException;

/**
 * 
 * 	@author minchoba
 *	백준 11501번: 주식
 *
 *	@see https://www.acmicpc.net/problem/11501/
 *
 */
public class Boj11501 {
	private static final char NEW_LINE = '\n';
	
	public static void main(String[] args) throws Exception{
		InputReader in = new InputReader(System.in);
		StringBuilder sb = new StringBuilder();
		int T = in.readInt();
		
		while(T-- > 0) {
			int N = in.readInt();
			int[] cost = new int[N];
			
			for(int i = N - 1; i >= 0; i--) {		// 날 별 주식을 거꾸로 저장
				cost[i] = in.readInt();
			}
			
			sb.append(getSum(cost)).append(NEW_LINE);
		}
		
		System.out.println(sb);		// 결과 출력
	}
	
	private static long getSum(int[] arr) {
		long total = 0;
		int max = 0;
		
		for(int i = 0; i < arr.length; i++) {
			if(arr[i] >= max) {					// 해당 날의 주식이 최댓값이면 갱신
				max = arr[i];
				continue;
			}
			
			total += (max - arr[i]);			// 최댓값보다 작은 주가라면 이익을 총합에 더해줌
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
