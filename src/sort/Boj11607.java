package sort;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;

/**
 * 
 * 	@author minchoba
 *	백준 11607번: 모노톤길
 *
 *	@see https://www.acmicpc.net/problem/11607/
 *
 */
public class Boj11607 {
	private static ArrayList<Integer>[] roads = new ArrayList[100_001];
	private static ArrayList<Pair> cafe;
	
	private static final String NEW_LINE = "\n";
	private static final String SPACE = " ";
	
	private static class Pair {
		int x;
		int y;
		
		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) throws Exception{
		InputReader in = new InputReader(System.in);
		StringBuilder sb = new StringBuilder();

		int T = in.readInt();
		
		while(T-- > 0) {			
			int n = in.readInt();
			cafe = new ArrayList<>();
			
			for(int i = 0; i < roads.length; i++) {
				roads[i] = new ArrayList<>();
			}
			
			for(int i = 0; i < n; i++) {
				roads[in.readInt()].add(in.readInt());
			}
			
			numbering();
			int m = in.readInt();
			
			while(m-- > 0) {
				int num = in.readInt();
				sb.append(cafe.get(num).x).append(SPACE).append(cafe.get(num).y).append(NEW_LINE);
			}
		}
		
		System.out.print(sb);
	}
	
	private static void numbering() {
		cafe.add(new Pair(-1, 0));					// 정렬에 영향 받지 않을 가장 앞의 값
		
		for(int i = 0; i < roads.length; i++) {
			int roadsCount = roads[i].size();
			if(roadsCount == 0) continue;
			
			if(roadsCount == 1) {
				cafe.add(new Pair(i, roads[i].get(0)));
			}
			else {
				Collections.sort(roads[i]);			// (i, ?)의 정점 오름차순 정렬
				int length = cafe.size();
				
				if(cafe.get(length - 1).y == roads[i].get(0)) positioning(i, 0, roadsCount - 1, 1);		// 맨 앞의 y값이 동일하다면 정방향
				else positioning(i, roadsCount - 1, 0, -1);												// 맨 뒤의 y값이 동일하다면 역방향
			}
		}
	}
	
	private static void positioning(int index, int start, int end, int adder) {		// 위치 지정
		for(int i = start; i * adder <= end * adder; i += adder) {
			cafe.add(new Pair(index, roads[index].get(i)));
		}
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
