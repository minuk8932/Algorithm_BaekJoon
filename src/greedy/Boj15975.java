package greedy;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.InputMismatchException;

/**
 * 
 * 	@author minchoba
 *	백준 15975번: 화살표 그리기
 *
 *	@see https://www.acmicpc.net/problem/15975/
 *
 */
public class Boj15975 {
	public static void main(String[] args) throws Exception{
		InputReader in = new InputReader(System.in);
		int N = in.readInt();
		
		Pointer[] p = new Pointer[N];
		for(int i = 0; i < N; i++) {
			p[i] = new Pointer(in.readInt(), in.readInt());
		}
		
		System.out.println(getSum(p));
	}
	
	private static class Pointer implements Comparable<Pointer>{
		int pos;
		int color;
		
		public Pointer(int pos, int color) {
			this.pos = pos;
			this.color = color;
		}

		@Override
		public int compareTo(Pointer p) {
			if(this.color < p.color) {
				return -1;
			}
			else if(this.color > p.color) {
				return 1;
			}
			else {
				if(this.pos < p.pos) return -1;
				else if(this.pos > p.pos) return 1;
				else return 0;
			}
		}
	}
	
	private static long getSum(Pointer[] arr) {
		long total = 0;
		int before = Integer.MAX_VALUE;
		
		Arrays.sort(arr);		// 색별 정렬 후 위치별 정렬
		
		for(int i = 0; i < arr.length - 1; i++) {
			if(arr[i].color == arr[i + 1].color) {
				int diff = arr[i + 1].pos - arr[i].pos;
				total += Math.min(before, diff);			// 이전 간격과 현재 간격 중 짧은 것을 더함
				
				before = diff;
			}
			
			else {
				if(i != 0) {
					if(arr[i].color == arr[i - 1].color)
						total += (arr[i].pos - arr[i - 1].pos);		// 끝 간격을 한번 더 더함
				}
				
				before = Integer.MAX_VALUE;
			}
			
			if(i == arr.length - 2) {							// 가장 마지막에 걸린 경우
				if(arr[i].color == arr[i + 1].color) {
					total += arr[i + 1].pos - arr[i].pos;
				}
			}
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
