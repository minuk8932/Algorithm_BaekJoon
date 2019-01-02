package disjoint_set;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.InputMismatchException;

/**
 * 
 * 	@author minchoba
 *	백준 4195번: 친구 네트워크
 *
 *	@see https://www.acmicpc.net/problem/4195/
 *
 */
public class Boj4195 {
	private static int[] parent;
	private static final char NEW_LINE = '\n';
	
	private static HashMap<String, Integer> hm = new HashMap<>();
	
	public static void main(String[] args) throws Exception{
		InputReader in = new InputReader(System.in);
		StringBuilder sb = new StringBuilder();
		int T = in.readInt();
		
		while(T-- > 0) {
			int F = in.readInt();
			parent = new int[2 * F + 1];
			init();
			
			int count = 1;
			
			for(int i = 0; i < F; i++) {
				String name1 = in.readString();
				String name2 = in.readString();
				
				boolean already = initPerson(name1, count);
				if(!already) count++;
				
				already = initPerson(name2, count);
				if(!already) count++;
				
				int from = hm.get(name1);
				int to = hm.get(name2);
				
				sb.append(-merge(from, to)).append(NEW_LINE);
			}
		}
		
		System.out.println(sb);		// 결과 출력
	}
	
	private static void init() {
		for(int i = 1 ; i < parent.length; i++) {
			parent[i] = -1;
		}
	}
	
	private static boolean initPerson(String str, int idx) {
		if(hm.containsKey(str)) return true;
		
		hm.put(str, idx);
		return false;
	}
	
	private static int find(int x) {
		if(parent[x] < 0) return x;
		else return parent[x] = find(parent[x]);
	}
	
	private static int merge(int x, int y) {
		x = find(x);
		y = find(y);
		
		if(x != y) {
			if(parent[x] > parent[y]) {		// 음수 값으로 관계수 추가
				parent[y] += parent[x];
				parent[x] = y;
			}
			else {
				parent[x] += parent[y];
				parent[y] = x;
			}
		}
		
		return parent[x] < 0 ? parent[x] : parent[y];
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

		public String readString() {
			int c = read();
			while (isSpaceChar(c)) {
				c = read();
			}
			StringBuilder res = new StringBuilder();
			do {
				res.appendCodePoint(c);
				c = read();
			} while (!isSpaceChar(c));
			return res.toString();
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
