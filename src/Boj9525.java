import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;

/**
 * 
 * 	@author exponential-e
 *	백준 17402번: 시간 끌기
 *
 *	@see https://www.acmicpc.net/problem/17402/
 *
 */
public class Boj9525{
	private static int[] aMatch, bMatch;
	private static boolean[] visit;
	
	private static ArrayList<Integer>[] connected;
	
	public static void main(String[] args) throws Exception{
		InputReader in = new InputReader(System.in);
		int N = in.readInt();
		
		connected = new ArrayList[N];
		for(int i = 0; i < N; i++) {
			connected[i] = new ArrayList<>();
		}
		
		System.out.println(bipartiteMatch(N));
	}
	
	private static int bipartiteMatch(int n){
		aMatch = new int[n];
		bMatch = new int[n];
		Arrays.fill(aMatch, -1);
		Arrays.fill(bMatch, -1);
		
		int count = 0;
		
		for(int i = 0; i < n; i++) {
			if(aMatch[i] != -1) continue;
			
			visit = new boolean[n];
			count += recursion(i);
		}
		
		return n + n - count;
	}
	
	private static int recursion(int current) {
		visit[current] = true;
		
		for(int next: connected[current]) {
			if(bMatch[next] == -1 || (!visit[bMatch[next]] && recursion(bMatch[next]) == 1)) {
				bMatch[next] = current;
				aMatch[current] = next;
				
				return 1;
			}
		}
		
		return 0;
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
