import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.LinkedList;

public class Boj2150 {
	private static ArrayDeque<Integer> stack = new ArrayDeque<>();
	private static ArrayList<Integer>[] res;
	
	private static boolean[] isVisited;
	private static int counts = 0;
	
	private static final char NEW_LINE = '\n';
	private static final char SPACE = ' ';
	
	public static void main(String[] args) throws Exception{
		InputReader in = new InputReader(System.in);
		int V = in.readInt();
		int E = in.readInt();
		
		ArrayList<Integer>[] forward = new ArrayList[V + 1];
		ArrayList<Integer>[] backward = new ArrayList[V + 1];
		isVisited = new boolean[V + 1];
		
		for(int i = 0; i < V + 1; i++) {
			forward[i] = new ArrayList<>();
			backward[i] = new ArrayList<>();
		}
		
		while(E-- > 0) {
			int A = in.readInt();
			int B = in.readInt();
			
			forward[A].add(B);
			backward[B].add(A);
		}
		
		
		DFS(forward, 1);
		isVisited = new boolean[V + 1];
		ArrayList<Integer>[] res = new ArrayList[V + 1];
		for(int i = 0; i < V + 1; i++) {
			res[i] = new ArrayList<>();
		}
		
		while(!stack.isEmpty()) {
			int start = stack.pop();
			
			if(isVisited[start]) continue;
			
			SCC(backward, start);
			counts++;
		}
	}
	
	private static void DFS(ArrayList<Integer>[] map, int current) {		
		for(int next: map[current]) {
			if(isVisited[next]) continue;
			isVisited[next] = true;
			
			DFS(map, next);
			stack.push(next);
		}
	}
	
	private static void SCC(ArrayList<Integer>[] map, int current) {		
		for(int next: map[current]) {
			if(isVisited[next]) continue;
			isVisited[next] = true;
			
			SCC(map, next);
		}
		
		res[counts].add(current);
	}

	private static StringBuilder getRes() {
		StringBuilder sb = new StringBuilder();
		
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
