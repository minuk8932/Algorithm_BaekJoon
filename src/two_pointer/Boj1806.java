package two_pointer;
import java.io.IOException;
import java.io.InputStream;
import java.util.InputMismatchException;

/**
 * 
 * 	@author minchoba
 *	백준 1806번: 부분합
 *
 *	@see https://www.acmicpc.net/problem/1806/
 *
 */
public class Boj1806 {
	public static void main(String[] args) throws Exception{
		InputReader in = new InputReader(System.in);
		int N = in.readInt();
		int S = in.readInt();
		
		int[] seq = new int[N];
		
		for(int i = 0; i < N; i++) {
			seq[i] = in.readInt();
		}
		
		System.out.println(twoPointer(seq, N, S));		// 결과 출력
	}
	
	private static class Pointer{
		int start;
		int end;
		
		public Pointer(int start, int end) {
			this.start = start;
			this.end = end;
		}
	}
	
	private static int twoPointer(int[] arr, int n, int total) {
		Pointer p = new Pointer(0, 0);
		int sum = 0, min = Integer.MAX_VALUE;
		
		while(true) {
			if(sum >= total) {
                min = getMinSize(p.end - p.start, min);		// total보다 합이 클 경우 최소 사이즈 갱신
                sum -= arr[p.start++];						// 최전방 포인터를 한칸 뒤로
            }
			else if(sum < total){
				if(n == p.end) break;				// 모든 수를 비교 완료한 경우 종료
                sum += arr[p.end++];				// total보다 합이 작은 경우 최후방 포인터를 한칸 뒤로
            }
		}
		
		return min == Integer.MAX_VALUE ? 0 : min;
	}
	
	private static int getMinSize(int sSize, int min) {
		return sSize > min ? min : sSize;
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
