package search;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.InputMismatchException;

/**
 * 
 * 	@author exponential-e
 *	백준 17241번: Pineapple Advertising
 *
 *	@see https://www.acmicpc.net/problem/17241/
 *
 */
public class Boj17241 {
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		InputReader in = new InputReader(System.in);
		int N = in.readInt();
		int M = in.readInt();
		int Q = in.readInt();
		
		ArrayList<Integer>[] link = new ArrayList[N];
		for(int i = 0; i < N; i++) {
			link[i] = new ArrayList<>();
		}
		
		while(M-- > 0) {
			int node1 = in.readInt() - 1;
			int node2 = in.readInt() - 1;
			
			link[node1].add(node2);
			link[node2].add(node1);
		}
		
		StringBuilder sb = new StringBuilder();
		boolean[] adjVisit = new boolean[N];
		boolean[] visit = new boolean[N];
		
		for(int i = 0; i < Q; i++) {
			int current = in.readInt() - 1;
			int count = 1;
			
			if(visit[current]) {						// origin visit
				sb.append(0).append(NEW_LINE);
				continue;
			}
			
			if(adjVisit[current]) count = 0;			// adjacent visit
			adjVisit[current] = visit[current] = true;
			
			for(int next: link[current]) {
				if(adjVisit[next]) continue;
				adjVisit[next] = true;
				count++;								// pizza advertising
			}
			
			sb.append(count).append(NEW_LINE);
		}
		
		System.out.println(sb.toString());
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
