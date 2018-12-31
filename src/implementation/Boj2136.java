package implementation;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.InputMismatchException;

/**
 * 
 * 	@author minchoba
 *	백준 2136번: 개미
 *
 *	@see https://www.acmicpc.net/problem/2136/
 *
 */
public class Boj2136 {
	public static void main(String[] args) throws Exception{
		InputReader in = new InputReader(System.in);
		int N = in.readInt();
		int L = in.readInt();
		
		int left = 0;
		int ant1 = 0, ant2 = 0;
		
		Pair[] p = new Pair[N + 1];
		p[0] = new Pair(-1, -1);
		for(int i = 1; i < N + 1; i++) {
			int ant = in.readInt();
			
			if(ant < 0) {
				left++;
				ant = -ant;
				ant1 = getMaxAnt(ant, ant1);		// 좌측 개미 중 가장 큰 값
			}
			else {
				ant2 = getMaxAnt(L - ant, ant2);	// 우측
			}
			
			p[i] = new Pair(i, ant);
		}
		
		Pair res = getRes(p, ant1, ant2, left);
		System.out.println(res.idx + " " + res.pos);
	}
	
	private static class Pair implements Comparable<Pair>{
		int idx;
		int pos;
		
		public Pair(int idx, int pos) {
			this.idx = idx;
			this.pos = pos;
		}

		@Override
		public int compareTo(Pair p) {
			return this.pos < p.pos ? -1 : 1;
		}
	}
	
	private static int getMaxAnt(int ant1, int ant2) {
		return ant2 > ant1 ? ant2 : ant1;
	}
	
	private static Pair getRes(Pair[] ants, int a, int b, int i) {	
		Arrays.sort(ants);
		int pre = ants[i].idx;
		int post = ants[i + 1].idx;
		
		return a > b ? new Pair(pre, a) : new Pair(post, b);	// 큰 값과, 가장 마지막에 떨어진 개미의 번호 (좌, 우) 구분
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
