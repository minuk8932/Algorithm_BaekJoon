import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.InputMismatchException;

public class Boj11607 {
	private static Pair[] roads;
	private static Pair[] cafe;
	
	private static final String NEW_LINE = "\n";
	private static final String SPACE = " ";
	
	private static class Pair implements Comparable<Pair>{
		int x;
		int y;
		
		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(Pair p) {
			if(this.x < p.x) {
				return -1;
			}
			else if(this.x > p.x) {
				return 1;
			}
			else {
				int ty = Math.abs(this.y);
				int py = Math.abs(p.y);
						
				if(ty < py) return -1;
				else if(ty > py) return 1;
				else return 0;
			}
		}
	}
	
	public static void main(String[] args) throws Exception{
		InputReader in = new InputReader(System.in);
		StringBuilder sb = new StringBuilder();

		int T = in.readInt();
		
		while(T-- > 0) {			
			int n = in.readInt();
			cafe = new Pair[n];
			roads = new Pair[n];
			
			for(int i = 0; i < n; i++) {
				roads[i] = new Pair(in.readInt(), in.readInt());
			}
			
			numbering();
			int m = in.readInt();
			
			while(m-- > 0) {
				int num = in.readInt() - 1;
				sb.append(cafe[num].x).append(SPACE).append(cafe[num].y).append(NEW_LINE);
			}
		}
		
		System.out.print(sb);
	}
	
	private static void numbering() {
		Arrays.sort(roads);
		cafe[0] = roads[0];
		
		Pair current = new Pair(cafe[0].x, cafe[0].y);
		Pair save = new Pair(-1, -1);
		int index = 1;
		
		for(int i = 1; i < roads.length; i++) {
			Pair next = roads[i];
			
			if(current.y == next.y) {
				cafe[index++] = next;
			}
			else {
				if(current.x == next.x) {
					cafe[index++] = new Pair(next.x, next.y);
					
					if(save.x != -1) {
						cafe[index++] = new Pair(save.x, save.y);						
						save = new Pair(-1, -1);
					}
				}
				else {
					save = next;
				}
			}
			
			current = next;
		}
		
		if(save.x != -1) cafe[index] = new Pair(save.x, save.y);
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
